---
layout: page
title: "Submitting"
assignment: "markov"
---

The following classes must be submitted in order to be graded:
<li> MapMarkovModel.java </li>
<li> WordMarkovModel.java </li>
<li> WordNgram.java </li>

Make sure when you submit, all of the following are present and functional:

<code>
Class: MapMarkovModel<br>
&nbsp;Constructor: MapMarkovModel()<br>
&nbsp;Method: public void initialize(Scanner)<br>
&nbsp;Method: protected String makeNGram(int, int)<br>
&nbsp;Method: protected int readChars(Scanner)<br>
&nbsp;Method: public void process(Object)<br>
Class: WordNgram<br>
&nbsp;Constructor: WordNgram(String[], int, int)<br>
&nbsp;Constructor: WordNgram(String[])<br>
&nbsp;Method: public boolean equals(Object)<br>
&nbsp;Method: public String toString()<br>
&nbsp;Method: public int hashCode()<br>
&nbsp;Method: public int compareTo(WordNgram)<br>
Class: WordMarkovModel<br>
&nbsp;Constructor: WordMarkovModel()<br>
&nbsp;Method: public void initialize(Scanner)<br>
&nbsp;Method: protected String makeNGram(int, int)<br>
&nbsp;Method: protected int readChars(Scanner)<br>
&nbsp;Method: public void process(Object)<br>
</code>

You also must submit your README in order to receive any credit.

You may create any new classes required for the above classes to compile but you must submit those as well.

The following classes should be considered "read-only" and not changed - if you submit any of them, the grader will replace them with the unmodified version you snarfed. Thus, you are free to modify them for testing, but the classes you submit should not depend on those modifications in order to be compile/have the correct return values:
<li> AbstractModel.java </li>
<li> IModel.java </li>
<li> IView.java </li>
<li> MarkovMain.java </li>
<li> MarkovModel.java </li>
<li> SimpleViewer.java </li>
