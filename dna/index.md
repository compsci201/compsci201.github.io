---
layout: page
title: "DNA"
assignment: "dna"
---

## DNA - Overview
---

<b>Note: Before you start coding, make sure to thoroughly read this assignment, look at the powerpoint on Sakai and watch the overview done by Arun. It will save you a lot of time. </b>

The goal of this assignment is to help you gain confidence with linked lists, get practice analyzing your code, and improve your understanding of tradeoffs, while learning to use important concepts like JUnit tests, inheritance, and much more.

## ASSIGNMENT OUTLINE:	   
	   
In	   this	   assignment	   you	   will	   you	   will	   experiment	   with	   different	   implementations	   of	   a	   simulated	   restriction	    enzyme	   cutting	    (or	    cleaving)	    a	    DNA	    molecule.	    You	    will	   complete	   several	   tasks:	

   
Benchmark	    the	    given	    code	    in	    SimpleStrand.cutAndSplice().	    This	   can	   be	   done	   with	   the	   DNABenchMark	   class.	   Your	   report	   must	   show	   that	   this	   algorithm/code	   is	   O(N)	   where	   N	   is	   the	   size	   of	   the	   returned	   strand.	

   
Test	   your	   benchmarking	   by	   running	   out	   of	   memory	   and	   then	   re-­?running	   the	   simulation	   with	   more	   memory.	   This	   will	   be	   in	   the	   report.	

   
Design,	    code,	    and	    test	    LinkStrand	    that	    implements	    IDnaStrand	    (like	   SimpleStrand).	   It	   should	   pass	   the	   tests	   in	   TestStrand.	   


4. Run	    experiments	    to	    show	    that	    LinkStrand	    is	    O(B)	    for	    a	    strand	    with	    B	   breaks	   as	   described	   below.	   


	   
Before we get started, we need the starting skeleton code. It is available through Snarf (using [Ambient](https://www.cs.duke.edu/csed/ambient/)). You can also download the equivalent .jar file [here](/dna/src/DNA.jar)

You can also view/download the individual classes:

[DNABenchmark.java](/dna/code/DNABenchmark.html)

[IDnaStrand.java](/dna/code/IDnaStrand.html)

[LinkStrand.java](/dna/code/LinkStrand.html)

[SimpleStrand.java](/dna/code/SimpleStrand.html)

[TestStrand.java](/dna/code/TestStrand.html)

[Here](/dna/printer-friendly) is a printer friendly version of this assignment.

### Assignment Overview

In	this	assignment	you	will	you	will	experiment	with	different	implementations	of	a	
simulated restriction	 enzyme cutting	 (or	 cleaving)	 a	 DNA	 molecule. Refer to the [background theory](/dna/theory.html) for more information. You	 will
complete four	tasks:

<ol>
<li> Benchmark	 the	 given	 code	 in	 <code><a href="code/SimpleStrand.html">SimpleStrand</a>.cutAndSplice()</code>. This	can	be	done	with	the	DNABenchMark class.	Your	report	must	show	that	this	algorithm/code	is	O(N)	where	N	is	the	size	of	the	returned	strand.</li>
<li> Benchmark the amount of memory required by <code><a href="code/SimpleStrand.html">SimpleStrand</a></code> by varying  th amount of memory allocated to the program.</li>
<li> Design,	 code,	 and	 test	 <code><a href="code/LinkStrand.html">LinkStrand</a></code> that	 implements	 <code><a href="code/IDNAStrand.html">IDNAStrand</a></code>.	Your implementation	should	pass	the	JUnit tests	in	<code><a href="code/TestStrand.html">TestStrand</a></code>.</li>
<li> Run	 experiments	 to	 show	 that	 <code><a href="code/LinkStrand.html">LinkStrand</a></code> is	 O(B)	 for	 a	 strand	 with	 B	breaks	as	described	below.</li>
</ol>
