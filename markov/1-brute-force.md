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

Here is the Java code implementation of this brute-force approach to generate up to maxLetters letters at random from the training-text in instance variable myString using an order-k Markov model (this is taken directly from MarkovModel.java)

<code>
&nbsp;&nbsp;protected String makeNGram(int k, int maxLetters) {<br>
&nbsp;&nbsp;&nbsp;&nbsp;// Appending to StringBuilder is faster than appending to String<br>
&nbsp;&nbsp;&nbsp;&nbsp;StringBuilder build = new StringBuilder();<br>
&nbsp;&nbsp;&nbsp;&nbsp;// Pick a random starting index<br>
&nbsp;&nbsp;&nbsp;&nbsp;int start = myRandom.nextInt(myString.length() - k + 1);<br>
&nbsp;&nbsp;&nbsp;&nbsp;String seed = myString.substring(start, start + k);<br>
&nbsp;&nbsp;&nbsp;&nbsp;ArrayList<Character> list = new ArrayList<Character>();<br>
&nbsp;&nbsp;&nbsp;&nbsp; // generate at most maxLetters characters<br>
&nbsp;&nbsp;&nbsp;&nbsp;for (int i = 0; i < maxLetters; i++) {<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;list.clear();<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int pos = 0;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;while ((pos = myString.indexOf(seed, pos)) != -1 && pos < myString.length()) {<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (pos + k >= myString.length())<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;list.add((char) 0); //This represents the end of the file<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;else<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;list.add(myString.charAt(pos + k));<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pos++;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int pick = myRandom.nextInt(list.size());<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;char ch = list.get(pick);<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (ch == 0) //end-of-file<br> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return build.toString();<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;build.append(ch);<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;seed = seed.substring(1) + ch;<br>
&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;return build.toString();<br>
&nbsp;&nbsp;}

</code>

The code above works, but to generate N letters in a text of size T the code does O(NT) work since it re-scans the text for each generated character. (O(NT) means that the running time is cNT, i.e. within a constant factor proportional to N times T).

###Why maxLetters?

One thing to note about this implementation of Markovian Text Generation is that the length of the generated text can vary. We specify an upper bound for the number of characters via the argument <code>maxLetters</code>, but if the end-of-file case is reached before we reach the upper bound, we will generate less than maxLetters characters.

This is an implementation choice - there are several ways we could guarantee that a fixed number of characters are generated:

<li>We could, for example, choose to use a "wraparound" to treat the start of the source as following the end of the source. However, this can result in us generating k-grams that don't appear in the original text, which contradicts the idea that the text we generate should resemble the source text. </li>
<li>We could try to only pick kgrams which have the potential to generate the desired length of text. However, this would involve using graph theory and turning this simple algorithm into a more complicated one. </li>
<li>We could simply discard attempts at generating text until we make an attempt which generates the desired number of characters. However, as long as there is some possibility of not generating the desired number of characters, this method has the potential to go on forever (as we could end up failing over and over again)

Thus, while we could ensure a fixed number of characters, it is much simpler and makes more sense within the scope of Markovian Text Generation not to.
