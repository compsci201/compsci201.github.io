---
layout: page
title: "Program Specifications"
assignment: "nbody"
---

Before you start programming, please read this assignment and the how-to page.

####Input Format
The input file is a text file that contains the information for a particular universe. The first value is an integer N which represents the number of particles. The second value is a real number R which represents the radius of the universe. Finally, there are N rows, and each row contains 6 values. The first two values are the x- and y-coordinates of the initial position; the second two values are the x- and y-coordinates of the initial velocity; the third value is the mass; the last value is a String that is the name of an image file used to display the particle. As an example, the input file planets.txt contains data for the inner planets of our solar system (in SI units):

<code>
5 </br>
2.50e11 </br>
1.496e11 0.000e00 0.000e00 2.980e04 5.974e24 earth.gif </br>
2.279e11 0.000e00 0.000e00 2.410e04 6.419e23 mars.gif </br>
5.790e10 0.000e00 0.000e00 4.790e04 3.302e23 mercury.gif </br>
0.000e00 0.000e00 0.000e00 0.000e00 1.989e30 sun.gif </br>
1.082e11 0.000e00 0.000e00 3.500e04 4.869e24 venus.gif </br>
</code>

Each text file will be passed to you as a [Scanner](http://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html). Keep in mind that some submissions have comments at the end - be sure you read only N lines of input instead of reading until the end of the file!

The nbody project contains the planets.txt file, images of the planets, and many other sample universes in the data folder.

####Creating an animation

Draw each particle at its current position using the [picture](http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html#picture(double, double, java.lang.String)) method in the [princeton.StdDraw](http://www.cs.princeton.edu/introcs/stdlib/javadoc/StdDraw.html) library, and repeat this process at each time step. By displaying this sequence of snapshots (or frames) in rapid succession, you will create the illusion of movement. After each time step:
<ol>
<li>draw the background image starfield.jpg, </li>
<li>redraw all the bodies in their new positions, and </li>
<li>control the animation speed using [StdDraw.show()](http://www.cs.princeton.edu/introcs/stdlib/javadoc/StdDraw.html#show(int)). </li>
</ol>

####Your program
Write the method **`positions()`** in NBody.java, which reads in the universe from a Scanner <code>info</code> and simulates its dynamics using the leapfrog scheme described above with Δt = `timeStep`,  animates it using <code>StdDraw</code>, and returns the final positions of the *n* bodies. To plot the simulation, write a for loop which runs until <code>totalTime</code> has passed and updates/plots the positions/velocities of the particles each iteration as described above. When plotting, use `StdDraw.setXscale(-R, R)` and `StdDraw.setYscale(-R, R)`. 

Your method should finish by returning a 2D array of doubles containing the x and y-positions of the *n* bodies. When you snarf the project, the method <code>positions()</code> comes with code/commented instructions on how to return the array in the format the grader expects. You also need to implement the methods <code>force()</code> and <code>distance()</code> for full credit.

When you’ve finished, you can test if your program works by running it on planets.txt. If you are using the default values time = 10,000,000 and Δt = 25,000, the simulation should show four planets orbiting the sun and stop in this position:

<img src = "img/planets_example.png" alt = "result">

and should produce [this animation](http://www.cs.duke.edu/courses/cps100e/spring10/assign/nbody/nbody-planets.mov).

To make the viewing experience more enjoyable, uncomment the commented line in main to play the theme to 2001: A Space Odyssey using `StdAudio` and the file 2001.mid. You can have the program instead play the theme to Superman using superman.mid. 

### Your analysis

In your README.txt, you will answer two questions about the simulation of the bodies described in planets.txt.

1. What is the final position of the planets after 1,000,000 seconds with a timestep (i.e. Δt) of 25,000.  
2. For what values of timeStep (e.g., Δt > T), does the simulation no longer behave correctly? That is, the planets planets no longer follow their orbits around the Sun. Explain how you arrived at your answer.
