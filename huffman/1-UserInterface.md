---
layout: page
title: "User Interface"
assignment: "huffman"
---


In order to make your implementation of Huffman easier to test, run, and benchmark, a simple user interface has been provided for you to use.  There are three tabs, each performing a unique and important task.

1. Compress: The compress tab allows you to use the compress method you wrote in `HuffProcessor`.  Add individual files or folders by clicking the "Add File" button.  If you add a folder, the code will compress each sub-file/folder individually.  You may also click on folders in the display to show their contents individually.  You can also remove files either by clicking the "x" in each row or with the "Clear All" button at the bottom.  The output of running the compress tab is a series of compressed files sharing the same name as the original with a ".hf" extension.

2. Decompress: The decompress tab allows you to test the decompress method you wrote in `HuffProcessor`.  Functionally, it closely mirrors the compress tab.  Decompressed files remove the ".hf" extension and instead add the ".dehf" extension.

3. Verify: The verify tab compares two files bit by bit to ensure that they are identical.  Verify will tell you where your files differ if at all.  In addition to running the junit tests, running verify with your compressed files and the provided compressed files is the best way to test your code.  Note again that passing the junit tests and successfully running verify do not guarantee full correctness, so be sure to check a number of files or add mroe edge cases to the junit tests.

On a final note, although you can switch away from a tab and immediately run something else, it is recommended that you wait until each process is finished since running multiple things at once will likely cause errors (and headaches).
