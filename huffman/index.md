---
layout: page
title: "Huffman"
assignment: "huffman"
---

Welcome to Huffman coding, your final programming assignment of the semester.  Your task for this programming assignment will be to implement a fully functional Huffman coding suite equipped with methods to both compress and decompress files.  Additionally for extra credit, you may opt to additionally implement the Burrows-Wheeler/Move-To-Front transformations which substantially increases the effectiveness of the Huffman Algorithm.  This assignment specification/guide should be sufficient to walk you through both Huffman coding and Burrows-Wheeler step-by-step.  However, if you run into any difficulties or are having trouble understanding exactly how Huffman coding works, then feel free to check out the [Wikipedia article](https://en.wikipedia.org/wiki/Huffman_coding) on Huffman coding, or, as always, the teaching staff will be available both at helper hours and on piazza to assist you as well.

The printer friendly version can be accessed from the navigation bar or [here](printer-friendly.html).

To begin, you'll need the user interface code available through snarf or below; you should have these files.

- [BitInputStream.java](code/BitInputStream.html) - Reads bits from file system
- [BitOutputStream.java](code/BitOutputputStream.html) - Writes bits to file system
- [HuffException.java](code/HuffException.html) - Flags errors specific to the Huffman algorithm
- [HuffNode.java](code/HuffNode.html) - Used for building Huffman trees
- [HuffMain.java](code/HuffMain.html) - Launches the user interface
- [HuffViewer.java](code/HuffViewer.html) - Contains the user interface code
- [Processor.java](code/Processor.html) - Interface that all compression algorithms must implement (compress and decompress)
- [TestHuff.java](code/TestHuff.html) - Simple JUnit tests to check for correctness

If you're having trouble getting the snarfed code to run, try removing your JRE library and adding it again.  To do that, right click on the project, select build path, and click configure build path.  Go to the libraries tab and select the JRE System Library (may also just be called jdk...) and then remove it.  Next click add library, JRE System Library, and then use the workspace default.  If this does not work, post on piazza and a member of the teaching staff will help you get your code running.

Additionally, you should have a data folder which contains three standard compression corpi that are commonly used to test the effectiveness of compression algorithms.  Both the calgary and canterbury corpi offer a cash prize for every byte of improvement over the previous record.  Unfortunately, standard Huffman coding won't quite be enough to claim any sort of prize.  More importantly than the corpi, you've also been given two text files (kjv10.txt and melville.txt) along with a binary file (lena.tif) as well as compressed versions of these files using our implementation of the Huffman algorithm.  You should use these files to test your own implementation by comparing your own compressed files to these using the compare tab of the user interface or the JUnit tests.  In order to receive full credit, you will need to be able to both compress the original files into an exact match of our compressed files and also decompress our compressed files into an exact match of the original files.  For debugging purposes, the JUnit tests check the header, body, and pseudo-EOF sections separately for compression.  If your header section is incorrect, this will likely cause the body and pseudo-EOF tests to fail as well, so use these tests incrementally.  That is, make sure that your header is correct before trying to test the body since the latter is dependent on the former.  As a way to circumvent this if you're really struggling with the header or body, the tests only depend on the length of the sections so you could just write empty bits to fill up the expected length of the header or body section.  This way the body sections of your output and the solution will line up in the JUnit tester so that the tests run correctly.  As always, remember that these criteria alone will not guarantee full credit, but merely suggest that you're on the right track.

You only need to implement and submit a single file for Huffman: `HuffProcessor.java`.  In order to get full credit, you should thoroughly read and reference this assignment write-up and follow these steps.

1. Create the class `HuffProcessor` which should implement `Processor`.
2. Complete the compress method
    1. Count the character frequencies
    2. Build a Huffman tree using `HuffNode`s and a `PriorityQueue`
    3. Extract the codes from the tree
    4. Write the `HUFF_NUMBER`
    5. Write the header using a pre-order traversal of the tree
    6. Write the body of the file using the extracted codes
    7. Write the `PSEUDO_EOF`
3. Complete the decompress method
    1. Check for the `HUFF_NUMBER`
    2. Re-buid the Huffman tree from the header
    3. Traverse the tree bit-by-bit until the `PSEUDO_EOF` is found
    4. Write out a character each time a leaf node is encountered
4. Answer the analysis quesions
5. Submit!

Please note that unlike some previous semesters this is NOT a partner project so please work alone and submit your own work.
