---
layout: page
title: "Grading Criteria"
assignment: "percolation"
---

For transparency's sake, below is a list of aspects of your code the automated tests will check. Your code will still be checked by a TA, and passing all these tests does not guarantee a perfect grade. <b> Make sure you follow the conventions for <tt>isOpen</tt> and <tt>isFull</tt> in PercolationDFS and PercolationUF outlined in this writeup. We test your IPercolate implementations by seeing if they produce the same grid ours do, so if yours differ from ours you will fail many tests. </b> 

<ul>
<li>Do PercolationDFS and PercolationUF's isOpen, isFull, and percolates methods return the expected values? </li>
<li>Do PercolationDFS and PercolationUF handle out of bounds calls correctly?</li>
<li>Do PercolationDFS and PercolationUF run percolation simulations correctly?</li>
<li>Do PercolationDFS and PercolationUF work with small N?</li>
<li>Do PercolationDFS and PercolationUF accept calls to open/isOpen/isFull/percolates after percolation occurs?</li>
<li>Do PercolationDFS and PercolationUF use unnecessary static variables?</li>
<li>Do PercolationDFS and PercolationUF handle the longest possible simulation correctly? (percolation only occurring after close to N<sup>2</sup> opens) </li>
<li>Do PercolationDFS and PercolationUF handle calls to methods in a random order correctly? (will not pass if any of your methods besides open() change values in the grid, for example) </li>
<li>Do PercolationDFS and PercolationUF handle backwash correctly? (UF handling backwash is for extra credit, you can still get full credit if PercolationUF produces backwash)</li>
<li>Does a trial of PercolationUF make a reasonable number of calls to the methods in the IUnionFind object?</li>
<li>Does PercolationUF make a constant number of calls to IUnionFind per call to each of its methods, regardless of what N is?</li>
<li>Does PercolationUF achieve a reasonable order of growth when using an optimized IUnionFind object?</li>
</ul>

In addition to the automated tests, we will 
<ul>
<li> time your IPercolate implementations, 
<li> test whether your PercolationVisualizer draws the grid correctly for
both implementations, and  
<li> run your PercolationStats to see if it produces the correct results
</ul>
