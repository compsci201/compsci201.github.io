---
layout: page
title: "Autocomplete"
assignment: "autocomplete"
---

## Autocomplete - Overview
---

Welcome to the Autocomplete assignment. In this assignment, you will be implementing the autocomplete algorithm, which is discussed at length [here](/autocomplete/1-autocomplete-algorithm.html), using binary search and trie traversal. In doing so, you will see some of the advantages of using tries (or more generally, trees/graphs) and sorted arrays over general arrays.

As always, before starting be sure to snarf the assignment via Ambient so you can read along.

[Here](/autocomplete/printer-friendly.html) is a printer friendly version of this assignment.

###Introduction

In this assignment, we will be implementing the autocomplete algorithm, which should take a query String and give a suggestion or suggestions of the most likely  terms which complete the String. For example, if I am given the query "com" I suggest terms like "computer", "communication" or "combine". For this assignment, our terms have predetermined fixed weights assigned to them, and we will return the highest-weighted terms among those starting with the query.

When you snarf this project, you will receive the following already completed classes:

<li>Autocompletor – the interface which you will be writing implementations of.</li>
<li>AutocompleteMain – the class to launch the GUI from. When testing your implementations of autocomplete, you will need to change the String AUTOCOMPLETOR\_CLASS\_NAME’s value.</li>
<li>AutocompletorBenchmark – once you have written an implementation of Autocompletor, this class will tell you how quickly it runs.</li>
<li>BruteAutocomplete - A completed (albeit slow) implementation of Autocompletor. </li>
<li>Node – a node class for use in TrieAutocomplete. Nothing needs to be modified here, but definitely read through this class before starting TrieAutocomplete.</li>
<li>AutocompleteGUI – the GUI for this project. You can ignore this class.</li>
<li>In – a more advanced Scanner, used by AutocompleteGUI to read in data. You can ignore this class.</li>

You are responsible for completing the following classes:

<li>Term – a class used to encapsulate individual search terms and their weights, which includes several methods of comparing terms. You need to complete these class in order for BruteAutocomplete to be usable with AutocompleteMain.</li>
<li>BinarySearchAutocomplete – an implementation of Autocompletor which performs the autocomplete algorithm using binary search.</li>
<li>TrieAutocomplete – an implementation of Autocompletor which performs the autocomplete algorithm using trie exploration.</li>

In addition, after completing these classes, you will use AutocompleteBenchmark to analyze the efficiency of the Autocompletor implementations.

The incomplete methods should have headers detailing what the inputs and expected output are, as well as implementation details such as edge cases and exceptions to be throw. You could probably complete the assignment using only these headers, but you should still read through this writeup. It will make completing the assignment easier, and you might avoid some simple mistakes in doing so.

