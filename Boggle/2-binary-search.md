---
layout: page
title: "BinarySearchLexicon"
assignment: "boggle"

---


You must modify the class named **BinarySearchLexicon** implementing the ILexicon interface.

**Note:** In the ILexicon.load(...) methods you can assume no duplicate words will be inserted via either the Scanner or ArrayList parameters to the load methods.

Some of **BinarySearchLexicon** is already written for you. The existing code stores words in an ArrayList. You'll need to modify the code to sort in the method *wordStatus* to use Collections.binarySearch to search the list. 

Read the documentation for binarySearch (online Java APIs- Collections.binarySearch). Note that when the index value returned is less than zero the value can be used to determine where a word *should be* in a sorted list. For example, when looking up "ela" the value returned might be -137. This means that "ela" is not in the lexicon, but if it were to be inserted it would be at index 136. This also means that if the word at index 136 does not begin with "ela" then no word in the lexicon has a prefix of "ela." So, any non-negative value returned by binarySearch means the status of word is LexStatus.WORD. IF the value is negative, one call of the appropriate String.startsWith() method can determine if LexStatus.PREFIX should be returned (make sure you don't go off the end of the array of words in the lexicon when calling startsWith).