---
layout: page
title: "Extra Credit (Burrows-Wheeler)"
assignment: "huffman"
---

##Introduction
To receive credit for doing this additional part of the assignment you must write a new class called BWTProcessor (that stands for Burrows-Wheeler Transformation) in the which implements `Processor` like `HuffProcessor`.  You must be able to compress files to a greater degree than just the `HuffProcessor` alone, and you must be able to decompress those files back into their original state such that verify runs correctly.  In order to use your `BWTProcessor`, change the value of `PROCESSOR` in `HuffMain` to `BWTProcessor`.  Be warned, although the premise behind Burrows-Wheeler transformations is simple enough, implementing the algorithm in code may be challenging.  If you struggled with the regular Huffman assignment, it is unlikely that you will develop a working Burrows-Wheeler processor.  Debugging will prove to be even more challenging than the standard Huffman assignment and the teaching staff will likely be so overloaded with regular Huffmans that it may be difficult to get helped (also we have not all actually implemented our own `BWTProcessor` so some nuanced questions may be very hard to answer).  Lastly, the amount of credit you'll actually get may not exactly equate to the amount of effort you put in; Burrows-Wheeler is less about extra credit and more about the challenge.  Despite all of this, I really don't mean to discourage you from attempting Burrows-Wheeler.  It is an enjoyable assignment, and you may find it incredibly rewarding to implement a compression algorithm that is significantly more efficient than the rest of your classmates' without sacrificing extreme amounts of run time.

Reference the [Burrows-Wheeler](http://en.wikipedia.org/wiki/BurrowsâWheeler_transform) and [Move-to-Front](http://en.wikipedia.org/wiki/Move-to-front_transform) Wikipedia pages as needed to gain a better understanding of the algorithm.  You will actually implement the decompress method described [here](http://michael.dipperstein.com/bwt/) rather than what you'll find on the Wikipedia page.

##Compression
If you're still reading at this point, congratulations.  The next few sections will detail exactly how the Burrows-Wheeler transformation works and why it improves the compressibility of files.  You will actually also have to implement a Move-To-Front transformation as well in order to see the benefits of Burrows-Wheeler in practice.  It's the combination of these two transformations that makes Huffman coding extremely effective.  Basically, the Burrows-Wheeler transformation rearranges the existing letters so that similar letters or recurring groups of letters appear next to each other.  This alone will do nothing to improve Huffman coding since the frequency of all of the letters is exactly the same as before the transformation.  However, the Move-To-Front transformation takes care of this by skewing the distribution of letters to heavily favor the first few characters.  At this point, the Huffman coding algorithm takes over and is able to save a much larger amount of space since it can assign very short bit codes to the frequent characters.

First, Burrows-Wheeler.  A Burrows-Wheeler transformation doesn't actually change the characters in a `String`.  Rather, it just rearranges them in a way that is both useful and reversible.  The first step is to create a list of every possible rotation of your `String` (i.e. abc, bca, and cab).  Next, sort all of the rotations alphabetically.  You'll need to record the index of the original `String` in the sorted list so that you can undo the transform later.  The final output of the transformation is the final letter of each row, starting from the top.  Check out this example from Wikipedia.

![](https://d262ilb51hltx0.cloudfront.net/max/800/1*h3Ce41lYSGqiTzU6h3dLTg.png)

Next, you take the output of the Burrows-Wheeler transformation and apply a Move-To-Front transformation.  The core idea is that you have a morphing alphabet.  When a character occurs, it gets moved to the first slot of the alphabet.  Then, if it occurs again, you record the new position of the character within the alphabet.  Transforming a `String` that has many repeating characters (such as the output of a Burrows-Wheeler transformation) will result in a higher frequency of characters at the beginning of the alphabet.  Huffman will be much more able to compress anything with heavily skewed character distributions since it can save bits more often than it loses bits.  Compare the compressed Huffman output of the file "abababab" to "abcdefgh" if you find this hard to believe.  The first file is heavily skewed while the second is evenly distributed.

Let's walk through the whole process with "go go gophers".  First apply the Burrows-Wheeler transformation.

| Original      | Rotations      | Sorted         | Output        |
|---------------|----------------|----------------|---------------|
|               | go go gophers  | _ go gophersgo |               |
|               | o go gophersg  | _ gophersgo go |               |
|               | _ go gophersgo | ersgo go goph  |               |
|               | go gophersgo_  | go go gophers  |               |
|               | o gophersgo g  | go gophersgo_  |               |
|               | _ gophersgo go | gophersgo go_  |               |
| go go gophers | gophersgo go_  | hersgo go gop  | oohs  pgggoer |
|               | ophersgo go g  | o go gophersg  |               |
|               | phersgo go go  | o gophersgo g  |               |
|               | hersgo go gop  | ophersgo go g  |               |
|               | ersgo go goph  | phersgo go go  |               |
|               | rsgo go gophe  | rsgo go gophe  |               |
|               | sgo go gopher  | sgo go gopher  |               |

Now we apply the Move-toFront transformation.

| Input | Alphabet                    | Output  |
|-------|-----------------------------|---------|
| o     | abcdefghijklmnopqrstuvwxyz_ | 14 -> o |
| o     | oabcdefghijklmnpqrstuvwxyz_ | 0  -> a |
| h     | oabcdefghijklmnpqrstuvwxyz_ | 8  -> i |
| s     | hoabcdefgijklmnpqrstuvwxyz_ | 18 -> s |
| _     | shoabcdefgijklmnpqrtuvwxyz_ | 26 -> _ |
| _     | _shoabcdefgijklmnpqrtuvwxyz | 0  -> a |
| p     | _shoabcdefgijklmnpqrtuvwxyz | 17 -> r |
| g     | p_shoabcdefgijklmnqrtuvwxyz | 11 -> l |
| g     | gp_shoabcdefijklmnqrtuvwxyz | 0  -> a |
| g     | gp_shoabcdefijklmnqrtuvwxyz | 0  -> a |
| o     | gp_shoabcdefijklmnqrtuvwxyz | 5  -> f |
| e     | ogp_shabcdefijklmnqrtuvwxyz | 10 -> k |
| r     | eogp_shabcdfijklmnqrtuvwxyz | 19 -> t |

Now you're ready to try to implement Burrows-Wheeler with code.  Create a new class called `BWTProcessor` which implements `HuffProcessor`.  Just like regular Huffman compression, before you do anything else you need to write out a `Huff_NUMBER` which tells decompress that it is able to process the following bits.  You don't want to use the same `HUFF_NUMBER` as `HuffProcessor` however.  By using a different `HUFF_NUMBER`, the two different implementations will be easily discernable from the file alone so that the right decompressor is used on each file.  Add the line `public static final int BWT_NUMBER = HUFF_NUMBER | 2;` in `Processor` right beneath the line where `HUFF_NUMBER` is declared.  Return to `BWTProcessor` and write the `BWT_NUMBER` to the file using the provided `BitOutputStream` just like you did for regular compression.

Now for one of the tricky parts of Burrows-Wheeler.  Since both of the transformations change a `String` rather than a file, you have to read the `BitInputStream` in ahead of time and fabricate the `String` character by character.  This alone would not be challenging except that for longer files, it will take an impossible amount of time and space to apply the transformation to the entire file all at once.  Instead, you want to read the file in blocks to make the algorithm faster.  You will consider this trade-off further, but for now take note that in general bigger blocks compress more but take longer.  A block size of 16,384 will likely do on your computer.  Any larger and you run into trouble with heap space, although you could change the run configuration to use more RAM.  Look up how to do this on the internet if you're curious.  For testing during development, it may be advantageous to decrease the block size so that you can compress and decompress files rapidly; I'd recommend using 1024 or even 512/256 depending on your computer.

To read in the blocks, you'll need two nested loops: one outer while loop which runs as long as the current byte isn't -1 and an inner for loop which runs from 0 to your block size as long as the current byte does not equal -1.  You need both checks for the inner loop since the last block will most likely be smaller than all of the others.  You can just &amp;&amp; the two statements together.  Inside of the for loop, all you need to do is construct the `String` for the current block.  The simple solution from here would be to fill an `array` with every permutation of the `String` and then use `Arrays.sort()` to put them in order.  However, this runs in O(nlogn) time where n is the block size.  If we want to be able to use large block sizes without sacrificing speed, then we need to use a more efficient sorting algorithm.  In this case, we can implement a simple hash table and use a radix sort.  Although you've been told that `HashMap`s and `HashSet`s do not correctly order elements, this is not entirely true.  With a well designed hash function, these data structures can actually deliver both sorting and constant time adds and removes.  Using `int`s as keys will accomplish this perfectly.

So rather than filling an `array`, instantiate a `HashMap` of `Integer` to `List<String>`.  You'll still need a for loop to generate each break point in the `String` to create each permutation.  On each iteration, you need to compute the `Integer` key and create the `String` value.  To compute the key, take the first four characters from the `String` and treat their binary value as one 32-bit `int` instead of 4 8-bit characters.  There are a number of ways to do this; I would recommend using the shift operator (`<<`) or repeatedly multiplying by 2^8 and adding.  The `String` value is the substring from index i to the end and then 0 to i.  Make sure that a `List` exists for the calculated key before adding.  It might be a good time now to note that it will be substantially faster and less memory consuming to use a `StringBuilder` instead of a `String`.  This is because `String`s are immutable in Java and thus are copied on every substring call.  This copy call is O(n) and will make your runtime O(n^2) overall even with the improved radix sort.  Additionally, if you update the `StringBuilder` correctly each time, you should be able to use the first four characters each time and avoid dealing with wrap-around.  Note that the `HashMap` should still take `String`s to avoid pass by reference modifications.

Once the `Map` is full, you'll need to make sure the `List` at each key is sorted.  To do this, loop through the keySet of the `Map` and call `Collections.sort()` on each value.  To minimize code reuse and optimize runtime, you can also search for the original `String` and compile the output of the transformation with this loop as well.  To do this, add an internal for loop which iterates over each `List` value after it has been sorted.  For each `String`, append the last character to a `transformedString` and check if it equals the original input.  If it does, then call `out.writeBits()` using `BITS_PER_INT` and the index of current `String`.  Keep track of a counter to find the index.

Next up is the Move-To-Front transformation.  The hardest part about the Move-To-Front transformation is keeping track of the alphabet.  There are a number of options including an `array`, `ArrayList`, and `LinkedList`.  Of these, the `LinkedList` is easily the fastest since repeatedly adding to the front of an `ArrayList` is linear time as opposed to constant time for a `LinkedList`.  You can just use the built-in Java `LinkedList` with add, remove, etc. already defined.  No matter which you choose, `Character` will be the easiest object to work with.  Once you've initialized your data structure (remember to add every ASCII character not just letters) and set up an output, you need to loop through each character in the Burrows-Wheeler output.  Find the index of that character in the data structure, and add it to the output (cast as a `char` of course).  You also need to remove the character and replace it at the 0th index of the data structure.  In this case, the output should be a `ByteArrayOutputStream`.  You use a `ByteArrayOutputStream` the same way you would use a `BitOutputStream`; call `write(byte b)` for each character.  The `ByteArrayOutputStream` will create a `byte[]` which you'll be able to use to create a `BitInputStream` for Huffman compression.  Once this is complete, you have completed the two transformations and are ready to compress the current block.

You're going to use the compress method that you already wrote for `HuffProcessor` so you need to create the input/output streams for the parameters.  The `BitOutputStream` should be the same as the one given as a parameter, so just pass that one along.  However, you do need a new `BitInputStream`.  Fortunately this is fairly simple.  You should use the following code:

```java
BitInputStream temp = new BitInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
new HuffProcessor().compress(temp, out);
temp.close();
```

This will compress the `String` that you just created the same way the `HuffProcessor` compresses a normal file.  You have now completed compress.  You can try running it to make sure that it works, but until you finish decompress it's unlikely you will be able to draw any conclusive results.

##Decompression
In order to receive the extra credit for Burrows-Wheeler, you must also be able to decompress files.  The first step, as always, is to check for the `BWT_NUMBER` at the beginning of the file.  If it's not there, throw a `HuffException` like before.  Also, make sure that your `readBits()` call in your while loop that reads the body of the compressed file comes after the check if the current node is a leaf or before current is updated.  This order doesn't matter for regular decompression, but since you're dealing with multiple adjacent blocks, even just one additional or missing bit will throw the algorithm off and result in an error.

Similar to compression, you need a while loop which will continue to read bits until there aren't any left since you don't know how many blocks make up the compressed file.  Inside the while loop, the first thing you need to do for each block is read the 32 bits you wrote that tell you which index in the table the original `String` is located; you'll need this later.  Next, undo the Huffman compression.  You can do this using the following code.

```java
BitOutputStream temp = new BitOutputStream(new ByteArrayOutputStream());
new HuffProcessor().decompress(in, temp);
temp.flush();
String block = "";
for (byte b : temp.toByteArray()) {
	block += (char) b;
}
temp.close();
```

You can use the same input stream since the decompression algorithm you already wrote will automatically stop at the pseudo-EOF.  Since the output of this decompress operation is an unprocessed `String` however, you don't want to output it immediately to final output file, hence the creation of a temporary `byte[]` and a new `BitOutputStream`.  Now you need to undo the Move-To-Front transformation.  You will manipulate the alphabet in the same way as the compress algorithm; every time you use a character, move it to the front.  The only difference is that instead of searching for the character and recording the index, you will go to the index and record the character for decompress.</p>

The only challenging part of Burrows-Wheeler remaining is undoing the Burrows-Wheeler transformation.  Since the actual algorithm is entirely unintuitive, first consider an example before implementing the algorithm with code.  The goal of this algorithm is to undo the Burrows-Wheeler transformation by recreating the table without ever creating a physical manifestation of the table in code.  You won't have to worry about the table at all once you move on to implementation, but for now imagine what the table might look like/pretend that you do indeed have the table ahead of time.  For this example, use the simple `String` "banana" as the source.  The table should look something like the following.

| Original | Rotations | Sorted | Output |
|----------|-----------|--------|--------|
|          | banana    | abanan |        |
|          | ananab    | anaban |        |
| banana   | nanaba    | ananab | nnbaaa |
|          | anaban    | banana |        |
|          | nabana    | nabana |        |
|          | abanan    | nanaba |        |

Take note that the original input is at index 3.  Using only index 3 and the output, you can easily deduce that the last letter of the input must be the 3rd (0 based) letter in the key nnbaaa.  Thus, so far you know that the input looks something like -----a based on the length of the `String` and the index of the original `String` in the table.  The next step is to identify the second to last character.  Hopefully, you can quickly agree that the corresponding table entry for the next to last letter begins with the last letter (based on rotations).  This substantially narrows our search to just the first three entries.  Furthermore, since the characters are alphabetically sorted, the choices will always be consecutive entries.  By parsing the key ahead of time, you can also calculate the number of letters in the `String` that lexigraphically come before each character in the `String`.  Because of this, you can index directly to the first potential entry in the group of possible (and consecutive) entries.  In this case, since 'a' occurs before 'b' and 'n', the first possible entry is the 0th one.  The next step is non-trivial and confusing; please take time to assure yourself of its actuality.  Because of the combination of rotations and sorting, the order in which a's appear in the last column is the same order as the first column.  Look at the characters around the a's first to establish that it is true in this case first.  Now, this fact must be true since the sorted order of the entries that begin with 'a' depend on the characters directly following the specific 'a' all the way to the end of the `String`.  Once rotated, the same sequence of characters is now at the beginning of the `String`.  Since the a's do not affect the sorted order either at the beginning or the end, the order is based solely on the other characters which are the same in both cases.  Thus, for each like character, the order in which the instances of the character occur in the last column exactly matches the order of the first column.  Again, by pre-processing the key to compile the number of like characters that occur before each character, you can access this information as you loop through the `String`.  The combination of the two numbers you found exactly indexes the table entry which is rotated one character to the right.  You know the final character of this entry from the key and also that it must be the next to last character.  Using the same information with the new character, you can find the third to last character and so on, until you complete the entire original `String`.  Here is an entire walk through of "banana" in case you're still wary.

Table A:
| Character | Like Chars Before |
|-----------|------------------:|
| n         | 0                 |
| n         | 1                 |
| b         | 0                 |
| a         | 0                 |
| a         | 1                 |
| a         | 2                 |

Table B:
| Character | Any Chars Before |
|-----------|-----------------:|
| a         | 0                |
| b         | 3                |
| n         | 4                |

key: nnbaaa
| Current String | Table A | Table B | Next Index | Next Char |
|----------------|--------:|--------:|-----------:|-----------|
| -----a         | 0       | 0       | 0          | n         |
| ----na         | 0       | 4       | 4          | a         |
| ---ana         | 1       | 0       | 1          | n         |
| --nana         | 1       | 4       | 5          | a         |
| -anana         | 2       | 0       | 2          | b         |
| banana         |         |         |            |           |

Remember that the first character comes from the index you stored in the compressed file.  There are two steps to implementing this algorithm: pre-processing and reconstructing.  Within pre-processing there are two steps.  The first is to loop through each character in the `String` and determine how many like characters have occurred before the current character so far.  Use a `Map` of `Character`s to `Integer`s to keep track of the counts of each character.  At each index in the `String`, record a value in an `int[]` equal to the number of the same character that have already occurred, a value which you get from the `Map`.  Be sure to update the `Map` with the new addition.  This gives you the first of two tables that you need for reconstructing.  Because you recorded the frequency of each character with a `Map` for the first table, constructing the second table should be particularly easy.  Simple set up an `int` to store the accumulated frequencies as you go and loop through each entry in the `Map`.  For each key value pair in the `Map`, put a new entry in a separate `Map` with the character as a key and the accumulated frequency as a value.  Be sure to increment the accumulated frequency by the value of the first `Map` before moving on.</p>

To undo the Burrows-Wheeler transformation, all you need are the two tables you just created and the key for you block.  Add the first character to the de-transformed `String` using the index recorded in the file.  The next index is made up of two components, one from each table.  The first comes from the `array` at the index you used to add the most recent character.  The second part comes from the `Map` of accumulated frequencies.  You can get the value out via the character at the most recent index.  Update your index variable to be the sum of these two parts and write the character at that index to the output using `out.writeBits()`.  Repeat this process until you have reconstructed the entire original input.  Remember that this will take one less cycle than the length of the input since you already have the first character.

Congratulations, you have now completed both Huffman and Burrows-Wheeler.  Write up your analysis and claim your extra-credit; it's well earned.

##Analysis
1. Compare the compressibility of both text and image files using your `BWTProcessor` and your `HuffProcessor`.
2. Analyze how the amount of compression and runtime change when the block size is increased.
