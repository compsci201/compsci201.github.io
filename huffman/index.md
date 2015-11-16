---
layout: page
title: "Huffman"
assignment: "huffman"
---

Welcome to Huffman coding, your final programming assignment of the semester.  Your task for this programming assignment will be to implement a fully functional Huffman coding suite equipped with methods to both compress and decompress files.  Additionally for extra credit, you may opt to additionally implement the Burrows-Wheeler/Move-To-Front transformations which substantially increases the effectiveness of the Huffman Algorithm.  This assignment specification/guide should be sufficient to walk you through both Huffman coding and Burrows-Wheeler step-by-step.  However, if you run into any difficulties or are having trouble understanding exactly how Huffman coding works, then feel free to check out the [Wikipedia article](https://en.wikipedia.org/wiki/Huffman_coding) on Huffman coding, or, as always, the teaching staff will be available both at helper hours and on piazza to assist you as well.

To begin, you'll need the user interface code available through snarf or below; you should have these files.

- [BitInputStream.java](https://cs.duke.edu/courses/compsci201/fall15/snarf/huffman/BitInputStream.java) - Reads bits from file system
- [BitOutputStream.java](https://cs.duke.edu/courses/compsci201/fall15/snarf/huffman/BitOutputStream.java) - Writes bits to file system
- [HuffException.java](https://cs.duke.edu/courses/compsci201/fall15/snarf/huffman/HuffException.java) - Flags errors specific to the Huffman algorithm
- [HuffNode.java](https://cs.duke.edu/courses/compsci201/fall15/snarf/huffman/HuffNode.java) - Used for building Huffman trees
- [HuffMain.java](https://cs.duke.edu/courses/compsci201/fall15/snarf/huffman/HuffMain.java) - Launches the user interface
- [HuffViewer.java](https://cs.duke.edu/courses/compsci201/fall15/snarf/huffman/HuffViewer.java) - Contains the user interface code
- [Processor.java](https://cs.duke.edu/courses/compsci201/fall15/snarf/huffman/Processor.java) - Interface that all compression algorithms must implement (compress and decompress)

Additionally, you should have a [data](https://cs.duke.edu/courses/compsci201/fall15/snarf/huffman/data) folder which contains three standard compression corpi that are commonly used to test the effectiveness of compression algorithms.  Both the calgary and canterbury corpi offer a cash prize for every byte of improvement over the previous record.  Unfortunately, standard Huffman coding won't quite be enough to claim any sort of prize.  More importantly than the corpi, you've also been given two text files (kjv10.txt and melville.txt) along with a binary file (lena.tif) as well as compressed versions of these files using our implementation of the Huffman algorithm.  You should use these files to test your own implementation by comparing your own compressed files to these using the compare tab of the user interface.  In order to receive full credit, you will need to be able to both compress the original files into an exact match of our compressed files and also decompress our compressed files into an exact match of the original files.  Remember that these criteria alone will not guarantee full credit, but merely suggest that you're on the right track.

You only need to implement and submit a single file for Huffman: `HuffProcessor.java`.  It should implement `Processor`.  The rest of the assignment will be devoted to elaborating on how to fill in the two required methods to get full credit.  Please note that unlike some previous semesters this is NOT a partner project so please work alone and submit your own work.
