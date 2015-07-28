---
layout: page
title: "Percolation Visualizer"
assignment: "percolation"
---

Your completed `PercolationVisualizer` should prompt the user for N and display the
percolation process starting with a N-by-N grid of sites (initially all blocked and [black](http://www.cs.duke.edu/csed/java/jdk1.6/api/java/awt/Color.html#BLACK)). After each site is opened, display full sites in [cyan](http://www.cs.duke.edu/csed/java/jdk1.6/api/java/awt/Color.html#CYAN), open sites (that aren't full) in [white](http://www.cs.duke.edu/csed/java/jdk1.6/api/java/awt/Color.html#WHITE), and blocked sites in black using <code>[princeton.StdDraw](http://www.cs.princeton.edu/introcs/15inout/javadoc/StdDraw.html)</code>. Here is an example of steps in a visualization on a 20x20 grid as in this [movie](http://www.cs.princeton.edu/courses/archive/spring11/cos226/assignments/percolation20-by-20-f11.mov) and the following snapshots. 

<img src = "img/visualizer_example.PNG" alt = "Percolation example">
#####Going from 50 to 100 to 150 to 204 to 250 open sites in a 20x20 grid.

###IPercolate data type
To model a percolation system, you will create different implementations of the
[IPercolate](http://www.cs.duke.edu/courses/compsci201/fall14/assign/percolation/code/IPercolate.html) interface:

<code>
public interface IPercolate {<br><br>
&nbsp;&nbsp;// Opens site at (row i, col j) if it is not already open<br>
&nbsp;&nbsp;public abstract void open(int i, int j);<br><br>
&nbsp;&nbsp;// Returns true if and only if site (row i, col j) is OPEN<br>
&nbsp;&nbsp;public abstract boolean isOpen(int i, int j);<br><br>
&nbsp;&nbsp;// Returns true if and only if site (row i, col j) is FULL<br>
&nbsp;&nbsp;public boolean isFull(int i, int j);<br><br>
&nbsp;&nbsp;// Returns true iff the simulated system percolates<br>
&nbsp;&nbsp;public abstract boolean percolates();<br><br>
}
</code>

You will complete brute-force <code>([PercolationDFS](http://www.cs.duke.edu/courses/compsci201/fall14/assign/percolation/code/PercolationDFS.html))</code> and union-find <code>([PercolationUF](http://www.cs.duke.edu/courses/compsci201/fall14/assign/percolation/code/PercolationUF.html))</code> versions of the IPercolate data type.

<b>NB</b>: By convention, the indices i and j are integers between 0 and N-1, where (0, 0) is the upper-left cell. Your code follow this convention in order to pass our tests.