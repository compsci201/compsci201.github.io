---
layout: page
title: "DNA"
assignment: "dna"
---

## DNA - Overview
---

Welcome to the page for the DNA assignment. The goal is to help you gain confidence with linked lists, get practice analyzing your code, and understanding tradeoffs, while learning to use important concepts like JUnit tests, inheritance, and much more.

Before we get started, we need the starting skeleton code. It is available through Snarf (using [Ambient](https://www.cs.duke.edu/csed/ambient/)). You can also download the equivalent .jar file [here](/dna/src/DNA.jar)

You can also view/download the individual classes:

[DNABenchmark.java](/dna/code/DNABenchmark.html)

[IDnaStrand.java](/dna/code/IDnaStrand.html)

[LinkStrand.java](/dna/code/LinkStrand.html)

[SimpleStrand.java](/dna/code/SimpleStrand.html)

[TestStrand.java](/dna/code/TestStrand.html)

[Here](/dna/printer-friendly) is a printer friendly version of this assignment.

### Introduction to DNA

In this assignment, we will be simulating genetic experiments using code. To understand the theory behind everything we are doing, it is recommended (but not required) to read [this](/dna/theory.html).

We are modeling DNA strands using classes implementing the interface IDNAStrand. You are given a naive implementation called SimpleStrand, which stores the DNA strand as a String. We will implement our more sophisticated version in LinkStrand. We can test the correctness of our implementation using LinkStrand using the JUnit tests. We will also benchmark these implementations using the class DNABenchmark.

The first part of our assignment is to benchmark the given code in SimpleStrand.

Then, we'll test the benchmarking by changing the amount of memory required.

The main part of the project comes next: we'll implement LinkStrand, which is a more sophisticated model of a DNA strand that uses linked lists. We'll test the correctness of our implementation in TestStrand.

Finally, we will run more benchmarking experiments again to show that our new version really is more efficient.

