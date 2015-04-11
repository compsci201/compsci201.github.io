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


###Note:

You are given the interface `IHuffConstants`. Please use the constants provided, for example, if you want to read in an 8-bit chunk, instead of using the int 8 use `BITS_PER_WORD`.
