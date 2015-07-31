---
layout: page
title: "Submitting"
assignment: "percolation"
---

The following classes must be submitted in order to be graded:
<li> PercolationDFS.java </li>
<li> PercolationStats.java </li>
<li> PercolationUF.java </li>
<li> PercolationVisualizer.java </li>
<li> QuickUWPC.java </li>

Make sure when you submit, all of the following are present and functional:

<code>
Class: PercolationDFS
 Constructor: PercolationDFS(int)
 Method: public void open(int, int)
 Method: public boolean isOpen(int, int)
 Method: public boolean percolates()
 Method: public boolean isFull(int, int)
Class: PercolationUF
 Constructor: PercolationUF(int, IUnionFind)
 Method: public void open(int, int)
 Method: public boolean isOpen(int, int)
 Method: public boolean percolates()
 Method: public boolean isFull(int, int)
Class: PercolationStats
 Method: public static void main(String[])
Class: PercolationVisualizer
 Method: public static void main(String[])
Class: QuickUWPC
 Method: public void initialize(int)
 Method: public int components()
 Method: public int find(int)
 Method: public boolean connected(int, int)
 Method: public void union(int, int)
</code>

You also must submit your README in order to receive any credit.

If you create any new classes which are required for the above classes to compile, you should submit those as well.

The classes we give you which are not specified above should be considered "read-only" - if you submit them, the grader will replace them with the unmodified version you snarfed. Thus, you are free to modify them for testing, but the classes you submit should not depend on those modifications in order to be compile/have the correct return values.