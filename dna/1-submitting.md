---
layout: page
title: "Submitting"
assignment: "dna"
---

The following classes must be submitted in order to be graded:
<li> LinkStrand.java </li>

Make sure when you submit, all of the following are present and functional:

<code>
Class: LinkStrand<br>
&nbsp;Constructor: LinkStrand()<br>
&nbsp;Constructor: LinkStrand(String)<br>
&nbsp;Method: public String toString()<br>
&nbsp;Method: public IDnaStrand append(String)<br>
&nbsp;Method: public IDnaStrand append(IDnaStrand)<br>
&nbsp;Method: public long size()<br>
&nbsp;Method: public IDnaStrand reverse()<br>
&nbsp;Method: public ArrayList nodeList()<br>
&nbsp;Method: public IDnaStrand cutAndSplice(String, String)<br>
&nbsp;Method: public String getStats()<br>
&nbsp;Method: public String strandInfo()<br>
&nbsp;Method: public void initializeFrom(String)<br>
</code>


You also must submit your README in order to receive any credit.

If you create any new classes which are required for the above classes to compile, you should submit those as well.

The following classes should be considered "read-only" - if you submit them, the grader will replace them with the unmodified version you snarfed. Thus, you may modify them for testing, but the classes you submit should not depend on those modifications in order to be compiled/return the correct values:
<li> SimpleStrand.java </li>
<li> DNABenchmark.java </li>
<li> IDnaStrand.java </li>
<li> TestStrand.java </li>