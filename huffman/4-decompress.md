---
layout: page
title: "Decompress"
assignment: "huffman"

---

Now that you've compressed your file, let's make sure we can decompress it. In the method *uncompress*, you will reconstruct the original file from your compressed one; then, the method will return how many bits it wrote.

### Reading the header:

To decompress a file, you'll need to read its header information, which lets you recreate the Huffman tree used to compress the file. You should first read the magic number, which tells you what kind of header information is stored. If the file has a magic number which your program does not recognize, you should notify the user. Example code:

	int magic = in.readBits(BITS_PER_INT);
    if (magic != MAGIC_NUMBER){
        throw new IOException("magic number not right");
    }

You will have to edit this slightly, as there are multiple possible magic numbers. Otherwise, depending on which magic number is present, you choose to read either of the following header formats. 

#### Standard Tree Format

We'll simply recreate the myCounts array from our preprocessing step. This should be simple: just read *BITS_PER_INT* bits into your myCounts array, *ALPH_SIZE* times:

	for(int k = 0; k < ALPH_SIZE; k++){
		myCounts[k] = in.readBits(BITS_PER_INT);
	}

Then recreate the tree; this should by identical to your preprocessing method. In fact, if you took advantage of modularity and wrote it in another method, you should just reuse that.

#### Tree Header

This is a little more tricky. Essentially, you're recreating the tree directly from a preorder traversal.

The idea is to read one bit at a time and reconstruct the tree using recursion:

Your recursive method should recurse on the bit input stream, returning a node. When done correctly, it will return the complete Huffman tree when called on the bit input stream at the beginning of the header. If the bit is a 0, then you've reached a non-leaf node; first set the current node's myLeft as the recursion on the bit input stream, then set the current node's myRight equal to another recursion on the bit input stream; then return the node. If it's a 1, then you've reached a leaf node and you can read the next 9 bits as the value of the current node. If your header was written correctly, this should work out perfectly so that your recursion ends right as the header ends.


### Reading the data:

Now, simply read bits one at a time, traversing the tree you just built.

Start at the root; again, 0 means left, 1 means right. When you get to a leaf, write the value stored there and then restart at the root. Keep track of how many bits you're writing: at the end, you'll return this value.

### Stopping at PSEUDO_EOF:

The point of PSEUDO_EOF is to know where to stop, since there may be extra buffer bits written to the file at the end. So when you decode something that means PSEUDO_EOF, stop-- your program is done!

If you reach the end of the file but didn't hit PSEUDO_EOF, then you've done something wrong: you should never run out of bits if you implemented it correctly. This should throw an IOException; see below.


###Some pseudocode you should use

Here's some pseudocode that implements reading the data and stopping at PSEUDO_EOF:

	int bits;
	while (true)
	{
	   if ((bits = input.readbits(1)) == -1)
	   {
	       System.err.println("should not happen! trouble reading bits");
	   }
	   else
	   { 
	       // use the zero/one value of the bit read
	       // to traverse Huffman coding tree
	       // if a leaf is reached, decode the character and print UNLESS
	       // the character is pseudo-EOF, then decompression done

	       if ( (bits & 1) == 0) // read a 0, go left  in tree
	       else                  // read a 1, go right in tree

	       if (at leaf-node in tree)
	       {
	           if (leaf-node stores pseudo-eof char) 
	               break;   // out of loop
	           else
	               write character stored in leaf-node
	       }
	   }
	}
