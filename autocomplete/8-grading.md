---
layout: page
title: "Grading Criteria"
assignment: "autocomplete"
---

For transparency's sake, below is a list of aspects of your code the automated tests will check. Your code will still be checked by a TA, and passing all these tests does not guarantee a perfect grade. 

This may appear to be a large number of tests, but if you follow the guidelines set in the method headers and this writeup, you should already be passing most of, if not all of these tests:

<li>Do Term's compareTo and Comparator inner classes sort correctly?</li>
<li>Does Term's constructor initialize correctly and throw exceptions when appropriate?</li>
<li>Does Term.PrefixOrder(r).compare() take O(r) time?</li>
<li>Do BinarySearchAutocomplete and TrieAutocomplete's topMatch and topKMatches return the correct results?</li>
<li>Do BinarySearchAutocomplete and TrieAutocomplete's topMatch simply call topKMatches?</li>
<li>Can BinarySearchAutocomplete and TrieAutocomplete's topMatch and topKMatches handle an empty string argument?</li>
<li>Do BinarySearchAutocomplete and TrieAutocomplete's topKMatches handle a k = 0 argument?</li>
<li>Do BinarySearchAutocomplete and TrieAutocomplete handle words with irregular character values correctly?</li>
<li>Do BinarySearchAutocomplete and TrieAutocomplete modify the values they are constructed from when they shouldn't? </li>
<li>Do BinarySearchAutocomplete and TrieAutocomplete use static variables unnecessarily? </li>
<li>Do BinarySearchAutocomplete and TrieAutocomplete's constructors, topMatch, and topKMatches throw NullPointerExceptions if an argument is null? </li>
<li>Do BinarySearchAutocomplete and TrieAutocomplete modify the values they are constructed from? </li>
<li>Do BinarySearchAutocomplete's firstIndexOf and lastIndexOf return the correct results?</li>
<li>Do BinarySearchAutocomplete's firstIndexOf and lastIndexOf handle empty arrays?</li>
<li>Do BinarySearchAutocomplete's firstIndexOf and lastIndexOf use the correct number of compares?</li>
<li>Do BinarySearchAutocomplete's firstIndexOf and lastIndexOf use compare instead of equals?</li>
<li>Do BinarySearchAutocomplete's firstIndexOf and lastIndexOf change the value of their arguments when they shoudn't?</li>
<li>Does TrieAutocomplete's add generate the trie correctly?</li>
<li>Does TrieAutocomplete's add handle calls to the same word twice correctly?</li>
<li>Do TrieAutocomplete's topMatch and topKMatches avoid exploring more nodes than needed?</li>