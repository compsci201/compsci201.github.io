---
layout: page
title: "Analysis"
assignment: "autocomplete"
---

In addition to submitting your completed classes, you should benchmark them. Then, answer the following questions. Use data wherever possible to justify your answers, and keep explanations brief but accurate:

<ol> <li> What is the order of growth (big-Oh) of the number of compares
(in the worst case) that each of the operations in the Autocomplete data
type make, as a function of the number of terms N, the number of matching
terms M, and k, the number of matches returned by <tt>topKMatches</tt> for
BinarySearchAutocomplete?  <li>How does the runtime of topKMatches() vary
with k, assuming a fixed prefix and set of terms? Provide answers for
BruteAutocomplete, BinarySearchAutocomplete and TrieAutocomplete. Justify
your answer, with both data and algorithmic analysis. </li> <li>Look at the
methods topMatch and topKMatches in BruteAutocomplete and
BinarySearchAutocomplete and compare both their theoretical and empirical
runtimes. Is BinarySearchAutocomplete always guaranteed to perform better
than BruteAutocomplete? Justify your answer.</li> <li>For all three of the
Autocompletor implementations, how does increasing the size of the source
and increasing the size of the prefix argument affect the runtime of
topMatch and topKMatches? (Tip: Benchmark each implementation using
fourletterwords.txt, which has all four-letter combinations from aaaa to
zzzz, and fourletterwordshalf.txt, which has all four-letter word
combinations from aaaa to mzzz. These datasets provide a very clean
distribution of words and an exact 1-to-2 ratio of words in source files.)
</li> </ol>
