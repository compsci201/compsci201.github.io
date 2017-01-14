---
layout: page
title: "PercolationDFS/PercolationUF"
assignment: "percolation"
---

###IPercolate data type
To model a percolation system, you will create different implementations of the
[IPercolate](/percolation/code/IPercolate.html) interface:

<pre><tt><b><font color="#7f0055">public</font></b><font color="#000000"> </font><b><font color="#7f0055">interface</font></b><font color="#000000"> </font><font color="#000000">IPercolate</font><font color="#000000"> </font><font color="#000000">{</font>
<font color="#000000">	</font><font color="#717ab3">// Opens site at (row i, col j) if it is not already open</font>
<font color="#000000">	</font><b><font color="#7f0055">public</font></b><font color="#000000"> </font><b><font color="#7f0055">abstract</font></b><font color="#000000"> </font><b><font color="#7f0055">void</font></b><font color="#000000"> </font><font color="#000000">open</font><font color="#000000">(</font><b><font color="#7f0055">int</font></b><font color="#000000"> i</font><font color="#000000">,</font><font color="#000000"> </font><b><font color="#7f0055">int</font></b><font color="#000000"> j</font><font color="#000000">);</font><font color="#000000"> </font>
<font color="#000000">	</font><font color="#717ab3">// Returns true if and only if site (row i, col j) is OPEN</font>
<font color="#000000">	</font><b><font color="#7f0055">public</font></b><font color="#000000"> </font><b><font color="#7f0055">abstract</font></b><font color="#000000"> </font><b><font color="#7f0055">boolean</font></b><font color="#000000"> </font><font color="#000000">isOpen</font><font color="#000000">(</font><b><font color="#7f0055">int</font></b><font color="#000000"> i</font><font color="#000000">,</font><font color="#000000"> </font><b><font color="#7f0055">int</font></b><font color="#000000"> j</font><font color="#000000">);</font>
<font color="#000000">	</font><font color="#717ab3">// Returns true if and only if site (row i, col j) is FULL</font>
<font color="#000000">	</font><b><font color="#7f0055">public</font></b><font color="#000000"> </font><b><font color="#7f0055">boolean</font></b><font color="#000000"> </font><font color="#000000">isFull</font><font color="#000000">(</font><b><font color="#7f0055">int</font></b><font color="#000000"> i</font><font color="#000000">,</font><font color="#000000"> </font><b><font color="#7f0055">int</font></b><font color="#000000"> j</font><font color="#000000">);</font><font color="#000000"> </font>
<font color="#000000">	</font><font color="#717ab3">// Returns true iff the simulated system percolates</font>
<font color="#000000">    </font><b><font color="#7f0055">public</font></b><font color="#000000"> </font><b><font color="#7f0055">abstract</font></b><font color="#000000"> </font><b><font color="#7f0055">boolean</font></b><font color="#000000"> </font><font color="#000000">percolates</font><font color="#000000">();</font>
<font color="#000000">}</font></tt></pre>


You will complete brute-force ([PercolationDFS](/percolation/code/PercolationDFS.html)) and union-find ([PercolationUF](/percolation/code/PercolationUF.html)) versions of the IPercolate data type. 

<b>NB</b>
<ol>
<li> By convention, the indices i and j are integers between 0 and N-1, where (0, 0) is the upper-left cell. Your code must follow this convention in order to pass our tests.</li>
<li> The methods can be called in any order. That is, you could call <tt>percolates</tt> twice in a row and then <tt>isFull</tt>, so make sure that your methods will return the correct value given the current state of your <tt>IPercolate</tt> object.</li>
</ol>

Below are some tips to get you started (not all methods you need to write have tips). Some tips for <code>PercolationUF</code> may be invalid if you are attempting the extra credit.

###[PercolationDFS](/percolation/code/PercolationDFS.html)

It may be useful to define and use constants (e.g. globals named <code>OPEN</code>, <code>FULL</code>, <code>BLOCKED</code>, probably ints) to keep track of the state of each cell.

####Constructor

Be sure to initialize your grid to be completely blocked.

####Open

See DFS below

####IsOpen and IsFull

These should both be one-line, O(1) methods. For our solution, cells which are full are not considered open by <code>isOpen</code>. That is, <code>isFull(i, j)</code> and <code>isOpen(i, j)</code> cannot both be true at the same time. 

####DFS

This method should use DFS from the top of the grid to determine which cells are filled and update <code>myGrid</code>.

Every cell this method visits should be marked as filled. Recursive calls should only be made on adjacent cells which are open (we avoid infinite recursion by not revisiting full cells) - because of this, <b>make sure to flush the grid by marking all full cells as open before calling this method</b>, otherwise the method will stop immediately and not explore the grid.

Since the purpose of <code>dfs</code> is to update the status of each cell, and each cell's status can only change when a new cell is opened, you want to call this method only when <code>open</code> is called.

For the purposes of analysis - a depth-first-search that visits k sites with m total connections between them takes O(k+m). Here, the sites are the cells in the grid, and the connections are the edges between two adjacent cells. This should let you determine the runtime of calling this method on all the top cells (we can consider all the calls to <code>dfs</code> made from one call to <code>open</code> as one search, since they cumulatively will not visit the same cell twice)

###[PercolationUF](/percolation/code/PercolationUF.html)

Be sure to handle out-of-bounds cases correctly in all methods.

Your <code>IUnionFind</code> object should have two additional elements besides those representing cells - a virtual source and virtual sink representing the top and bottom of the <code>Percolation</code> object. Be sure to use a consistent value for the index of these (constants may help here).

####Open

You should be making union calls in this method or in a helper method called by this method. Remember to handle unions for top/bottom cells with a special case.

####isOpen and isFull

These should both be one-line, O(1) methods. <b> Unlike PercolationDFS, for our solution, cells which are considered full by <code>isFull</code> are also considered open by <code>isOpen</code>. That is, if <code>isFull(i, j)</code> is true, then <code>isOpen(i, j)</code> must be true.</b> 

This may seem confusing, but it makes writing <code>PercolationDFS</code> and <code>PercolationUF</code> respectively much easier - for DFS, not being able to consider cells open and full at the same time is more convenient, but for union-find, the opposite is more convenient.

####Percolates

This should now be an O(1), one line method. 

