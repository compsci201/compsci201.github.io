---
layout: page
title: "Grading"
assignment: "nbody"
---

## Testing your code
We will be grading your project by calling the method `positions` on test cases, and seeing if the output matches the expected output. You can test whether `positions` is producing the correct array by inserting the following code into your main method:

```java
  double[][] test = myNBody.positions(new Scanner(
        new File("data/planets.txt")), 100000, 25000);
  for(int i = 0; i < test.length; i++) {
    for(int j = 0; j < test[i].length; j++) {
      System.out.print(test[i][j]+" ");
    }
    System.out.println();
  }
```

This should output the following in console:

```
-6.1693879017391205E10 1.112170146000611E11 -2.374012784923237E10 1213453.9629152862 -1.0733376883232066E11
1.3623638588516254E11 1.9831521210186227E11 5.234764234848518E10 1457675.4698118812 -1.2427747974008638E10
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
<li>Does the positions method call StdDraw.show() the correct number of times?</li>
</ol>
