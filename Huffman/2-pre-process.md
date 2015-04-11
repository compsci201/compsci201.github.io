---
layout: page
title: "PreprocessCompress"
assignment: "huffman"

---

Before you can start writing bits out to a file you must create the codes that will replace each character. 

You are given a `TreeNode` that implements `Comparable`. That means that you can use this class when storing weighted character(s) objects in a `PriorityQueue` to make a Huffman Tree. 

You are given an `InputStream` parameter in this method, you will want to use this to set up your `BitInputStream`. The following code shows how to do this: 

	 public int preprocessCompress(InputStream in) throws IOException {
		BitInputStream bis = new BitInputStream(in); 
		... 
	}




You must: 

###Count Frequencies
Count how many times every bit-sequence occurs in a file. These counts are used to build weighted nodes that will be leaves in the Huffman tree. Although this writeup sometimes refers to "characters", you should use *int*variables/values in your code rather than *char*. Note that the method for reading bits-at-a-time from a `BitInputStream` returns an *int*, so using int variables makes sense. Any wording in this write-up that uses the word character means an 8-bit chunk and this chunk-size could (in theory) change. Do not use any variables of type *byte* in your program. Use only *int* variables.

###Build the Tree
Use these counts to build the Huffman tree. First create one node per character, weighted with the number of times the character occurs, and insert each node into a `PriorityQueue`. You now have a lot of single 'TreeNodes', a forest. You must combine these nodes to make the tree. To do this, choose two minimal nodes, join these nodes together as children of a newly created node, and insert the newly created node into the `PriorityQueue` (see notes from class). The new node is weighted with the sum of the two minimal nodes taken from the priority queue. Continue this process until only one node is left in the priority queue. This is the root of the Huffman tree, store this. 

###Create map of Encodings
Create a table or map of 8-bit chunks (represented as an *int* value) to Huffman-codings. The map of chunk-codings is formed by traversing the path from the root of the Huffman tree to each leaf. Each root-to-leaf path creates a chunk-coding for the value stored in the leaf. When going left in the tree append a zero to the path; when going right append a one. The map has the 8-bit int chunks as keys and the corresponding Huffman encoding String (you can't make this an *int* because you want to preserve the binary and length of the encoding) as the value associated with the key. The map can be an array of the appropriate size (roughly 256, but be careful of PSEUDO_EOF) or you can use a Map implementation. An array is the simplest approach for this part of the huff/compress process, using a Map is not necessary, but it's fine to use one.

###Return an **int**
The return value of `preprocessCompress` should be an *int*, as specified by the method signature.  Rather than returning 0 or some other arbitrary value, consider evaluating the difference in bits between the compressed and uncompressed text, since this information will be useful for determining whether or not to proceed with compression.


