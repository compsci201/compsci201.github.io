---
layout: page
title: "Linked List Overview"
assignment: "dna"
---
<p>
You’ll be creating a class LinkStrand that implements a Java interface IDnaStrand. The class simulates cutting a strand of DNA with a restriction enzyme and appending/splicing-in a new strand.
</p>

<p>
You must use a linked-list to support the operations — specifically the class LinkStrand should maintain pointers to a linked list used to represent a DNA strand. You should maintain a pointer to the first Node of the linked list and to the last Node of the linked list. These pointers are maintained as class invariants. A class invariant is a property that must hold true after the constructor is finished and at the entry and exit of all public member functions. In this case the property of pointing to  first/last nodes must hold after any method in the class executes (and thus before any  method in the class executes). A Strand of DNA is initially represented by a linked list   with one Node; the Node stores one string representing the entire strand of DNA. Thus,  initially the instance variables myFirst and myLast will point to the same node. If any nodes are appended,	the	value of myLast must be	updated	to ensure that it correctly	points to the last node	of the new list.
</p>

<p>
You’ll need a nested/inner class to represent a Node of	
the	linked list	for	storing	DNA	information. Here is what that should look like:
<br>
<code>
Outer class definition.... <br> 
public class Node { <br>
&nbsp;&nbsp;&nbsp;&nbsp; String info; <br>
&nbsp;&nbsp;&nbsp;&nbsp; Node next; <br>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;Node(String s){ <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; info = s; <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; next = null; <br>
&nbsp;} <br>
} <br>
<br>
private Node myFirst, myLast; //first and last nodes of list <br>
private long mySize; //# nucleotides in DNA <br>
...rest of class
</code>
</p>

<p>
The diagram below shows the results of cutting an original strand of DNA at three points and then splicing-in the strand “GTGATAATTC” at each of the locations at which the original strand was cut. Since splicing into a linked list is a constant-time, O(1) operation this implementation should be more efficient in time and space when compared to the String implementation.
<br>
<img src="img/splicediagram.jpg" alt="Node">
</p>

<h2> How to Test Your Code </h2>
<p>
To test your LinkStrand class a JUnit tester has been provided for you. The tester will test individual methods in your class. If you need a refresher on JUnit refer to Recitation 5 Slides and Code. <br>
Note: Passing these tests doesn't gaurantee full credit since the tests are about correctness, not efficieny. <br>Implementing LinkStrand is the bulk of the coding work for this assignment. You’ll need to implement every method and use the JUnit tests to help determine if your methods are correctly implemented. </p>
