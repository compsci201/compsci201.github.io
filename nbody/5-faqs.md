---
layout: page
title: "FAQs"
assignment: "nbody"
---

####My computer doesn't display the animation smoothly. Is there anything I can do? 

Use StdDraw.show(30) to turn on the animation mode of standard draw. Call it once at the end of each time step, not after each each drawing command.

####Can I combine all the steps from the [Equations Behind NBody](/nbody/1-physics) page into one large for loop?

No! This will simultaneously screw up the physics and make your code harder to understand and debug.

####I draw the planets, but nothing appears on the screen. Why? 

Use [StdDraw.setXscale()](http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html#setXscale(double, double)) and [StdDraw.setYscale()](http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html#setYscale(double, double)) to change the coordinate system to use the physics coordinates instead of the unit box.

####My planets don't move. 

Make sure that you are using a large enough value of Δt (we specify 25000, but you might want a smaller one when debugging).

####My planets move, but leave images of themselves behind.

Make sure you draw starfield.jpg once per loop before drawing all your planets, instead of once for the entire simulation.

####I'm confused about all of the Δt / 2 terms. Do I need to worry about them to get the physics right? 

No! The update formulas for velocity and position already take this into account. As a technicality, the leapfrog method should be initialized with the velocity at time t = -Δt / 2, so we'll assume this is the value in the input file. In real codes, special care must be made to deal with this.

####I'm a physicist. Why should I use the leapfrog method instead of the formula I derived in high school? 

The leapfrog method is more stable for integrating Hamiltonian systems than conventional numerical methods like Euler's method or Runge-Kutta. The leapfrog method is symplectic, which means it preserves properties specific to Hamiltonian systems (conservation of linear and angular momentum, time-reversibility, and conservation of energy of the discrete Hamiltonian). In contrast, ordinary numerical methods become dissipative and exhibit qualitatively different long-term behavior. For example, the earth would slowly spiral into (or away from) the sun. For these reasons, symplectic methods are extremely popular for N-body calculations in practice. You asked!

####My planets repel each other. Why don't they attract each other? 

Make sure that you get the sign right when you apply Newton's law of gravitation: (x[j] - x[i]) vs. (x[i] - x[j]). Note that Δx and Δy can be positive or negative so do not use Math.abs(). Do not consider changing the universal gravitational constant G to patch your code!

####How should I compute x<sup>2</sup>? 

The simplest way is <code>x*x</code>. In Java, the ^ operator means XOR (not exponentiation).

####How should I compute the square root of x? 

Use [Math.sqrt](http://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#sqrt-double-) or [Math.pow](http://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#pow-double-double-). The return value of <code>Math.pow(a, b)</code> is a<sup>b</sup>.

####What is Δx? 

It's the change in x-coordinate between two bodies (not between a body at time t and at time t + Δt).

####When I compile NBody.java, it says "cannot resolve symbol StdDraw." Any thoughts? 

Make sure that you import princeton.* and add princeton.jar [to your library build path](http://www.cs.duke.edu/courses/cps004g/fall07/assign/final/shotgun/addlibrary.html).

####When should my planets move?

Since we are doing a leapfrog calculation, you should calculate every net force experienced by the objects in the system before you cause them to move. A leapfrog calculation can be thought of in terms of a snapshot: you take a snapshot of the system with the planets held in place, calculate the force, acceleration, velocity, and position, then you allow the planets to move for the amount of time specified by timeStep. This repeats until the simulation terminates.
