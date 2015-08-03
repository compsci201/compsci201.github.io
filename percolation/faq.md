---
layout: page
title: "FAQ"
assignment: "percolation"
---

####What should I read prior to writing code: 

Most of the code in this assignment comes from discussion in the text and supplementary reading. Please review the following:

[Section 2.4](http://introcs.cs.princeton.edu/24percolation/) in Introduction to Programming in Java by Robert Sedgewick & Kevin Wayne

Sedgewick & Wayne's [union-find case study](http://algs4.cs.princeton.edu/15uf/) or the [following excerpt](http://www.cs.princeton.edu/courses/archive/fall09/cos226/handouts/Algs3Ch1.pdf) from Algorithms, 3rd Edition

API documentation for [princeton.StdDraw](http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html)

Optional: If you'd like to understand the theory behind the complexity of union-find operations in detail, read [these notes](http://www.cs.princeton.edu/courses/archive/fall10/cos226/precepts/15UnionFind-Tarjan.pdf) from Robert Tarjan

####What should stddev() return if T equals 1? 

The sample standard deviation is undefined. We recommend returning <code>Double.NaN</code> (Not-A-Number).

####After the system has percolated, my PercolationVisualizer colors in cyan all sites connected to open sites on the bottom (in addition to those connected to open sites on the top). Is this backwash OK? 

<img src="img/percolation-backwash.png">

Yes, we won't deduct for that for PercolationUF (PercolationDFS should not have backwash, if it does there's a major error). You can think of the water as filling back up from the bottom. 

If you finish early, you can try to eliminate backwash without using a second union-find structure or worsening the runtime's order of growth for extra credit.  Our tests for PercolationUF compare your grid to both an IPercolate implementation which removes backwash and one which does not. Thus, whether or not you implement backwash should not affect your correctness score.

Your solution to eliminate backwash should be isolated within PercolationUF - that is, it should be independent of the IUnionFind implementation used and the visualizer. In addition, the Big-Oh runtime of every method should not increase as a result of implementing backwash removal, otherwise you will fail the engineering tests.

####How efficient should the methods in PercolationUF be?

For a NxN grid, the construction should take N<sup>2</sup> time; the other methods should take time at most logarithmic in N.

####How do I generate a random blocked site? 

Create a list of all blocked sites. [Shuffle](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#shuffle-java.util.List-java.util.Random-) the list at the beginning and then each step pick the next element from the list.

####How do I confirm that my PercolationUF and PercolationDFS work correctly?

You can add a method in your PercolationVisualizer to read cells to open from a file. The first line is N and the subsequent lines are cells to open. Opening the cells in [input20.txt](http://www.cs.duke.edu/courses/fall14/compsci201/assign/percolation/data/input20.txt) should result in the animation shown in this [linked movie](http://www.cs.princeton.edu/courses/archive/fall14/cos226/checklist/percolation20-by-20.mov). You could add either add a method to PercolationVisualizer to read in the cells to open from a file. Alternatively, you could read the row and column indices from the two arrays defined below.

<code>rowIndices = { 6, 17, 11,  8,  4,  0, 11,  4, 15,  2,  8, 11,  3,  2,  1,
4,  4, 16,  9,  3, 12,  3,  2,  7, 12, 14, 18, 18,  4, 18,  8,  6,  6,  8,
2,  1,  3,  9,  8,  9, 12,  8,  5,  7,  5, 13,  4,  3, 11, 17,  4, 14, 16,
3, 10, 10, 17,  7,  4,  1, 13, 12, 10, 10, 10, 12, 14,  5, 17, 13,  2,  7,
11,  5, 13, 10,  8, 14, 18, 14,  5,  7,  8,  9, 13, 13,  0, 10, 18, 17, 12,
17,  1,  4,  3,  3, 13,  1, 14, 19, 11,  7,  4, 17,  4,  9,  1,  4,  2, 19,
10, 15,  3,  8, 16, 13, 18,  6, 18, 10, 14, 12, 13, 10, 11, 16, 11,  0,  3,
17, 16, 12,  6,  1,  7, 18, 11, 19,  3, 14, 14, 16, 16,  0,  4, 19, 17,  2,
13, 15, 16, 18,  9,  0,  1, 14,  5, 11, 14, 17,  0, 15, 15, 18,  0,  7,  0,
18,  7, 15,  3, 14, 10,  3,  0,  3,  7, 13, 18, 17,  4, 16,  3,  6, 12,  1,
10, 15, 17, 10, 14, 14, 16, 15, 17, 17,  2,  6,  9, 13, 16,  0, 17,  6, 15,
5, 10, 11, 11,  6,  1, 12,  0, 14, 12, 16, 12, 14,  7, 18,  4,  0, 13,  5,
11,  6, 19,  8,  2, 15, 19,  6, 19,  6,  8,  7,  2,  2,  2,  4, 18, 15, 19,
4,  5,  7, 11,  1, 15,  0, 15,  0}; <br>
colIndices = { 10, 10,  4,  4,  8,  0,  0,  3, 18, 12, 13,  3, 10,  2,  1,
16, 19, 10,  2, 16,  3, 17,  3, 14,  4,  6, 19, 17,  0, 10,  9, 14,  0, 12,
11, 12,  9, 10,  0, 16, 14,  5,  4, 16,  8,  5,  9, 11, 15,  1, 10, 15, 12,
14,  2, 12, 19, 11,  5,  5, 17, 13,  6, 19, 13,  9, 16, 14,  9, 10,  0,  9,
13, 13,  9,  8, 18,  1,  5, 12,  1,  6,  8,  6, 19,  7, 13, 14,  0,  7,  7,
11,  4,  1,  6, 19, 12,  7, 11, 11,  6, 13,  4, 15, 11, 14,  6, 18,  8, 12,
7, 17,  4, 14,  9, 18,  7, 12, 11, 11, 17, 17,  0, 18, 18,  3,  5,  8,  8,
8, 18, 19,  1,  3, 10,  6,  1,  5, 15,  4,  2,  5, 11,  4, 13,  6,  6, 18,
14, 16, 14, 18,  5,  7,  8, 10,  6, 17,  3, 16, 10, 19,  3,  8,  6,  1, 12,
12,  2,  2,  1,  8,  3,  0, 14,  7, 12, 11, 13, 14,  6, 13,  5,  8,  6, 14,
0, 10,  4,  1, 14,  7,  6,  7,  0,  2, 17,  7, 13, 13, 15,  3, 12,  9,  1,
7,  5,  8, 10,  5,  2,  0,  1,  0, 15, 19, 18, 18,  0,  9, 12, 16,  4,  9,
11, 15, 18, 10,  5,  0,  0, 17,  4, 11,  1, 19, 14, 13,  4, 17, 16, 11,  3,
15,  2,  5, 14, 15,  5, 17,  5, 17};</code>