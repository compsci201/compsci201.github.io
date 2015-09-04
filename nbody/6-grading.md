---
layout: page
title: "Grading"
assignment: "nbody"
---

## Testing your code
We will be grading your project by calling the method `positions` on test cases, and seeing if the output matches the expected output. You can test whether nBodyPositions is producing the correct array by inserting the following code into your main method:

{% highlight java %}
  double[][] test = myNBody.positions(new Scanner(
        new File("data/planets.txt")), 100000, 25000);
  for(int i = 0; i < test.length; i++) {
    for(int j = 0; j < test[i].length; j++) {
      System.out.print(test[i][j]+" ");
    }
    System.out.println();
  }
{% endhighlight %}

This should output the following in console:

```
1.4956294976553436E11 2.2788403506932733E11 
5.765266792231076E10 330.8671434437699 
1.0812917648253027E11 2.9798154903465266E9 
2.409957795285363E9 4.784879361438484E9 
2.820200096518895 3.499427153160241E9 
```

## Automated Tests
Below is a list of aspects of your code the automated tests will check. Your code will still be checked by a TA, and passing all these tests does not guarantee a perfect grade. 

<ol>
<li>Does the force method return the right output?</li>
<li>Does the distance method return the right output?</li>
<li>Does the positions method return the right output?</li>
<li>Does the positions method process files with comments correctly?</li>
<li>Does the positions method use .next(), .nextInt(), and .nextDouble() to process files?</li>
<li>Does the positions method process universes with only one or two bodies?</li>
<li>Does the positions method handle odd time steps correctly/is the timing for loop written correctly? (e.g. running with timeStep = 25000, and 25001 should both update positions twice using totalTime = 50000, but timeStep = 24999 should update thrice)</li>
<li>Does the positions method process universes with 100+ bodies?</li>
<li>Does the nBodyPositions method call StdDraw.show() the correct number of times?</li>
</ol>

## Point Breakdown

This assignment is worth 25 points. 
<ul>
<li>	85% algorithmic/correctness: for the correctness of your implementation of NBody - based largely on whether it passes our automated tests</li>
<li>	5% engineering: for the structure and style of your NBody implementation. Does your solution decompose the problem appropriately? Is your code formatted appropriately?</li> 
<li> 10% analysis: your README.txt and answers to the analysis questions</li>
</ul>
