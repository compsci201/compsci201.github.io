---
layout: page
title: "Submitting"
assignment: "percolation"
---

The following classes must be submitted in order to be graded:
<ul>
<li> PercolationDFS.java </li>
<li> PercolationStats.java </li>
<li> PercolationUF.java </li>
<li> PercolationVisualizer.java </li>
<li> QuickUWPC.java </li>
</ul>

Make sure when you submit, all of the following are present and functional:

<pre>
Class: PercolationDFS<br>
 Constructor: PercolationDFS(int)<br>
 Method: public void open(int, int)<br>
 Method: public boolean isOpen(int, int)<br>
 Method: public boolean percolates()<br>
 Method: public boolean isFull(int, int)<br>
Class: PercolationUF<br>
 Constructor: PercolationUF(int, IUnionFind)<br>
 Method: public void open(int, int)<br>
 Method: public boolean isOpen(int, int)<br>
 Method: public boolean percolates()<br>
 Method: public boolean isFull(int, int)<br>
Class: PercolationStats<br>
 Method: public static void main(String[])<br>
Class: PercolationVisualizer<br>
 Method: public static void main(String[])<br>
Class: QuickUWPC<br>
 Method: public void initialize(int)<br>
 Method: public int components()<br>
 Method: public int find(int)<br>
 Method: public boolean connected(int, int)<br>
 Method: public void union(int, int)<br>
</pre>

You also must submit your README in order to receive any credit.

If you create any new classes which are required for the above classes to compile, you should submit those as well.

The classes we give you which are not specified above should be considered "read-only" - if you submit them, the grader will replace them with the unmodified version you snarfed. Thus, you are free to modify them for testing, but the classes you submit should not depend on those modifications in order to be compile/have the correct return values.
