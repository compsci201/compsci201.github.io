---
layout: page
title: "The Algorithm"
assignment: "huffman"
---

Compression algorithms work by reformatting data to use fewer bits.  By default, text files use the 8-bit (or byte) codes specified by ASCII (American Standard Code for Information Interchange).  While this may not seem like such a waste of space, 8 bits allows for 256 distinct characters to be expressed.  This is far more than most text files will ever use (if you don't believe it, try to come up with 256 different characters that you might find in a regular file in the English language).  If you look at some of the characters which ASCII includes, you'll notice that the standard has included a host of mathematical symbols in addition to group of control characters.  Even if you argue that the mathematical notation may actually occur quite frequently, many of the control characters are relics of historical coding practices/necessities that are no longer relevant.  Some applications even forbid the use of control characters.  Thus, in many cases, by ignoring the characters that don't actually occur in the file and reducing the number of unique codes needed, shorter bit codes can be conceived.  For example, consider the file with only four unique characters, the characters could be uniquely identified with only 2 bits apiece as opposed to 8 using 00, 01, 10, and 11 to represent each one.  A naive approach may do something like this and just trim the size of the character library to be only 128 characters or maybe even just 64, saving 1 bit and 2 bits per character respectively.  Although mildly effective in some cases, more complicated algorithms can capitalize on the statistical data of character distributions to extend compressibility even further.  Huffman is one of these entropy encoding algorithms.  Some others go even further to incorporate both the distribution of single characters and the distribution of character pairs, such as Burrows-Wheeler/Move-To-Front.

Entropy encoding algorithms work mainly by utilizing variable length codes combined with character frequencies.  Characters that occur a lot get shorter codes while lesser used characters get longer codes.  In some cases, longer codes may even exceed the 8-bit length of the standard ASCII codes.  While this may seem counter-intuitive, it actually allows for more bits to be saved on higher frequency characters.  Consider the following using the `String` "go go gophers" as an example.

Option A: use standard ASCII codes
| Character | Frequency | Bits | Total |
|-----------|----------:|-----:|------:|
| g         | 3         | 8    | 24    |
| o         | 3         | 8    | 24    |
| _         | 2         | 8    | 16    |
| p         | 1         | 8    | 8     |
| h         | 1         | 8    | 8     |
| e         | 1         | 8    | 8     |
| r         | 1         | 8    | 8     |
| s         | 1         | 8    | 8     |
| Total     |           |      | 104   |

Option B: use alternative codes based on character frequencies
| Character | Frequency | Bits | Total |
|-----------|----------:|-----:|------:|
| g         | 3         | 7    | 21    |
| o         | 3         | 7    | 21    |
| _         | 2         | 7    | 14    |
| p         | 1         | 8    | 8     |
| h         | 1         | 8    | 8     |
| e         | 1         | 9    | 9     |
| r         | 1         | 9    | 9     |
| s         | 1         | 9    | 9     |
| Total     |           |      | 99    |

As you can see, by trading 1 bit from the more frequent characters to the less frequent characters, the alternative solution created some codes that were longer than 8 bits but still saved bits overall.  The goal of Huffman and other entropy encoding algorithms is to quickly generate these alternative codes in a predictable and guaranteed way.  As it turns out, binary trees turn out to be excellent data structures for storing characters and generating character codes.  Provided that every internal-node in the tree has two children, binary trees provide a perfect way to translate a sequence of binary digits into characters.  Each binary digit directs you how to traverse down the tree. A 0 indicates that the current node should be updated to the left sub-child while a 1 indicates the right sub-child.  Each leaf-node represents a character, and since each leaf-node has a unique path from the root-node, each character has a unique code.  This should remind you of tries; in fact Huffman trees are sometimes also referred to as Huffman tries.  Variable length codes are even really easy to construct using a binary tree, just imagine a really unbalanced tree (but remember that each internal node still always needs two children).  Here's a sample Huffman binary tree of "go go gophers" and the corresponding codes.

![](https://cs.duke.edu/csed/poop/huff/info/gohuff.jpg)

| Character | Code |
|-----------|------|
| g         | 10   |
| o         | 11   |
| _         | 001  |
| p         | 0100 |
| h         | 0101 |
| e         | 0110 |
| r         | 0111 |
| s         | 000  |

The important thing about binary trees for Huffman coding is the *prefix property*.  When every internal-node contains exactly two children, each leaf-node contains a character, and the code generation conventions outlined above are followed, two related properties are fulfilled.

1. The code for any character is guaranteed to not be the prefix of another code.  For example, a Huffman tree cannot assign the codes 01 and 010 to two distinct characters.  This prevents confusion when decompressing (e.g. X has code 01 and Y has code 0101, the input 0101 could be interpreted as XX or Y).

2. The set of all codes generated by a Huffman tree contains prefixes for every possible binary input.  This means that no matter what sequence of binary inputs are fed into a Huffman tree, there is always a valid character interpretation.

When these two properties are combined, it means that for any given Huffman tree, there is a single unique character output for every binary input and vice-versa.  This implies that there is an unambiguous way to compress a file without loss of accuracy and to decompress a file and end up with the original uncompressed version (both of which seem like pretty important criteria for data compression).

There have been multiple proposed algorithms for constructing one of these magical Huffman trees, but until Huffman himself devised the algorithm you'll be implementing in a moment, no algorithms guaranteed that the resulting Huffman tree saved the most possible bits.  Huffman was able to do this by constructing the tree from the bottom up.  The basic idea is that you take the two nodes with the lowest frequency and combine them with an internal-parent-node.  The new parent-node takes the combined frequencies of its two sub-children as its frequency and is put back with all of the other nodes.  This process is repeated until there is only one node left, which is the root-node.  This diagram from Wikipedia clearly shows how the algorithm works.

![](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/HuffmanCodeAlg.png/330px-HuffmanCodeAlg.png)

Now that you've seen an example, try creating a Huffman tree for yourself.  Use "go go gophers" as the source for the tree so you can ckeck your answer with the image above.  WARNING: when you're done, your tree may not exactly match the picture.  This is ok; your characters don't have to be in the same spots, and the structure of the tree may be slightly different.  That said, you should end up with something that has essentially the same shape as the example, perhaps with some left and right sub-children flipped.  If you aren't certain that your tree is correct, try again, selecting nodes and orientations to specifically emulate the example.

Before you begin to actually implement Huffman, here are some interesting dilemmas to consider.  First, compression isn't free.  Your decompression algorithm needs to be able to reconstruct the tree you used to compress the file in order to decompress it.  You'll have to include some information at the beginning of each of your compressed files which instructs the decompress method on how to recreate the specific tree that it needs.  There are a number of ways to encode this information, but they all require a similar amount of space.  This additional information or header is why many shorter files can't be compressed; the additional length of the header outweighs the number of bits saved by compressing the body of the file.  Second, since the decompress algorithm requires very specific formatting in order to work, inputting a file that was never compressed in the first place could yield some interesting results.  In order to prevent decompress from trying to parse nonsensical information, you'll need to include some sort of flag or indicator at the beginning of your compressed files as well to communicate to decompress that it is all right to proceed.  There is a constant provided for you in `Processor` that you can use for this purpose called `HUFF_NUMBER`.  It is highly unlikely that `Huff_NUMBER` will naturally occur at the beginning of the file, so this is a nearly 100% effective approach.  Finally, since all of the Huffman bit codes are of variable length, there is no surefire mathematical way to know when you've reached the end of the file and need to stop reading bits.  Although the file does eventually end, there may be some number of extra bits at the end since files are written in bytes, not bits, and so every file must have a length with a multiple of 8 bits.  This could result in additional characters being added to the end of your decompressed file which did not exist in the original uncompressed version.  To combat this issue, you should include an end of file character at the end of each of your compressed files.  The pseudo-EOF will have its own Huffman code generated from the tree.  The decompression algorithm won't write this character to your decompressed file, but rather, once it encounters the unique pseudo-EOF code, it will trigger your loop to stop reading bits and exit.
