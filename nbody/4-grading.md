---
layout: page
title: "Grading"
assignment: "nbody"
---

## Testing your code
We will be grading your project by calling the method `positions` on test cases, and seeing if your output matches the expected output. You can test whether <code>positions</code> is producing the correct array by inserting the following code into your main method:

<pre><code>
double[][] test = myNBody.positions(new Scanner(new File("data/planets.txt")), 100000, 25000);
for(int i = 0; i < test.length; i++) {
    for(int j = 0; j < test[i].length; j++) {
        System.out.print(test[i][j]+" ");
    }
    System.out.println();
}
</code></pre>

This should output the following in console:

<samp>
1.4956294976553436E11 2.2788403506932733E11 <br>
5.765266792231076E10 330.8671434437699 <br>
1.0812917648253027E11 2.9798154903465266E9 <br>
2.409957795285363E9 4.784879361438484E9 <br>
2.820200096518895 3.499427153160241E9 <br>
</samp>

## Automated Tests
Below is a list of aspects of your code the automated tests will check. Your code will still be checked by a TA, and passing all these tests does not guarantee a perfect grade. 

<ol>
<li>Does the <code>force</code> method return the right output?</li>
<li>Does the <code>distance</code> method return the right output?</li>

<li>Does the <code>positions</code> method return the right output?</li>
<li>Does the <code>positions</code> method process files with comments correctly?</li>
<li>Does the <code>positions</code> method use <code>.next()</code>, <code>.nextInt()</code>, and <code>.nextDouble()</code> to process files?</li>
<li>Does the <code>positions</code> method process universes with only one or two bodies correctly?</li>
<li>Does the <code>positions</code> method handle odd time steps correctly/is the timing for loop written correctly? (e.g. running with <code>timeStep</code> = 25000, and 25001 should both update positions twice using <code>totalTime</code> = 50000, but <code>timeStep</code> = 24999 should update thrice)</li>
<li>Does the <code>positions</code> method process universes with 100+ bodies?</li>
<li>Does the <code>positions</code> method call <code>StdDraw.show()</code> the correct number of times?</li>
</ol>

## Point Breakdown

This assignment is worth 25 points. 
<ul>
<li>	<strong>85% algorithmic/correctness:</strong> for the correctness of your implementation of NBody (based largely on whether it passes our automated tests).</li>
<li>	<strong>5% engineering:</strong> for the structure and style of your NBody implementation. Does your solution decompose the problem appropriately? Is your code formatted appropriately?</li> 
<li> <strong>10% analysis:</strong> for your README.txt and answers to the analysis questions.</li>
</ul>
