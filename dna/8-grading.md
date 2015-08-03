---
layout: page
title: "Grading"
assignment: "dna"
---

For transparency's sake, below is a list of aspects of your code the automated tests will check. Your code will still be checked by a TA, and passing all these tests does not guarantee a perfect grade. 

<li>Does the size method return the correct value?</li>
<li>Does the size method take O(1)?</li>
<li>Does the toString method return the correct value?</li>
<li>Does the append method modify the LinkStrand object correctly?</li>
<li>Can the append method be called multiple times in a row?</li>
<li>Does the append method append SimpleStrand objects correctly?</li>
<li>Does the append method append LinkStrand objects correctly?</li>
<li>Does the append method append generic IDnaStrand objects (i.e. implementations which are not SimpleStrand or LinkStrand) correctly?</li>
<li>Does the append method take O(1)?</li>
<li>Does the initializeFrom method set up the LinkStrand object correctly?</li>
<li>Does the initializeFrom method set up the LinkStrand object correctly, if the LinkStrand has already been initialized?</li>
<li>Does the reverse method work for a single-node LinkStrand?</li>
<li>Does the reverse method work for a multiple-node LinkStrand?</li>
<li>Does the reverse method use memoization? (For extra credit)?</li>
<li>Does the cutAndSplice method return the correct value?</li>
<li>Does the cutAndSplice method throw an exception when called from a multi-node strand?</li>
<li>Does the cutAndSplice method take O(B)?</li>
<li>Does the nodeList method return the correct value?</li>
<li>Does the constructor with no arguments initialize LinkStrand correctly?</li>
<li>Does the constructor initialize the values in the LinkStrand correctly?</li>
<li>Does the getStats method return the correct value?</li>
<li>Does the strandInfo method return the correct value?</li>
<li>Does calling methods in a random order modify the LinkStrand object as expected?</li>
<li>Does LinkStrand use unnecessary static variables?</li>