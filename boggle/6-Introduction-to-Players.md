---
layout: page
title: "Introduction to Players"
assignment: "boggle"

---


Details about the classes you write for this part of the assignment and help in writing them are provided on the next page. 

Here’s a diagram of some of the classes and interfaces in the player hierarchy. You’ll implement the two classes at the bottom of the diagram: **BoardFirstAutoPlayer** and **LexiconFirstAutoPlayer**:

####![Boggle Board Example](https://www.cs.duke.edu/courses/fall12/compsci201/assignments/boggle/PlayerClassDiagram.jpg)

Each class you will add code to extends **AbstractAutoPlayer** and thus implements the **IPlayer** interface. When you implement method *findAllValidWords* you should first set the autoplayer’s score to zero and then clear any words already stored (see the code you’re given in **LexiconFirstAutoPlayer** – you do this by calling the inherited method clear(). Remember that since you inherit all the methods from **AbstractAutoPlayer** you can call them in the subclasses you write. If you choose to override an inherited method you should use the @Override annotation, but for the auto‐player classes you likely don’t need to override any methods, you simply need to implement *findAllValidWords*. You may find it useful to implement helper methods as well. 

You’re given one class **LexiconFirstAutoPlayer** and you’ll implement a new AutoPlayer that also finds all the words on a Boggle board. Each class uses a different technique and you’ll analyze the runtime tradeoffs in these techniques. You’ll also reason empirically about the performance of these two classes when they’re configured with different implementations of **ILexicon** lexicons. 