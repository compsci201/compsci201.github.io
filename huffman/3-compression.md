---
layout: page
title: "Compress"
assignment: "huffman"

---

In this method you will use the information (counts and encodings) you created in `preprocessCompress` and actually write bits to a new, compressed file. You will need to do a few things: 


###Forcing Compression:
You should only proceed with compression if the compressed file will have fewer bits than the uncompressed file.  For many shorter files, compression will not save bits but will still be useful for debugging.  In order to compress these files, you must force compression using the *boolean* **force** taken in as a parameter of the compress methood.  You can set this boolean to true by going to options -> force compression in the GUI.  If **force** is false and compression won't save bits, then you want to throw an error and provide an informative message for the user. Throw an exception like this: 

	throw new IOException("Compression does not save any space");


###Magic Number:
Write a magic number at the beginning of the compressed file. Use the `IHuffConstants.MAGIC_NUMBER` to do this. When you uncompress, you'll read this number to ensure you're reading a file your program compressed. Your program should be able to uncompress files it creates.

For example, if my program only works with files I've compressed, not other standard formats, I would have the following code: 
	
	//write out the magic number
     out.writeBits(BITS_PER_INT, MAGIC_NUMBER);
     

###Header Information:

For this assignment, you will make two headers, a frequency header and a tree header. To differentiate between those two, you need to write a number at the beginning of the file to signify this: hence, the two magic numbers. These are 'STORE_COUNTS' and 'STORE_TREE', which are constants defined in the IHuffConstants interface.

Implement these in two different classes - you should write a SimpleHuffProcessor class that uses count headers, and a TreeHuffProcessor class that uses tree headers.

**You will need to implement both SCF and the tree header.**

####STORE_COUNTS- Standard Count Format (SCF)
Using SCF is likely the simplest way to store the encoded information in your header. To write this type of header, write out the counts for each of the  `ALPH_SIZE` (256) characters as a 32-bit int value. Thus, there should be one 32-bit value for each 8-bit chunk. You do not need to write a count for pseudo_EOF since you are guaranteed that its frequency will be 1 for any file.

In my non-saving-space code using SCF, my header is written by the following code. Note that `BITS_PER_INT` is 32 in Java.

	for(int k=0; k < ALPH_SIZE; k++){
          out.writeBits(BITS_PER_INT, myCounts[k]);
      }

####STORE_TREE
Another way to write the header is by writing out the information of the tree you created during pre-process. This header format omits data about characters that do not appear in the file and thus is space saving relative to the `STORE_COUNTS` header style.

One way to write the tree information would be to use a 0 or 1 bit to differentiate between internal nodes and leaves. The leaves must store character values (in the general case, using 9-bits because of the pseudo-EOF character). Standard Tree Format in the Huff program/suite uses a pre-order traversal, a single zero-bit for internal nodes, a single one-bit for a leaf, and nine bits for the value stored in a leaf.

For example, the sequence of 0's and 1's below represents the following tree (if you write the 0's and 1's the spaces wouldn't appear, the spaces are only to make the bits more readable to humans).

	0 0 1 001100001 1 000100000 1 001110100

####![Example of A Tree](http://www.cs.duke.edu/courses/compsci201/current/assign/huff/images/smalltree.jpg)

The first 0 indicates a non-leaf, the second 0 is the left child of the root, a non-leaf. The next 1 is a leaf, it is followed by 9 bits that represent 97 (001100001 is 97 in binary), the Unicode/ASCII code for 'a'. Then there's a 1 for the right child of the left child of the root, it stores 32 (000100000 is 32 in binary), the ASCII value of a space. The next 1 indicates the right child of the root is a leaf, it stores the Unicode/ASCII value for a 't' which is 116 (001110100 is 116 in binary).

Your program should write these bits using a standard pre-order traversal. You can then read them by reading a bit, and recursively reading left/right subtrees if the bit is a zero.


###File Encoding:
 Now you can actually encode the file. Write the bits needed to encode each character of the input file. For example, if the coding for 'a' is "01011" then your code will have to write 5 bits, in the order 0, 1, 0, 1, 1 every time the program is compressing/encoding the chunk 'a'. You'll re-read the file being compressed, look up each chunk/character's encoding and print a 0 or 1 bit for each '0' or '1' character in the encoding.


###Pseudo_EOF:
Finally, write out the encoding of the pseudo_EOF file character. This way, your uncompression algorithm will know when to stop reading bits since files tend to have a small number of buffer bits at the end which don't actually express any information. You will need to explicitly write out the bits for the psuedo_EOF so that they will be able to be recognized when you uncompress the file. To make sure that the entire bit-encoding of the pseudo_EOF gets written out to the file, you also need to flush the `BitOutputStream` since it only writes 8 bits at a time. 

