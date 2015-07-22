---
layout: page
title: "MapMarkovModel"
assignment: "markov"
---

You’ll implement a class named MapMarkovModel that extends the abstract class AbstractModel. You should use the existing MarkovModel class to get ideas for your new MapMarkovModel class. 

You can modify MarkovMain to use MapMarkovModel by simply changing one line. 

<code>
&nbsp;&nbsp;public static void main(String[] args){ <br>
&nbsp;&nbsp;&nbsp;&nbsp;IModel model = new MapMarkovModel(); //THE ONLY CHANGE <br>
&nbsp;&nbsp;&nbsp;&nbsp;SimpleViewer view = new SimpleViewer(<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "CompSci 201 Markov Generation", "k count>");<br>
&nbsp;&nbsp;&nbsp;&nbsp;view.setModel(model);<br>
&nbsp;&nbsp;}</code>

Instead of scanning the training text N times to generate N random characters, you’ll first scan the text once to create a structure representing every possible k-gram used in an order-k Markov Model. You may want to build this structure in its own method before generating your N random characters. You should also note that <b>if you generate random text more than once with the same value of k, you will not need to regenerate this structure and doing so will cost you points.</b>

####Example
Suppose the training text is "bbbabbabbbbaba" and we’re using an order-3 Markov Model. 

The 3-letter string (3-gram) "bbb" occurs three times, twice followed by ‘a’ and once by ‘b’. We represent this by saying that the 3-gram "bbb" is followed twice by "bba" and once by "bbb". That is, the next 3-gram is formed by taking the last two characters of the seed 3-gram "bbb", which are "bb" and adding the letter that follows the original 3-gram seed. 

The 3-letter string "bba" occurs three times, each time followed by ‘b’. The 3-letter string “bab” occurs three times, followed twice by ‘b’ and once by ‘a’. What about the 3-letter string “aba”? It only occurs once, and it occurs at the end of the string, and thus is not followed by any characters. So, if our 3-gram is ever “aba” we will always end the text with that 3-gram. If instead, there were one instance of “aba” followed by a 'b' and another instance at the end of the text, then if our current 3-gram was “aba” we would have a 50% chance of ending the text. 

To represent this special case in our structure, we say that "aba" here is followed by an end-of-file character. If while generating text, we randomly choose the end-of-file character to be our next character, then instead of actually adding a character to our text, we simply stop generating new text and return whatever text we currently have. For this assignment, to represent an end-of-file character we suggest you use the character whose integer value is 0, which can be generated using <code>((char) 0)</code> – see MarkovModel’s makeNgram method for a better idea of how to implement this.

In processing the training text from left-to-right we see the following transitions between 3-grams starting with the left-most 3-gram "bbb"

bbb -> bba -> bab -> abb -> bba -> bab -> abb ->bbb -> bbb -> bba -> bab -> aba

This can be represented as a map of each possible 3gram to the 3grams that follow it (or EOF if the 3gram is at the end): 

<img src="img/abmap_example.png" alt="Splice">