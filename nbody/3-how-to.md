---
layout: page
title: "How To/Suggestions"
assignment: "nbody"
---

####Possible Plan
We suggest you implement the algorithm for `positions` in the following steps.

<ol>
<li> Read in the data file planets.txt using a <a href="http://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html">Scanner</a> and store the information in six arrays.

<p>Let <code>px[i], py[i], vx[i], vy[i]</code>, and <code>mass[i]</code> be real numbers which store the current position (x and y coordinates), velocity (x and y components), and mass of planet i. Let <code>image[i]</code> be a string which represents the filename of the image used to display planet i. To test that you read it in correctly, print the information back out using <code>System.out.println().</code></p>

<p><strong>Do not even think of continuing until you have checked that you read in the data correctly.</strong> Use Scanner's <code>next()</code> method to return the next word, <code>nextInt()</code> to get the next integer, and <code>nextDouble()</code> to get the next real number.</p></li>

<li> Plot the background starfield.jpg image. Note that <code>StdDraw.picture(x, y, file)</code> centers the picture on (x, y). Test that it works. Now, write a loop to plot the N bodies. If all goes correctly, you should see the four stationary planets and the sun. Now, go and test it on another data file.</li>

<li><p>Write a loop to calculate the new velocity and position for each body using <code>timeStep</code> (This code goes before the plotting code you wrote in the previous step). Since we haven't yet incorporated gravity, assume the acceleration acting on each planet is zero. </p>
<li><p>Add an outer loop to repeat the previous two steps for the duration of <code>totalTime</code>. Test it. You should now see the four planets moving off the screen in a straight line, with constant velocity. Test it on another data file to be sure.</p></li>

<li>Now, calculate the net force acting on each body. You will need two additional arrays <code>fx[i]</code> and <code>fy[i]</code> to store the net force acting planet i. First, write a loop to initialize all the net forces to 0.0. Then write two nested for loops to calculate the net force exerted by planet j on planet i. Add these values to fx[i] and fy[i], but skip the case when i equals j. Once you have these values computed, you can use them to compute the acceleration (instead of assuming it is zero). Test your program on several data files.</li>
</ol>
