---
layout: page
title: "Boggle"
assignment: "boggle"

---


##**Lexicons Introduction**

For this part of the assignment you'll design code for one class (with an optional extra class for extra credit) and you'll analyze the performance of several classes empirically by using LexiconBenchmark. 

In Boggle, legal words are those found in the *lexicon* associated with a game. A lexicon is simply a list of words, it's not a dictionary because it doesn't have associated definitions for each word. 

The **ILexicon** interface specifies which methods require implementations. You can read the JavaDoc comments in the interface for a description of what each method should do. 

You're given one implementation, **SimpleLexicon** which implements the method *wordStatus* in O(n) time because it simply does a linear search over the list of words it stores. There is extensive documentation in the code for you to reference. 

An inheritance diagram of the classes is given below — the classes you write must implement the methods of **ILexicon** and can provide other methods as well if that seems helpful. 

1. You will write BinarySearchLexicon.
2. CompressedTrieLexicon is extra credit.
3. SimpleLexicon and TrieLexicon are provided. 

##![Boggle Board](https://www.cs.duke.edu/courses/fall12/compsci201/assignments/boggle/newlexhierarchy2.jpg)

The following pages give a more in depth look at how to implement the classes **BinarySearchLexicon** and **CompressedTrieLexicon** 


##**Testing**

We provide a JUnit testing class **TestLexicon** to use as you develop your ILexicon implementations. To test different implementations simply change the code in the method *makeLexicon* to return the implementation you want to test and run the JUnit tests. 