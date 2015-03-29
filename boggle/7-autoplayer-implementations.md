---
layout: page
title: "AutoPlayer Implementations"
assignment: "boggle"

---


###**LexiconFirstAutoPlayer**

You’re given another class, **LexiconFirstAutoPlayer**, that is relatively simple to implement since you’ll have working lexicons and a working **GoodWordOnBoardFinder** from earlier in this assignment. In the implementation of **LexiconFirstAutoPlayer** every word is looked up in the lexicon to see if it’s on the board. This method is surprisingly fast enough for a game of Boggle , but it’s probably not fast enough to run 10,000 times without waiting for a while. 

Once you've implemented **GoodWordOnBoardFinder** to find where a word occurs on a board you'll be able to implement (complete) the class **LexiconFirstAutoPlayer**. This class extends **AbstractAutoPlayer**. To find all the words on a board simply iterate over every value in a lexicon checking to see if the word is on the board by calling the *cellsForWord* method you wrote earlier. 

The code to do this is already written for you- just change **LexiconFirstAutoPlayer** to use the GoodWordOnBoardFinder you wrote. This should involve just changing one line. 

###**BoardFirstAutoPlayer**
We will refer to the following board in the discussion of this class: 

####![Boggle Board Example](https://www.cs.duke.edu/courses/fall12/compsci201/assignments/boggle/simpleboggle.jpg)

This looks at the board and tries to form words by trying all the paths on the board.

Rather than iterating over every word in the dictionary you can use the board to generate potential words. For example, in the board shown the following words can be formed started the "L" in the upper-left corner: "LORE", "LOSE", "LOST", and "LOT." From the output it's clear that "LOSER" isn't in the lexicon being used when the screen shot was taken since it's on the board, but isn't shown in the output (word list). 

Starting at the cell “R” at [1,3] (recall the first row has index zero) we can form “REST” and “RESORT”. Starting at the cell “R” at [0,2] we can form “ROLL” and “ROSE” as well as “REST”. 

Since no word begins with “GT”, “GP”, “GS”, no search will proceed from the “G” in the lower-right after looking at more than two cubes since these two-character prefixes aren’t found in the lexicon. 

You'll write a recursive helper method for this class to find all the words starting at a specified [row,column]. The basic idea is to pass to this helper method at least the following: 
1. The current row, column.
2. The string built from the search so far (you may want to use a StringBuilder) 

The code you write will be very similar to the code you wrote in **GoodWordOnBoardFinder** *cellsForWord* and its helper method. 

When first called, the string built from the search so far is the empty string: "". The current cell on the board, if legal and not used in the search so far, is added to the end of the string built so far. If the string is a word, the word is added to the collection of found words by calling the inherited add(..) method. (See the code in **AbstractPlayer** for how the words found are stored via this method.)


If the string is either a word or the prefix of a word in the lexicon then the search is continued by calling the helper method for each adjacent cell with the string built so far. 
If the string is not a prefix or a word then the search is cut-off at this point and the recursion will unwind/backtrack (essentially to the point where the last possible word/prefix was formed). 

Backtracking code should sure your code doesn’t re‐use a **BoardCell** once it has been used in the current search. This means that each **BoardCell** that contributed to the string built from the search so far can’t be re-‐used in extending the string. But the cell can be re-used when searching for different strings/starting from or continuing from different cubes. 

You can use an instance variable/field to store the **BoardCell** objects used in the current word being formed, but other approaches work as well (e.g., using a parameter) — note that **BoardCell** implements Comparable. But, since you’re backtracking, be sure to undo the marking of a board cell both in the string being built and in the structure storing which the board cells contributed to the string.