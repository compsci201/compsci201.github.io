---
layout: page
title: "Summary"
assignment: "huffman"

---


You will write a class that implements the `IHuffProcessor` interface, which requires you to write three methods, thus we will break the assignment up into details for each of these methods, as they will be somewhat lengthy. 


###PreProcess: 

In this method you will read through the file, finding character frequiencies which you will use to write encodings for each character. You create a Huffman tree to derive per-character encodings.

###Compression:

You loop through each character in the file, find the encodings using the tree, and write bits based on these encodings. The `Huff` main program has a GUI front-end whose menu offers three choices: count characters, compress, uncompress (and quit as a fourth choice). You can't compress until you can count/create a tree, so make sure counting/tree-creation/encoding works.

Because the compression scheme involves reading and writing in a bits-at-a-time manner as opposed to a char-at-a-time manner, the program can be hard to debug. In order to facilitate the design/code/debug cycle, you should take care to develop the program in an incremental fashion. If you try to write the whole program at once, it will be difficult to get a completely working program. 

###Decompression: 

To uncompress a file your program previously compressed you'll need to read header information from the compressed file your program creates. The header information is data that you'll use to recreate the Huffman tree that was originally used to compress the data. This header is key, to uncompress you need the same tree you used to compress. After recreating the tree your code will read one bit at a time to uncompress the data and recreate the original file that was compressed. 

Basically you read the header information to recreate the tree, then do a tree-walk one bit at a time to find the characters stored in the leaves of the Huffman tree. Each time you find a leaf you print the value there. This process recreates the original, uncompressed file.

###Using BitInputStream and BitOutputStream:

Look over the classes `BitInputStream` and `BitOutputStream` and their javadoc comments as you will be using both extensively. Here is an example of how the method **readBits** works to read a file: 

	int inbits;
    BitInputStream bits = new BitInputStream(new FileInputStream("data/txt"));
    while ((inbits = bits.readBits(BITS_PER_WORD)) != -1) {
        System.out.println(inbits);   // put writes one character
    }

`BITS_PER_WORD` has the value of 8, so this will read in 8-bit chunks. When the end of the file is reached, the method **readBits** will return -1. 

If you want to access just one bit an int returned from **readBits** you can use the bitwise and:

	int inbits;
    BitInputStream bits = new BitInputStream(new FileInputStream("data/poe.txt"));
    inbits = bits.readBits(1);
    if ((inbits & 1) == 1)
        // do stuff because the bit read was 1 
    else
        // do stuff because the bit read was 0

Alternatively, you could just mod the int by 2 and check if the remainder is 1 or 0, but using bitwise is slighty faster. 


When you use `BitOutputStream` and the method **writeBits**, some bits will not be written immediately because of buffering. To ensure all bits get written, the last bits should be explicitly flushed. You can call the method **flush**, or preferably you can call **close** to close your output stream, which calls flush in its method body. 


###PSEUDO_EOF:

Your operating system is going to buffer output. This means output to disk doesn't actually occur until an internal buffer is full. It is not actually possible to write just one single bit at a time to a file. All output is actually done in "chunks," possibly 8-bit or 256-bit chunks. When you use `BitOutputStream` to write out 3 bits, then 2 bits, all the bits are eventually written, but you cannot know when they're precisely written during the program execution. For example, if all output is done in 8-bit chunks, and you write out 61 bits, 3 extra bits will be written to fill the last buffer. Therefore, at the end of your file you may have "extra" bits.

Consequently, when you read bits you cannot simply read until there are no bits left. It is tempting to write code to read in bits like this: 

	while(true) { 
		int bit = input.readBits(1); //read one bit
		if(bit == -1) break; 	//done reading
		 ...
	}

But this is not what you should do, or it will create problems with your uncompressed files differing from the original. To avoid any issues, you will use a psuedo_EOF chracter whose Huffman encoding is written to the compressed file. When you uncompress the file, your code will stop when it reaches this character. 


###Note:

You are given the interface `IHuffConstants`. Please use the constants provided, for example, if you want to read in an 8-bit chunk, instead of using the int 8 use `BITS_PER_WORD`.