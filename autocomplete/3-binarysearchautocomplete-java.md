---
layout: page
title: "BinarySearchAutocomplete Overview/Methods"
assignment: "autocomplete"
---

<code><a
href="code/BinarySearchAutocomplete.html">BinarySearchAutocomplete</a></code>
implements the <code><a
href="code/Autocompletor.html">Autocompletor</a></code> interface, which
means it should, given a list of terms and weights for those terms, be able
to find the top match(es) for a prefix amongst those terms. To do this, you
will write binary search methods which can narrow the list of all terms
down to a list of terms matching the prefix. Once we have a list of
prefix-matching terms, finding the highest weight ones should be trivial.

Within this class, you should:
<ol>
<li>Write the binary search method firstIndexOf</li>
<li>Write the binary search method lastIndexOf</li>
<li>Write the interface-required method topMatch </li>
<li>Write the interface-required method topKMatches</li>
</ol>

###The Constructor

The constructor for this class is already written for you. However, you should still quickly read through it to understand what it does. Notice that the list of terms is initialized for you in lexicographic order. This means you should not need to sort it yourself - doing so will slow down your methods unnecessarily. Furthermore, you should maintain the sorted order - if one of your methods changes the array of Terms, or if methods fail because the first method call did not preserve sorted order for the
second, you might lose points.

###firstIndexOf and lastIndexOf
You should be comfortable with the binary search algorithm already. We will be implementing two more specific versions of it in the methods firstIndexOf and lastIndexOf. firstIndexOf and lastIndexOf are different from typical binary search algorithms in two ways: 
<ol>
<li>firstIndexOf and lastIndexOf should not check for exact equality, but rather for equality according to the Comparator argument. This is so that we can use PrefixOrder (which has a very loose definition of equality) in conjunction with binary search to narrow down the list of terms.</li>
<li>firstIndexOf should return the smallest index of any matching element, and lastIndexOf should return the largest index. This means that when you find a matching element, you cannot immediately return its index as there might be matching elements with a smaller/larger index. Instead, finding an element equal to the element being searched for simply halves your range of possible indices, just like finding an element not equal to the search element. More specifically, in firstIndexOf, the index of a matching element should be your new upper bound, and in lastIndexOf, the index of a matching element should be your new lower bound.</li>
</ol>

<strong>As a performance requirement, firstIndexOf and lastIndexOf should use at most 1 + &lceil;log<sub>2</sub><i>N</i>&rceil; compare calls for an N-element array</strong>. The methods should return -1 if no matching element exists, including the case where the input array is empty.

###topMatch and topKMatches

Once firstIndexOf and lastIndexOf are written, most of the work for topMatch and topKMatches is done. By using firstIndexOf and lastIndexOf and Term.PrefixOrder, you can easily find the indices of all terms starting with the prefix. Once you have narrowed down the list of terms to m terms starting with the prefix, finding the highest weight term(s) and returning
them should be trivial.

If there are <i>m</i> terms starting with the prefix, the best timing for finding a top match is O(log <i>n</i> + <i>m</i>), and the best timing for the top k matches is O(log <i>n</i> + <i>m</i> log <i>m</i>). You should be able to justify for yourself that your
methods run in these times.

One may notice that topMatch is simply a special case of topKMatches where k = 1 and be tempted to have topMatch simply call 
<pre><tt>  topKMatches(prefix,1)</tt></pre>

**Do not do this** – the optimal timing for topKMatches is worse than topMatch’s optimal timing, meaning calling topKMatches with k = 1 will never be as fast as properly implementing topMatch if written correctly. If your topMatch method simply calls topKMatches with k = 1 and returns the output for either Autocompletor implementation, you will lose points.

###After BinarySearchAutocomplete Is Written

You can test BinarySearchAutocomplete similar to how you tested Term - set <code>AUTOCOMPLETOR_CLASS_NAME</code> to <code>BINARY_SEARCH_AUTOCOMPLETE</code> from AutocompleteMain, and run it using words-333333.txt as the source and "auto" as the query. The results
should be the same as the ones shown on the [previous page](/autocomplete/2-term-java.html). In general, <code><a href="code/BruteAutocomplete.html">BruteAutocomplete</a></code> and <code><ahref="code/BinarySearchAutocomplete.html">BinarySearchAutocomplete</a></code> (and <code><a href="code/TrieAutocomplete.html">TrieAutocomplete</a></code>) should always produce the same output for <code>topMatch</code> and <code>topKMatches</code>.

Keep in mind that if your BruteAutocomplete works but BinarySearchAutocomplete does not, it may be a problem with Term instead of with BinarySearchAutocomplete - BruteAutocomplete only uses Term.WeightOrder, whereas BinarySearchAutocomplete could potentially use all of Term's Comparable/Comparators.
