---
layout: page
title: "A Brute Force Approach"
assignment: "markov"
---

Below is pseudo-code to generate random letters (and thus random text) using an order-k Markov model and a training text from which the probabilities are calculated. 

<code>
seed = random k-character substring from the training text<br> <br>
repeat N times to generate N random letters<br> 
&nbsp;for each occurrence of seed in training text<br> 
&nbsp;&nbsp;record the letter that follows the occurrence of seed in a list <br>
&nbsp;&nbsp;(or if end-of-file follows, record end-of-file)<br> 
&nbsp;choose a random element of the list as the generated letter C<br>
&nbsp;if C is end-of-file, exit loop<br> 
&nbsp;print or store C<br> 
&nbsp;seed = (last k-1 characters of seed) + C</code>

Here is the Java code implementation of this brute-force approach to generate numLetters at random from the training-text in instance variable myString using an order-k Markov model (this is taken directly from MarkovModel.java)

<code>
&nbsp;&nbsp;protected String makeNGram(int k, int maxLetters) {<br>
&nbsp;&nbsp;&nbsp;&nbsp;// Appending to StringBuilder is faster than appending to String<br>
&nbsp;&nbsp;&nbsp;&nbsp;StringBuilder build = new StringBuilder();<br>
&nbsp;&nbsp;&nbsp;&nbsp;// Pick a random starting index<br>
&nbsp;&nbsp;&nbsp;&nbsp;int start = myRandom.nextInt(myString.length() - k + 1);<br>
&nbsp;&nbsp;&nbsp;&nbsp;String seed = myString.substring(start, start + k);<br>
&nbsp;&nbsp;&nbsp;&nbsp; // Allow for wraparound   <br>
&nbsp;&nbsp;&nbsp;&nbsp;ArrayList<Character> list = new ArrayList<Character>();<br>
&nbsp;&nbsp;&nbsp;&nbsp; // generate maxLetters characters<br>
&nbsp;&nbsp;&nbsp;&nbsp;for (int i = 0; i < maxLetters; i++) {<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;list.clear();<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int pos = 0;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;while ((pos = myString.indexOf(seed, pos)) != -1 && pos < myString.length()) {<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	if (pos + k >= myString.length())<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;list.add((char) 0); //This represents the end of the file<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;else<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;list.add(myString.charAt(pos + k));<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pos++;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int pick = myRandom.nextInt(list.size());<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;char ch = list.get(pick);<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (ch == 0)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return build.toString();<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;build.append(ch);<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;seed = seed.substring(1) + ch;<br>
&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;return build.toString();<br>
&nbsp;&nbsp;}</code>

The code above works, but to generate N letters in a text of size T the code does O(NT) work since it rescans the text for each generated character. 