---
layout: page
title: "Extra Enrichment"
assignment: "nbody"
---

Nothing on this page is for credit - instead, this page offers those of you who found this assignment interesting ways to immerse yourself further.

####More on the leapfrog method
Are you surprised that the update formula for position uses the velocity at the updated time step rather than the previous one? Or did you expect the formula for position to be p<sub>x</sub> = v<sub>x</sub>Δt + 1/2 a<sub>x</sub>(Δt)<sup>2</sup>? Here's an explanation.

The classic Euler method updates the position uses the velocity at time t instead of using the updated velocity at time t + Δt. A better idea is to use the velocity at the midpoint t + Δt / 2. The leapfrog method does this in a clever way. It maintains the position and velocity one-half time step out of phase: At the beginning of an iteration, (p<sub>x</sub>, p<sub>y</sub>) represents the position at time t and (v<sub>x</sub>, v<sub>y</sub>) represents the velocity at time t - Δt / 2. Interpreting the position and velocity in this way, the updated position (p<sub>x</sub> + Δt v<sub>x</sub>, p<sub>y</sub> + Δt v<sub>y</sub>). uses the velocity at time t + Δt / 2. Almost magically, the only special care needed to deal with the half time-steps is to initialize the system's the velocity at time t = -Δt / 2 (instead of t = 0). Note also that the acceleration is computed at time t so that when we update the velocity, we are using the acceleration at the midpoint of the interval under consideration.

The leapfrog method has several important advantages (over class numerical integration methods such as Euler or Runge-Kutta) for integrating Hamiltonian systems. (See Feynman Lectures on Physics, Vol. I, Sec. 9.6.) The leapfrog method is symplectic, which means it preserves properties specific to Hamiltonian systems (conservation of linear and angular momentum, time-reversibility, and conservation of energy of the discrete Hamiltonian). In contrast, ordinary numerical methods become dissipative and exhibit qualitatively different long-term behavior. For example, the earth would slowly spiral into (or away from) the sun. For these reasons, symplectic methods are extremely popular for N-body calculations in practice. 

####Additional challenges
Challenges for the bored. There are limitless opportunities for additional excitement and discovery here. Try adding other features, such as:

<li>supporting elastic or inelastic collisions</li>
<li>make the simulation three-dimensional by doing calculations for x-, y-, and z-coordinates, then using the z coordinate to vary the sizes of the planets</li>
<li>add a rocket ship that launches from one planet and has to land on another. Allow the rocket ship to exert force with consumption of fuel</li>
<li>make the solution more efficient</li>

In addition, or a more algorithmic challenge, try implementing [Barnes-Hut](http://en.wikipedia.org/wiki/Barnes-Hut_simulation), which approximates the N-body problem using substantially fewer calculations.

####Work others have done with Nbody

[Here](http://en.wikipedia.org/wiki/Barycenter) are some interesting two-body systems. [Here](http://cosmicvariance.com/2006/07/23/n-bodies/) is a nifty 21-body system in a figure-8.

[Here](http://www.art.net/~simran/GenerativeMusic/Kepler.html) is a website that generates music using an N-body simulator!

[Here](http://www.schlitt.net/xstar/n-body.pdf) is a wealth of information on the N-body simulation problem

####Applications

N-body simulations play a crucial role in our understanding of the universe. Astrophysicists use it to study stellar dynamics at the galactic center, stellar dynamics in a globular cluster, colliding galaxies, and the formation of the structure of the Universe. The strongest evidence we have for the belief that there is a black hole in the center of the Milky Way comes from very accurate N-body simulations. Many of the problems that astrophysicists want to solve have millions or billions of particles. More sophisticated computational techniques are needed. 

The same methods are also widely used in molecular dynamics, except that the heavenly bodies are replaced by atoms, gravity is replaced by some other force, and the leapfrog method is called Verlet's method. With van der Waals forces, the interaction energy may decay as 1/R<sup>6</sup> instead of an inverse square law. Occasionally, 3-way interactions must be taken into account, e.g., to account for the covalent bonds in diamond crystals.