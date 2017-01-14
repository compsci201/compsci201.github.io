
/**
 *	Adapted from BitInputStream written by Owen Astrachan for previous versions
 *	of the Huffman assignment. Allows user to read a specified number of bits
 *	at a time by buffering extra bits. Upgraded so that all BitInputStream objects
 *	can be reset. Re-throws exceptions as RuntimeException so user does not have
 *	to catch and handle any file input/output related errors or exceptions at compile
 *	time.
 *
 *	@author Brian Lavallee
 *	@since 5 November 2015
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitInputStream extends InputStream {

	private static final int BYTE_SIZE = 8;

	private static final int bmask[] = { 0x00, 0x01, 0x03, 0x07, 0x0f, 0x1f, 0x3f, 0x7f, 0xff, 0x1ff, 0x3ff, 0x7ff,
			0xfff, 0x1fff, 0x3fff, 0x7fff, 0xffff, 0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff,
			0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, 0xffffffff };

	private InputStream input;

	private int bitCount;
	private int buffer;

	private int bitsRead;

	/**
	 * Constructs a one-bit-at-a-time InputStream from a file. (creates File
	 * object and calls alternative constructor)
	 *
	 * @param filePath
	 *            Location of the source of the InputStream in the user's file
	 *            system.
	 */
	public BitInputStream(String filePath) {
		this(new File(filePath));
	}

	/**
	 * Constructs a one-bit-at-a-time InputStream from a file. Catches the
	 * FileNotFoundException and re-throws it as a RuntimeException so user does
	 * not have to handle it within there own code.
	 *
	 * @param source
	 *            Source of the InputStream's bits.
	 */
	public BitInputStream(File source) {
		try {
			initialize(new FileInputStream(source));
		} catch (FileNotFoundException fnf) {
			throw new RuntimeException(fnf.getMessage());
		}
	}

	/**
	 * Constructs a one-bit-at-a-time InputStream from another implementation of
	 * InputStream.
	 *
	 * @param in
	 *            The alternative InputStream to use as the source.
	 */
	public BitInputStream(InputStream in) {
		initialize(in);
	}

	/*
	 * Initializes instance variables.
	 */
	private void initialize(InputStream in) {
		input = new BufferedInputStream(in);
		input.mark(Integer.MAX_VALUE);
		bitCount = buffer = bitsRead = 0;
	}

	/**
	 * Allows user to access the total number of bits read so far without
	 * counting themselves.
	 *
	 * @return bitsRead which is incremented every time readBits() is called.
	 */
	public int getBitsRead() {
		return bitsRead;
	}

	/**
	 * Resetting is fully supported by BitInputStream. Re-throws IOException as
	 * RuntimeException.
	 */
	public void reset() {
		try {
			input.reset();
			input.mark(Integer.MAX_VALUE);
			bitsRead = buffer = bitCount = 0;
		} catch (IOException ioe) {
			throw new RuntimeException("Error resetting stream " + ioe);
		}
	}

	/**
	 * Closes the input stream. Affect depends on type of InputStream used in
	 * constructor. If a file is used, makes all subsequent read() calls throw
	 * and IOException.
	 */
	public void close() {
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException ioe) {
			throw new RuntimeException("Error closing bit stream " + ioe);
		}
	}

	/**
	 * Overridden abstract method in InputStream. Only calls readBits() which
	 * should be used as the primary method for reading from a BitInputStream.
	 */
	public int read() {
		return readBits(BYTE_SIZE);
	}

	/**
	 * Reads numBits bits from the InputStream. Uses a buffer to store extra
	 * bits should numBits not require all of the bits from the full byte read.
	 *
	 * @param numBits
	 *            The number of bits the user wants to read.
	 * @return The int value of the bits read.
	 */
	public int readBits(int numBits) {
		bitsRead += numBits;
		int retval = 0;

		if (input == null) {
			return -1;
		}

		while (numBits > bitCount) {
			retval |= (buffer << (numBits - bitCount));
			numBits -= bitCount;

			try {
				if ((buffer = input.read()) == -1) {
					return -1;
				}
			} catch (IOException ioe) {
				throw new RuntimeException("Problem reading bits " + ioe);
			}

			bitCount = BYTE_SIZE;
		}

		if (numBits > 0) {
			retval |= buffer >> (bitCount - numBits);
			buffer &= bmask[bitCount - numBits];
			bitCount -= numBits;
		}
		return retval;
	}
}