---
layout: page
title: "Analysis"
assignment: "huffman"

---



You should run the program `HuffMark` which will read the files in a directory and compress it to another file in the same directory with a '.hf' suffix. You may want to modify this benchmarking program to print more data than it currently does, and to run it on both the calgary directory which represents the **Calgary Corpus**, a standard compression suite of files for empirical analysis, you can see this reference for comparisons on the Calgary Corpus and on the waterloo directory which is a collection of .tiff images used in some compression benchmarking. You can, of course, run your code on other data/collections. Be sure to check whether the file you compress and uncompress is the same as the original using `Diff`.

When you compress a file, the suffix .hf will be added. If you uncompress the .hf file, the suffix .unhf will be added. When you run `Diff` you must select two files, you should select the original file you compressed, and the .unhf version of that file. Then `Diff` will tell you if the files differ anywhere, if your program works properly the original file and the .unhf file should be the same. 


In your analysis you should use empirical data and logical reasoning to answer the following questions: 

Which compresses more, binary or text files? (Use the calgary files and the waterloo files to compare)

How much additional compression do you get by compressing an already compressed file? When does this become ineffective?

Can a file be designed that should compress a lot? When is it no longer worthwhile to keep compressing that file?

