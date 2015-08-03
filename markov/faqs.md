---
layout: page
title: "FAQs"
assignment: "markov"
---

####How should we implement WordNgram's compareTo()?

Compare WordNgrams like you would Strings. For comparing "abc" and "abd" for example, we compare the first letters and see they're the same, so we'd move on. We'd do the same thing for the second letter. Once we get to the third letter, we realize c and d are not the same, and c comes before d, so "abc" comes before "abd". We could apply a similar process to the WordNgrams ["a", "b", "c"] and ["a", "b", "d"], but also to ["apple", "bat", "cat"] and ["apple", "bat", "dog"]. 

Also, consider comparing "ab" and "abc" and how that might translate to WordNgrams. 

####Do we need to account for the case where k doesn't change but myString does?

Yes, absolutely. If I use k = 3 and the training text "ababab", and then k = 3 and the training text "aaaaaa", it won't make sense for me to receive Markov text with a 'b' in it for the second case, but that's what will happen if changes to myString but not to k do not rebuild the map.

####When writing MapMarkovModel, should I start from scratch or use MarkovModel?

We suggest you use MarkovModel as a basis for MapMarkovModel. Specifically, if you declare MapMarkovModel as follows:

<code>public class MapMarkovModel extends MarkovModel</code>

Then any method which you don't rewrite in MapMarkovModel, MapMarkovModel will copy from MarkovModel. This reduces the total amount of code you write without losing any functionality. If you do rewrite a method in MapMarkovModel, it is considered good style (but it is not necessary) to add an @Override annotation before the method declaration.

####When I ran my model on a large text file, it gave me an java.lang.OutOfMemoryError. How can I fix this?

In the Eclipse menu, click on Run -> Run configurations, and add -Xmx512M (or -Xmx1024M, -Xmx2048M) under VM arguments to increase the size of your heap. 

If you still get the error after adding VM arguments, you are likely using too much memory somehow (e.g. storing maps you don't need anymore). 

You need to be able to run on large text files, otherwise you may fail tests where we compare the results of your model to the results of our model on large text files.

####For MapMarkovModel, I know I can compare the results to the brute-force model by using the same random seed, but how do I verify the results from WordMarkovModel?

Try writing your own text file which has predictable results (hint: if you add a space between every character in a one-word text file, how would the output of MapMarkovModel using the original text and the output of WordMarkovModel using the modified text compare?)

Alternatively, as long as you do not look at each others code, you and a classmate are free to share the outputs of your models on certain text files and compare. Chances are if you and 1 or 2 classmates generate the same results, you are all correct.

####Am I allowed to add methods to any of the classes I am writing other than the ones specified?

Yes! Breaking up your code using helper methods and making classes more functional by adding various methods is never a bad thing. In fact, you will definitely need to add a method to WordNgram, and MapMarkovModel/WordMarkovModel will be much easier with a helper method.

That being said, keep in mind some of the classes we've given you shouldn't be modified. See [the submitting guidelines](/markov/0-submitting) for more info.

####How do I combine all the words in WordMarkovModel's output?

The output string should have all the words, separated by one space each, with no trailing space. So, if the words produced were ["a", "b", "c"], the output String should be "a b c". Make sure you don't have any trailing spaces! (like "a b c ").

If you're using Java 8, one method you could use is [String.join()](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#join-java.lang.CharSequence-java.lang.CharSequence...-). Alternatively, every time you add a word to your output, add a space after it. Then, before returning, trim the space off using [String.trim()](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#trim--).

####How should I handle punctuation in WordMarkovModel?

There's no need to treat punctuation characters specially, just treat them like any other character. This means that "end" and "end." should be different words just like "end" and "ends" are. The only characters you need to worry about are whitespace characters, which <code>.split("\\s+")</code> will handle for you.

####Why do I get a ArrayIndexOutOfBoundsException?

You're attempting to access an element outside the bounds of an array. e.g. For a 10-element array, <code>arr</code>, <code>arr[x]</code> will trigger this exception for x <u><</u> -1 and x <u>></u> 10. 

When students get this exception in this project, it is usually a result of an off-by-one error in a for loop. It also often occurs as a result of initializing WordNgram, which uses arrays in its constructor, without understanding how the arguments to the constructor are used - reading that constructor may help you avoid this error.

####Why do I get a NullPointerException?

NullPointerExceptions occur when either a method is passed a null value and that method rejects it, or you try to call something from a null value. e.g. a TreeMap will throw a NullPointerException if you try to call <code>TreeMap.get(null)</code>. Alternatively, if <code>a</code> is null, then calling something like <code>a.hashCode()</code> will throw a NullPointerException even though hashCode is not written to throw one.

As a note, for any map, if you do not put a key into it but attempt to get the value associated with that key, you will get null. So, the following code would throw an exception:

<code>
HashMap<String, String> markovMap = new HashMap<String, String>(); <br>
String gram = markovMap.get("a"); <br>
System.out.println(gram.hashCode()); <br>
</code>