---
layout: page
title: "Analysis"
assignment: "markov"
---

The analysis has two parts.

####Part A

1. Answer these questions in the provided README template:
How long does it take using the provided, brute force code to generate text if you vary the following? Why?

	<p>A. Order of Model (e.g. order-1 vs. order-5 vs. order-10) </p>

	<p>B. Length of training text (e.g., Alice’s Adventures in Wonderland (alice.txt) vs. The Scarlet Letter  (hawthorne.txt)) </p>

	<p>C. Number of characters generated (e.g. 100, 200, 400, 800, 1600 characters)  </p>

	Do empirical results match what you expect? How long do you think it will take to generate 1600 random characters using an order-5 Markov model when the The Complete Works of William Shakespeare is used as the training text — our online copy of this text contains roughly 5.5 million characters. Justify your answer — don’t test empirically, use reasoning.

2. Provide timings using your Map/Smart model for both creating the map and generating 200, 400, 800, and 1600 character random texts with an order-5 Model and alice.txt. Provide some explanation for the timings you observe.

3. Provide timings for the WordMarkovModel with different hashcode methods. Time the method you are given and compare the results that you achieve with the better hashcode method that you developed.


####Part B

The goal of the second part of the analysis is to analyze the performance of WordMarkovModel using a HashMap (and the hashCode function you wrote) and a TreeMap (and the compareTo function you wrote). The main difference between them should be their performance as the number of keys (i.e, WordNGrams as keys in your map) gets large. So set up a test with the lexicons we give you and a few of your own. Figure out how many keys each different lexicon generates (for a specific number sized n-gram). Then generate some text and see how long it takes. Graph the results. On one axis you’ll have the number of keys, on the other you’ll have the time it took to generate a constant number of words (you decide - choose something pretty big to get more accurate results). Your two lines will be HashMap and TreeMap. Try to see if you can see any differences in their performance as the number of NGrams in the map get large. If you can’t, that’s fine. Briefly write up your analysis (like 1 or 2 paragraphs) and include both that and the graph in a PDF you submit.

Name your file Analysis.pdf so that our checking program can know that you submitted the right thing. Your analysis should always be in a file called Analysis.pdf.