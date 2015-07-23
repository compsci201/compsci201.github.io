---
layout: page
title: "WordMarkovModel"
assignment: "markov"
---

The only difference between the MapMarkovModel and the WordMarkovModel is that the MapMarkovModel operates on a character-by-character basis (a 3-gram is three characters). The WordMarkovModel operates on a word-by-word basis (a 3-gram is three words). This will be done using the WordNgram class which has been started for you. 

####The WordNgram Class

The WordNgram class has been started for you. You can create new constructors or change the constructor given, though the provided constructor will likely be useful. You can also add instance variables in addition to myWords. 

You’ll need to ensure that .hashCode, .equals, .compareTo and .toString work properly and efficiently. You’ll probably need to implement additional methods to extract state (words) from a WordNgram object. In my code, for example, I had at least two additional methods to get information about the words that are stored in the private state of a WordNgram object. 

To facilitate testing your .equals and .hashcode methods a JUnit testing program is provided. You should use this, and <b>you may want to add more tests to it in testing your implementation</b>. The given tests are a good indication of how well your code works, but as always JUnit tests are not a 100% guarantee. 

Testing with JUnit shows that a method passes some test, but the test may not be complete. For example, your code will be able to pass the the tests for .hashCode without ensuring that objects that are equal yield the same hashvalue. That should be the case, but it’s not tested in the JUnit test suite you’re given. 

####Using JUnit

To test your WordNgram class you’re given testing code. This code tests individual methods in your class, these tests are called unit tests and so you need to use the standard JUnit unit-testing library with the WordNgramTest.java file to test your implementation.

To choose Run as JUnit test first use the Run As option in the Run menu as shown on the left below. You have to select the JUnit option as shown on the right below. Most of you will have that as the only option.

<img src="img/junit_example1.png" alt="Splice">

There are two tests in WordNgramTest.java: one for the correctness of .equals and one for the “performance” of the .hashCode method.

If the JUnit tests pass, you’ll get all green as shown on the left below. Otherwise you’ll get red — on the right below — and an indication of the first test to fail. Fix that, go on to more tests. The red was obtained from the code you’re given. You’ll work to make the testing all green.

<img src="img/junit_example2.png" alt="Splice">

####The WordMarkovModel class

You’ll implement a class named WordMarkovModel that extends the abstract class AbstractModel class. This should be very similar to the MapMarkovModel class you wrote, but this class uses words rather than characters. 

A sequence of characters was stored as a String in the code for character-oriented Markov models. For this program you’ll use ArrayLists (or arrays) of Strings to represent sequences of words used in the model. 

The idea is that you’ll use 4-words rather than 4-characters in predicting/generating the next word in an order-4 word based Markov Model. You’ll need to construct the map-based WordMarkovModel and implement its methods so that instead of makeNgram generating 100 characters at random it generates 100 words at random (but based on the training text it reads). The words should be separated by single spaces, without any trailing whitespace at the beginning or end.

To get all words from a String use the String split method which returns an array. The regular expression <code>"\\s+"</code> (two back-slashes, followed by "s+") represents any whitespace, which is exactly what you want to get all the words in file/string:

<code>String[] words = myString.split(“\\s+”); </code>

Using this array of words, you’ll construct a map in which each key, a WordNgram object, is mapped to a list of WordNgram objects — specifically the n-grams that follow it in the training text (instead of using <code>((char) 0)</code> to represent the end of the file in your map, we suggest you use <code>null</code>). This is exactly what your MapMarkovModel did, but it mapped a String to a list of Strings. Each String represented a sequence of k-characters. In this new model, each WordNgram represents a sequence of k-words. The concept is the same. 

####Comparing Words and Strings in the Different Models

In the new WordMarkovModel code you write you’ll conceptually replace Strings in the map with WordNgrams. In the code you wrote for maps and strings, calls to .substring will be replaced by calls to new WordNgram. This is because .substring creates a new String from parts of another and returns the new String. In the WordMarkovModel code you must create a new WordNgram from the array of strings, so that each key in the word-map, created by calling new, corresponds to a string created in your original program created by calling substring.
