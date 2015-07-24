---
layout: page
title: "Grading"
assignment: "nbody"
---

We will be grading your project by calling the method nBodyPositions on test cases, and seeing if the output matches the expected output. You can test whether nBodyPositions is producing the correct array by inserting the following code into your main method:

<code>
&nbsp;&nbsp;double[][] test = myNBody.nBodyPositions(new Scanner(<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;new File("data/planets.txt")), 100000, 25000);<br>
&nbsp;&nbsp;for(int i = 0; i < test.length; i++){<br>
&nbsp;&nbsp;&nbsp;&nbsp;for(int j = 0; j < test[i].length; j++){<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.print(test[i][j]+" ");<br>
&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;System.out.println();<br>
&nbsp;&nbsp;}<br>
</code>

This should output the following in console:

<code>1.4956294976553436E11 2.2788403506932733E11 5.765266792231076E10 330.8671434437699 1.0812917648253027E11 <br>
2.9798154903465266E9 2.409957795285363E9 4.784879361438484E9 2.820200096518895 3.499427153160241E9 
</code>

For transparency's sake, below is a list of aspects of your code the automated tests will check. Your code will still be checked by a TA, and passing all these tests does not guarantee a perfect grade. 

<li>Does the force method return the right output?</li>
<li>Does the distance method return the right output?</li>
<li>Does the nBodyPositions method return the right output?</li>
<li>Does the nBodyPositions method process files with comments correctly?</li>
<li>Does the nBodyPositions method use .next(), .nextInt(), and .nextDouble() to process files?</li>
<li>Does the nBodyPositions method process universes with only one or two bodies?</li>
<li>Does the nBodyPositions method handle odd time steps correctly/is the timing for loop written correctly? (e.g. running with timeStep = 25000, and 25001 should both update positions twice using totalTime = 50000, but timeStep = 24999 should update thrice)</li>
<li>Does the nBodyPositions method process universes with 100+ bodies?</li>
<li>Does the nBodyPositions method call StdDraw.show() the correct number of times?</li>
>