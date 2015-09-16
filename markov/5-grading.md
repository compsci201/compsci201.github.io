---
layout: page
title: "Grading"
assignment: "markov"
---
## Testing
When autograding MapMarkovModel and WordMarkovModel, we will call initialize() from your model on a Scanner, and then test the output by calling process() and then calling/using the output from makeNgram(). We will never call the methods out of this order, so you do not have to program them to handle edge cases that would occur when calling them out of order. As long as you follow the way MarkovModel's methods handle calls, you should easily pass most tests.

For transparency's sake, below is a list of aspects of your code the automated tests will check. Your code will still be checked by a TA, and passing all these tests does not guarantee a perfect grade. 

<ol>
<li>Do MapMarkovModel and WordMarkovModel's makeNgram generate the correct Ngram? (We test if you get the exact Ngram our solution gets, but if you don't we have a bunch of other tests to see how close/correct yours is)</li>
<li>Do MapMarkovModel and WordMarkovModel's makeNgram handle the end of file correctly? </li>
<li>Do MapMarkovModel and WordMarkovModel's makeNgram take less time to generate text if k and the training text take the same time? </li>
<li>Do MapMarkovModel and WordMarkovModel handle a change in training text correctly?</li>
<li>Do MapMarkovModel and WordMarkovModel use static variables unnecessarily?</li>
<li>Do WordNgram's compareTo, equals, and hashCode methods work as expected?</li>
<li>Do WordNgram's compareTo and equals handle edge cases? (Like passing a non-WordNgram to equals, or comparing two WordNgrams of different lengths)</li>
</ol>
In addition, if your code throws unexpected errors or takes too long to run the tests, you will be considered as failing the tests.

## Grading
This assignment is worth 35 points. 

<ul>
<li> <strong>67.5% Correctness:</strong> for your implementation of MapMarkovModel, WordMarkovModel, and WordNgram and passing the tests described above.</li>
<li> <strong>20% Analysis:</strong> for your README and description of the tradeoffs.
<li> <strong>12.5% Engineering:</strong> for your the structure and style of your code and the implementation of the WordNgram class and its associated model. Do you appropriately use decomposition and inheritance to avoid duplicate code? </li>
</ul>

