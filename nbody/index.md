---
layout: page
title: "NBody Simulation"
assignment: "nbody"
---

##NBody - Overview
---

This assignment and writeup was originally developed by [Kevin Wayne](https://www.cs.princeton.edu/~wayne/) and [Robert Sedgewick](https://www.cs.princeton.edu/~rs/) at Princeton University. 

You can download the skeleton code from the snarf site using Ambient, or the equivalent .jar to import from [here](/nbody/src/NBody.jar). Submit the assignment to the nbody folder. You should only need to submit your README and the NBody.java class. Make sure you submit the latter within the src folder.

You can also view/download the NBody class here:

[NBody.java](/nbody/code/NBody.html)

We suggest you read the "Equations Behind NBody", "Program Specifications", and "Suggestions/How To" sections before writing any code.

[Here](/nbody/printer-friendly) is a printer friendly version of this assignment.

###Background

In 1687 Sir Isaac Newton formulated the principles governing the the motion of two particles under the influence of their mutual gravitational attraction in his famous Principia[1]. However, Newton was unable to solve the problem for three particles. Indeed, in general, systems of three or more particles can only be solved numerically. The N-Body problem is a reoccuring problem in many disciplines - from Biology and Biochemistry to Physics and Astronomy to Hydrology and Aerodynamics[2].

In this assignment, you will write a program to simulate the motion of N particles, mutually affected by gravitational forces, and animate the results. Such methods are widely used in cosmology, semiconductors, and fluid dynamics to study complex physical systems. Scientists also apply the same techniques to other pairwise interactions including Coulombic, Biot-Savart, and van der Waals.

###References
[1] Newton, Isaac. Philosophiae naturalis principia mathematica. 1713.

[2] Lubomir Ivanov, "The N-Body problem throughout the Computer Science Curriculum"
