---
layout: page
title: "Submitting"
assignment: "autocomplete"
---

The following classes must be submitted in order to be graded:
<li> BinarySearchAutocomplete.java </li>
<li> Term.java </li>
<li> TrieAutocomplete.java </li>

Make sure when you submit, all of the following are present and functional:

<pre><tt>
Class: BinarySearchAutocomplete<br>
&nbsp;Constructor: BinarySearchAutocomplete(String[], double[])<br>
&nbsp;Method: public String topMatch(String)<br>
&nbsp;Method: public String[] topKMatches(String, int)<br>
&nbsp;Method: public static int firstIndexOf(Term[], Term, Comparator)<br>
&nbsp;Method: public static int lastIndexOf(Term[], Term, Comparator)<br>
Class: TrieAutocomplete<br>
&nbsp;Constructor: TrieAutocomplete(String[], double[])<br>
&nbsp;Method: private void add(String, double)<br>
&nbsp;Method: public String topMatch(String)<br>
&nbsp;Method: public String[] topKMatches(String, int)<br>
&nbsp;Field: protected Node myRoot<br>
Class: Term<br>
&nbsp;Class: Term$WeightOrder<br>
 &nbsp;&nbsp;Method: public int compare(Term, Term)<br>
 &nbsp;Class: Term$ReverseWeightOrder<br>
 &nbsp;&nbsp;Method: public int compare(Term, Term)<br>
 &nbsp;Class: Term$PrefixOrder<br>
 &nbsp;&nbsp;Constructor: Term$PrefixOrder(int)<br>
 &nbsp;&nbsp;Method: public int compare(Term, Term)<br>
 &nbsp;&nbsp;Field: private final int r<br>
 &nbsp;Constructor: Term(String, double)<br>
 &nbsp;Method: public String toString()<br>
 &nbsp;Method: public int compareTo(Term)<br>
</tt> </pre>


You also must submit your README in order to receive any credit.

If you create any new classes which are required for the above classes to compile, you should submit those as well.

Any other classes we provide you should be considered "read-only" - if you submit them, the grader will replace them with the unmodified version you snarfed. Thus, you are free to modify them for testing, but the classes you submit should not depend on those modifications in order to be compile/have the correct return values:
