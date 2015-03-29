---
layout: page
title: "Lexicon Benchmarking"
assignment: "boggle"

---


You're given a class **LexiconBenchmark** you can use to analyze the empirical performance of your implementations. This class facilitates evaluating the efficiency of different implementations as well as correctness. Confidence in an implementation's correctness is increased if it returns the same  results as other implementations. 

If you completed the extra credit, you should benchmark your **CompressedTrieLexicon** class by determining how many nodes are stored/saved compared to the non-compressed trie and by determining how much more time the new, compressed version takes. Two methods in the **TrieLexicon** class for counting nodes are provided, *nodeCount* and *oneWayCount*. These may prove useful in benchmarking your class. 

In your Analysis file you nede to report information about which Lexicon implementation is the fastest. You should compare all the lexicons (including the ones we provided for you) and report on their relative times- give more information than "this one was fastest." You should have at least three lexicons to test, four if you completed the extra credit. You should explain the methods you used to determine this and report on experiments you run, giving times. 