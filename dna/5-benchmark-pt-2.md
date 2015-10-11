---
layout: page
title: "Benchmarking Part 2"
assignment: "dna"
---
<p>We're going to see how our newly created <code>LinkStrand</code> fares in the benchmark.</p>
<p> Let's run virtual experiments to show that LinkStrand is O(B) where B is the number of breaks. First, change <code>SimpleStrand</code> to <code>LinkStrand</code> in the main method of the class DNABenchMark. Then, make different runs to demonstrate this O(B) behavior, changing the number of breaks in your file. You can do this by constructing your own genomic data or by reusing <code>ecoli.dat</code>.</p>
<p>In your Analysis, describe the process, the results, and the reasoning you used to conclude the code is O(B). Please <em>write down</em> all the data you gathered, including timings, that demonstrate this O(B) behavior; <em>graph</em> the data to strengthen your case, and <em>explain</em> your data by demonstrating you understand the process.</p>
