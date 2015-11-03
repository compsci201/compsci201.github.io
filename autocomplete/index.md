---
layout: page
title: "Autocomplete"
assignment: "autocomplete"
---

## Autocomplete - Overview
---

Welcome to the Autocomplete assignment. In this assignment, you will be implementing the autocomplete algorithm, which is discussed at length [here](/autocomplete/1-autocomplete-algorithm.html), using binary search and trie traversal. In doing so, you will see some of the advantages of using tries (or more generally, trees/graphs) and sorted arrays over general arrays.

As always, before starting be sure to snarf the assignment via Ambient so you can read along. You can alternatively download the .jar file [here](/autocomplete/src/Autocomplete.jar).

You can also view/download the individual classes:

[AutocompleteGUI.java](/autocomplete/code/AutocompleteGUI.html)

[AutocompleteMain.java](/autocomplete/code/AutocompleteMain.html)

[Autocompletor.java](/autocomplete/code/Autocompletor.html)

[AutocompletorBenchmark.java](/autocomplete/code/AutocompletorBenchmark.html)

[BinarySearchAutocomplete.java](/autocomplete/code/BinarySearchAutocomplete.html)

[BruteAutocomplete.java](/autocomplete/code/BruteAutocomplete.html)

[In.java](/autocomplete/code/In.html)

[Node.java](/autocomplete/code/Node.html)

[Term.java](/autocomplete/code/Term.html)

[TrieAutocomplete.java](/autocomplete/code/TrieAutocomplete.html)

[Here](/autocomplete/printer-friendly.html) is a printer friendly version of this assignment.

###Introduction

In this assignment, you will implement <em>autocomplete</em> for a given set of <i>N</i>
terms, where a <em>term</em> is a query string and an associated nonnegative
<em>weight</em>. That is, given a prefix, find all queries that start with the given
prefix, in descending order of weight.w

For example, if I am given the query "com" I might suggest terms like
"computer", "communication" or "combine". For this assignment, our terms
have predetermined fixed weights assigned to them, and we will return the
highest-weighted terms among those terms that share have the query as their
prefix.

In this assignment, you will:

<ol>
<li> Complete a datatype <code><a href="code/Term.html">Term</a></code> that represents an autocomplete
term: a query string and an associated real weight.
<li> Write a class, <code><a
href="code/BinarySearchAutocomplete.html">BinarySearchAutocomplete</a></code>
that impletements autocomplete
functionality  by using <em>binary search</em>
to find the all query strings that start with a given prefix; and sort the
matching terms in descending order by weight. 
<li> Write a class, <code><a
href="code/TrieAutocomplete.html">TrieAutocomplete</a></code> to implement
Autcocomplete functionality using a Trie data structure.
</ol>

When you snarf this project, you will receive the following already completed classes:

<ul>
<li><code><a href="code/Autocompletor.html">Autocompletor</a></code> – the interface which you will be writing implementations of.</li>
<li><code><a href="code/AutocompleteMain.html">AutocompleteMain</a></code> – the class to launch the GUI from. When testing your implementations of autocomplete, you will need to change the String AUTOCOMPLETOR_CLASS_NAME’s value.</li>
<li><code><a href="code/AutocompletorBenchmark.html">AutocompletorBenchmark</a></code> – once you have written an implementation of Autocompletor, this class will tell you how quickly it runs.</li>
<li><code><a href="code/BruteAutocomplete.html">BruteAutocomplete</a></code> - A completed (albeit slow) implementation of Autocompletor. </li>
<li><code><a href="code/Node.html">Node</a></code> – a node class for use in TrieAutocomplete. Nothing needs to be modified here, but definitely read through this class before starting TrieAutocomplete.</li>
<li><code><a href="code/AutocompleteGUI.html">AutocompleteGUI</a></code> –
the GUI for this project. You can ignore this class.</li>
</ul>

You are responsible for completing the following classes:

<ul><
li><code><a href="code/Term.html">Term</a></code> – a class used to encapsulate individual search terms and their weights, which includes several methods of comparing terms. You need to complete these class in order for BruteAutocomplete to be usable with AutocompleteMain.</li>
<li><code><a href="code/BinarySearchAutocomplete.html">BinarySearchAutocomplete</a></code> – an implementation of Autocompletor which performs the autocomplete algorithm using binary search.</li>
<li><code><a href="code/TrieAutocomplete.html">TrieAutocomplete</a></code> – an implementation of Autocompletor which performs the autocomplete algorithm using trie exploration.</li></ul>
</ul>

In addition, after completing these classes, you will use following methods
on your <code><a href="code/Autocompletor.html">Autocompletor</a></code> implementations:

<ul>
<li> <code><a href="code/TestTerm.html">TestTerm</a></code>,
<code><a
href="code/TestBinarySearchAutocomplete.html">TestBinarySearchAutocomplete</a></code>,
and <code><a href="code/TestTrieAutocomplete.html">TestTrieAutocomplete</a></code> to test the
<em>correctness</em>; and 
<li> <code><a href="code/AutocompleteBenchmark.html">AutocompleteBenchmark</a></code> to analyze the efficiency.
</ul>

The incomplete methods come with headers detailing what the inputs and
expected output are, as well as implementation details such as edge cases
and exceptions to be throw. You could probably complete the assignment
using only these headers, but you should still read through this
writeup. It will make completing the assignment easier, and you might avoid
some simple mistakes in doing so.

