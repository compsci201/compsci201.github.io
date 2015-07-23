---
layout: page
title: "Markov"
assignment: "markov"
---

##Markov - Overview
---

<img src="img/whatwouldisay_example.png" alt="Splice">

######Markov text generated at [what-would-i-say](http:\\www.what-would-i-say.com) using the Duke Facebook page as a training source

Welcome to the Markov assignment. In this assignment, you will be using the Markovian Text Generation algorithm to generate random text modelled after some source. In doing so, you will learn how data structures like maps can make code more efficient, and get experience writing and implementing your own class in Java.

[Here](/markov/printer-friendly) is a printer friendly version of this assignment.

The code for this assignment is available through Snarf (using [Ambient](https://www.cs.duke.edu/csed/ambient/)).

###Acknowledgements

[Joe Zachary](https://www.cs.utah.edu/~zachary/) developed this nifty assignment in 2003 and [Kevin Wayne](https://www.cs.princeton.edu/~wayne/contact/) and [Robert Sedgewick](https://www.cs.princeton.edu/~rs/) have been using pseudo-random text generation as the basis for [an assignment](http://www.cs.princeton.edu/courses/archive/fall08/cos226/assignments/model.html) since 2005 and possibly earlier. [Owen Astrachan](https://www.cs.duke.edu/~ola/) created the original Duke version.

###Introduction
An order-k Markov model uses strings of k-letters to predict text, these are called kgrams.

An order-2 Markov model uses two-character strings or bigrams to calculate probabilities in generating random letters. For example suppose that in some text that we’re using for generating random letters using an order-2 Markov model, the bigram “th” is followed 50 times by the letter ‘e’, 20 times by the letter ‘a’, and 30 times by the letter ‘o’, because the sequences “the”, “tha” and “tho” occur 50, 20, and 30 times, respectively while there are no other occurrences of “th” in the text we’re modeling. 

Now suppose that we want to generate random text. We generate the bigram “th” and based on this we must generate the next random character using the order-2 model. The next letter will be an ‘e’ with a probability of 0.5 (50/100); will be an ‘a’ with probability 0.2 (20/100); and will be an ‘o’ with probability 0.3 (30/100). If ‘e’ is chosen, then the next bigram used to calculate random letters will be “he” since the last part of the old bigram is combined with the new letter to create the next bigram used in the Markov process.	

###Assignment Overview
You will have four primary tasks for this assignment. 

<ol>
<li>Make the provided class MarkovModel faster (by writing a new model MapMarkovModel, not changing the existing model)</li>
<li>Implement the WordNgram class.</li>
<li>Use the WordNgram class to create a new, word-based Markov Model. </li>
<li>Perform empirical analysis on the different implementations. </li>
</ol>


After you snarf the assignment run MarkovMain, the brute-force Markov generator. Using the GUI, go to File -> Open File and select a data file to serve as the training text. There is a data directory provided when you snarf containing several files you can use as training texts when developing your program.