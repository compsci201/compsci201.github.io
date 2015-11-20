
/**
 *	Modified and updated slightly from previous versions written by
 *	Owen Astrachan for earlier versions of the Huffman Assignment.
 *	Allows users to write a specified number of bits at a time (e.g. 1)
 *	as opposed to entire bytes at time by buffering values until there
 *	are enough to write using the underlying OutputStream. Re-throws
 *	exceptions as RuntimeExceptions so user does not have to handle them
 *	in there own code. Take note, user must call flush() or close() after
 *	using a BitOutputStream to ensure that buffer is properly written to
 *	the output.
 *
 *	@author Owen Astrachan
 *	@contributor Brian Lavallee
 *	@since 5 November 2015
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream extends OutputStream {

	private static final int BYTE_SIZE = 8;

	private static final int bmask[] = { 0x00, 0x01, 0x03, 0x07, 0x0f, 0x1f, 0x3f, 0x7f, 0xff, 0x1ff, 0x3ff, 0x7ff,
			0xfff, 0x1fff, 0x3fff, 0x7fff, 0xffff, 0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff,
			0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, 0xffffffff };

	private OutputStream output;

	private int buffer;
	private int bitsToGo;

	private int bitsWritten;

	/**
	 * Constructs a one-bit-at-a-time OutputStream from a file. (creates File
	 * and calls alternative constructor)
	 *
	 * @param filePath
	 *            Location of the output in the user's file system.
	 */
	public BitOutputStream(String filePath) {
		this(new File(filePath));
	}

	/**
	 * Constructs a one-bit-at-a-time OutputStream from a file. Re-throws
	 * FileNotFoundException as a RuntimeException so that the user does not
	 * have to catch and handle it in their own code.
	 *
	 * @param source
	 *            File that the BitOutputStream will write to.
	 */
	public BitOutputStream(File source) {
		try {
			initialize(new FileOutputStream(source));
		} catch (FileNotFoundException fnf) {
			throw new RuntimeException("Could not open file " + fnf);
		}
	}

	/**
	 * Constructs a one-bit-at-a-time OutputStream from another implementation
	 * of OutputStream.
	 *
	 * @param out
	 *            The alternative OutputStream to use as the output destination.
	 */
	public BitOutputStream(OutputStream out) {
		initialize(out);
	}

	/*
	 * Initializes instance variables.
	 */
	private void initialize(OutputStream out) {
		output = out;
		bitsWritten = buffer = 0;
		bitsToGo = BYTE_SIZE;
	}

	/**
	 * Allows to user to access the total number of bits written by the
	 * OutputStream without counting themselves.
	 *
	 * @return bitsWritten which is incremented on every writeBits() call.
	 */
	public int getBitsWritten() {
		return bitsWritten;
	}

	/**
	 * Writes any bits remaining in the buffer out to the file.
	 */
	public void flush() {
		if (bitsToGo != BYTE_SIZE) {
			write((buffer << bitsToGo));
			buffer = 0;
			bitsToGo = BYTE_SIZE;
		}

		try {
			output.flush();
		} catch (IOException ioe) {
			throw new RuntimeException("Error on flush " + ioe);
		}
	}

	/**
	 * Flushes remaining bits in buffer. Additional functionality depends on
	 * implementation of underlying OutputStream.
	 */
	public void close() {
		flush();
		try {
			output.close();
		} catch (IOException ioe) {
			throw new RuntimeException("Error closing BitOutputStream " + ioe);
		}
	}

	/**
	 * Overridden abstract method from OutputStream. Calls writeBits() which
	 * should be used as the primary writing method for users.
	 */
	public void write(int b) {
		writeBits(BYTE_SIZE, b);
	}

	/**
	 * Writes numBits bits to the OutputStream. Uses a buffer to store extra
	 * bits until there are enough to write a full byte.
	 *
	 * @param numBits
	 *            The number of bits the user wants to write.
	 * @param value
	 *            The int value of the bits to write.
	 */
	public void writeBits(int numBits, int value) {
		bitsWritten += numBits;
		value &= bmask[numBits];

		while (numBits >= bitsToGo) {
			buffer = (buffer << bitsToGo) | (value >>> (numBits - bitsToGo));
			try {
				output.write(buffer);
			} catch (IOException ioe) {
				throw new RuntimeException("Error writing bits " + ioe);
			}

			value &= bmask[numBits - bitsToGo];
			numBits -= bitsToGo;
			bitsToGo = BYTE_SIZE;
			buffer = 0;
		}

		if (numBits > 0) {
			buffer = (buffer << numBits) | value;
			bitsToGo -= numBits;
		}
	}
}