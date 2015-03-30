---
layout: page
title: "GoodWordOnBoardFinder"
assignment: "boggle"

---

If you went to recitation on March 30th you should have most of this part completed. Read through just to be sure. Recitation slides are a good resource to look at if you are confused. We did not write code to account for the 'Qu' cell in recitation so make sure you do that. 

####![Boggle Board](https://www.cs.duke.edu/courses/fall12/compsci201/assignments/boggle/bogglemountain.jpg)

When you play boggle, possible words are highlighted on the board. The above  picture shows the word 'mountain' being highlighted. The correct highlighting of words depends on the functionality of your 
**GoodWordOnBoardFinder**. 

###**BadWordOnBoardFinder:**
This class is provided for you. It implements the **IWordOnBoardFinder** interface, which contains one method: *cellsForWords*. If you look at the class implementation you see that **BadWordOnBoardFinder** just returns an empty list of **BoardCell** objects, so no words will be found. In other words this implementation fails to correctly find any words, if you run BoggleMain with the **BadWordOnBoardFinder** no words will be highlighted because the list of possible words is empty. Hence the description as 'Bad'. 

###**GoodWordOnBoardFinder:**
You should write this class implementing the **IWordOnBoardFinder** interface. You'll implement the method *cellsForWord* using a standard backtracking search to find a word on a Boggle board. You will write two methods in this class: *cellsForWord* and a recursive helper method. Together these methods will determine whether a String/word occurs on a given BoggleBoard. 

Your recursive helper method should search for the word beginning at a specified cell (row, column) on the board. The code for *cellsForWord* starts as follows: 

	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
	List<BoardCell> list = new ArrayList<BoardCell>();
	for(int r = 0; r < board.size(); r++) {
		for(int c = 0; c < board.size(); c++) {
			if(helper(board, r, c, list, ....)) {
			//not shown..

This code will call the helper method with every possible (row, column) as a starting point. The helper method will do most of the work. 

You can write the helper method however you want, here is a suggested implementation: 

Give the helper method an int parameter representing an index indicating which character in the string we are currently trying to match to our current (row, column). The first call to this helper method will start the index at zero indicating the first character of the string should be matched to our current cell. 

There are several base cases in this helper method:
<li> If the index of string is too large, the word has been found. </li>
<li> If the row and column are out of bounds, stop the search. </li>
<li>  If the current character (at index) does not match the string on the current board cell, you should stop. </li>
<li> If the boardcell has already been used, you should stop. Consider creating a list to keep track of which cells you have used. You will have to pass this list as a parameter in each recursive call. </li>

After the base cases we know that we are in a word and the current character matches the string on our current board cell. Now up to eight recursive calls will have to be made to find the next letter in the string. You will have to update your index, add to the list of used BoardCells, and change the row and column. 

You could explicitly write out the eight recursive calls, but please don't do that. Your implementation should be cleaner than that. Consider using two arrays like so: 

	int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1};
	int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
	 for(int k=0; k < rdelta.length; k++){
		 if (helper(board, row+rdelta[k], col+cdelta[k], ...) 
			 return true; 
	 }


In our fourth base case we indicated that keeping a list of the used BoardCells. This list really has two purposes:

1. The goal of writing *cellsForWord* is to return a list of cells that form the word. If we properly maintain the used BoardCells list then you can return this in *cellsForWord* if the word is found on the board. 
2. The list will also help you ensure that the same BoardCell isn't used more than once to find the word currently being searched for. (e.g. you can use .contains() to see if a BoardCell has been used before.

If you don't find the word being looked for you need to backtrack and undo what you've done so far. As with typical recursive backtracking code, this involves undoing the step made before the recursive invocations. In this case the backtrack step is simple, you should remove the current BoardCell object from the list of used BoardCells. 


###**Note:**
There are no 'Q' cells on a BoggleBoard, instead there is a 'Qu' cell. Consider the implications of this, the index parameter will not increase by one, but instead by two. If your code isn't working make sure all of your code would work for a normal cell and a 'Qu' cell, this is where a lot of issues will arise. 


####**Testing:**
You're given a JUnit test, **TestWordFinder** for testing your **GoodWordOnBoardFinder**. Hopefully passing the unit tests will be enough for your code to work in the Boggle game, but consider special cases. For example what if the tester doesn't test with a board containing a 'Qu' cell? You can additionally change **BoggleMain** to use **GoodWordOnBoardFinder** and play the game to see if the game works properly. 