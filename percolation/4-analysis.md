---
layout: page
title: "Analysis"
assignment: "percolation"
---

Implement <code>PercolationUF</code> using the quick find data structure ([QuickFind.java](http://www.cs.duke.edu/courses/compsci201/fall14/assign/percolation/code/QuickFind.html)) and by creating a version using the weighted quick union with path compression data structure.

Adapt the code from class or [WeightedQuickUnionUF.java](http://algs4.cs.princeton.edu/15uf/WeightedQuickUnionUF.java.html) to create
<code>QuickUWPC.java</code> which implements the [IUnionFind](http://www.cs.duke.edu/courses/compsci201/fall14/assign/percolation/code/IUnionFind.html) interface. Run
<code>PercolationStats</code> to gather timings for
<ol>
<li><code>PercolationDFS</code></li>
<li><code>PercolationUF</code> with <code>QuickFind</code></li>
<li><code>PercolationUF</code> with <code>QuickUWPC</code></li>
</ol>

In your README, you will answer the following questions.
<ol>
<li>How does doubling N affect the running time?</li>
<li>How does doubling T affect the running time?</li>
<li>Measure running time (using calls to System.currentTimeMillis) of the three
versions of your program (DFS, Quick Find, and weighted quick union with path
compression).</li>
<li>Give a formula (using Big-Oh notation) of the running time on your
computer (in seconds) as a function of both N and T. 
</li>
<li> Estimate the largest
experiment you could  perform in one [minute, day, year] assuming your computer has
  enough memory.</li>
<li>Give a formula (using Big-Oh notation) that describes the amount of
memory (in bytes) that your IPercolate object consumes as a function of N for DFS,
Quick Find, and weighted quick union with path
compression).
</li>

</ol>
