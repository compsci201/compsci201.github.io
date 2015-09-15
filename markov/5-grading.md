---
layout: page
title: "Grading"
assignment: "markov"
---

When autograding MapMarkovModel and WordMarkovModel, we will call initialize() from your model on a Scanner, and then test the output by calling process() and then calling/using the output from makeNgram(). We will never call the methods out of this order, so you do not have to program them to handle edge cases that would occur when calling them out of order. As long as you follow the way MarkovModel's methods handle calls, you should easily pass most tests.

For transparency's sake, below is a list of aspects of your code the automated tests will check. Your code will still be checked by a TA, and passing all these tests does not guarantee a perfect grade. 

<li>Do MapMarkovModel and WordMarkovModel's makeNgram generate the correct Ngram? (We test if you get the exact Ngram our solution gets, but if you don't we have a bunch of other tests to see how close/correct yours is)</li>
<li>Do MapMarkovModel and WordMarkovModel's makeNgram handle the end of file correctly? </li>
<li>Do MapMarkovModel and WordMarkovModel's makeNgram take less time to generate text if k and the training text take the same time? </li>
<li>Do MapMarkovModel and WordMarkovModel handle a change in training text correctly?</li>
<li>Do MapMarkovModel and WordMarkovModel use static variables unnecessarily?</li>
<li>Do WordNgram's equals and hashCode methods work as expected?</li>
<li>Does WordNgram's equals handle edge cases?</li>

In addition, if your code throws unexpected errors or takes too long to run the tests, you will be considered as failing the tests.
