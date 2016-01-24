---
layout: page
title: "Analysis and Grading"
assignment: "huffman"
---

Answer the following questions  You do not need to write several pages, but
you should provide thoughtful answers backed by data and analysis.

1. Benchmark your code on the given *calgary* and *waterloo*
directories. Refer to  [this reference for comparisons on the Calgary Corpus](http://www.data-compression.info/Comparisons/index.html). Develop a
hypothesis for how the *compression rate* and *time elapsed* vary with the
length of the file (in bits), the size of the alphabet (i.e., the number of
leaves in the tree), and any other factors. 
2. Do text files or binary (image) files compress more (compare the calgary (text) and waterloo (image) folders)?  Explain why.
3. How much additional compression can you achieve by compressing an already compressed file?

## Grading
This assignment is worth 50 points. 

- **65% Correctness:** for your implementation of
HuffProcessor. We will run your code on a number of files and
checked the results of the compression and decompression.
- **15% Analysis:** for your README, data from
your  AutocompleteBenchmark. answers to the
questions,  and description of the tradeoffs.
- **20% Engineering:** for your the structure and style of
your HuffProcessor implementation. Is your code inefficient?

