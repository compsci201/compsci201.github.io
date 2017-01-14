---
layout: page
title: "User Interface"
assignment: "huffman"
---


In order to make your implementation of Huffman easier to test, run, and benchmark, a simple user interface has been provided for you to use.  There are four tabs, each performing a unique and important task.

1. Compress: The compress tab allows you to use the compress method you wrote in `HuffProcessor`.  Add individual files by clicking the red "No File Chosen" text in the top left.  Once you have selected a file, you can either press the compress button to run your code or select a new file by clicking on the green text.  When you run your code, you should notice the progress bar in the top right slowly filling up.  If the bar turns green, then compression finished without any errors.  This does not mean that your code is correct, merely that it's not totally wrong.  If the bar becomes red instead, you encountered an error and need to begin debugging.  Upon completion, the user interface will also display some basic stats about the compression, namely the size of the file before and after, the percentage of space saved relative to the original file size, and how long the algorithm ran before finishing.  All of these will be useful later for the analysis.  The actual concrete output of running the compress tab is a compressed file sharing the same name as the original with a ".hf" extension.

2. Decompress: Comparably to the compress tab, decompress allows you to test your decompress method.  The user interface works in the same way.  The outputed file will remove the ".hf" extension and add ".dehf" in its place.

3. Compare: The compare tab compares two files bit by bit to ensure that they are identical.  Compare will tell you where your files differ if at all.  Running compare with your compressed files and the provided compressed files is the best way to test your code.  Note again that successfully running compare does not guarantee full correctness, so be sure to check a number of files to cover more edge cases.

4. Test: The final tab is the test tab which allows you to compress an entire folder of files all at once.  It works the same way that compress does, just on a list of files instead of just one.  This will be especially useful for the analysis section since it minimizes clicking.  There is an added user interface component here: the "test .hf files" checkbox.  This allows you to distinguish between compressing and recompressing when benchmarking.

On a final note, although you can switch away from a tab and immediately run something else, it is recommended that you wait until each process is finished since running multiple things at once will likely cause errors (and headaches).
