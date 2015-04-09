---
layout: page
title: "Huffman Overview"
assignment: "huffman"

---


Welcome to Huffman, your final programming assignment. Understanding what you have to do and how it works is key to successfully coding this assignment. Make sure you read the entire assignment before you start to code. 

###Background: 

This coding assignment was developed in the mid 90s for use at Duke, and has many adoptions in similar courses elsewhere. [Huff-Old-Program](http://www.cs.duke.edu/csed/poop/huff/info/ "Here") is a complete description of an older version of the assignment.

There are many techniques used to compress digital data. This assignment covers the Huffman coding algorithm. Several algorithms for data compression have been patented, e.g., to use the MP3 codec to compress audio (which uses Huffman encoding as one of the steps in the algorithm). 

Huffman coding was invented by David Huffman while he was a graduate student at MIT in 1950 when given the option of a term paper or a final exam. [Article](http://www.huffmancoding.com/my-uncle/scientific-american "For details see this 1991 Scientific American Article"). 

In this assignment you'll implement a complete program to compress and uncompress data using Huffman coding. Refer to notes from class and previous readings. 


##Brief Overview: 

You will write a class that implements the **IHuffProcessor** interface, which requires you to write three methods, thus we will break the assignment up into details for each of these methods, as they will be somewhat lengthy. 

###Compression:

More detailed instructions will be provided, but basically you create a Huffman tree to derive per-character encodings, then you write bits based on these encodings. The **Huff** main program has a GUI front-end whose menu offers three choices: count characters, compress, uncompress (and quit as a fourth choice). You can't compress until you can count/create a tree, so make sure counting/tree-creation/encoding works.

Because the compression scheme involves reading and writing in a bits-at-a-time manner as opposed to a char-at-a-time manner, the program can be hard to debug. In order to facilitate the design/code/debug cycle, you should take care to develop the program in an incremental fashion. If you try to write the whole program at once, it will be difficult to get a completely working program. 

###Decompression: 

To uncompress a file your program previously compressed you'll need to read header information from the compressed file your program creates. The header information is data that you'll use to recreate the Huffman tree that was originally used to compress the data. This header is key, to uncompress you need the same tree you used to compress. After recreating the tree your code will read one-bit-at-a-time to uncompress the data and recreate the original file that was compressed. 
Basically you read the header information to recreate the tree, then do a tree-walk one bit at a time to find the characters stored in the leaves of the Huffman tree. Each time you find a leaf you print the value there. This process recreates the original, uncompressed file.


###Analysis 

You should run the program **HuffMark** which will read the files in a directly and compress it to another file in the same directory with a '.hf' suffix. You may want to modify this benchmarking program to print more data than it currently does, and to run it on both the calgary directory which represents the **Calgary Corpus**, a standard compression suite of files for empirical analysis, you can see this reference for comparisons on the Calgary Corpus and on the waterloo directory which is a collection of .tiff images used in some compression benchmarking. You can, of course, run on other data/collections. Be sure to check whether the file you compress and uncompress is the same as the original.


