---
layout: page
title: "Benchmarking Autocomplete"
assignment: "autocomplete"
---

The class AutocompleteBenchmark has been provided to you for timing your implementations of Autocompletor. To test a specific implementation, change the return value of the getInstance method to a new instance of the implementation you are testing.

The benchmark class will time all of the following, for a source file you choose through JFileChooser:
<li> The time it takes to initialize a new instance of the class.</li>
<li> If the class is TrieAutocomplete, the number of nodes in the initialized Trie </li>
<li> The time it takes to call topMatch and topKMatches() for varied k on: A blank string, a random word from the source, prefixes of that word, and a prefix not in the source </li>

You can, of course, modify the benchmark class to add more tests.

For timing method calls, the benchmark class runs until either 1000 trials or 5 seconds have passed (to try to minimize variation in the results) and reports the average. 

Even for the largest source files (fourletterwords.txt and words-333333.txt) BinarySearchAutocomplete and TrieAutocomplete should have some or most methods take times in the range of microseconds. This makes these methods' runtimes very susceptible to variation - be sure to consider this when interpreting results and writing your analysis.