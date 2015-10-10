---
layout: page
title: "Overview of LinkStrand Methods"
assignment: "dna"
---
<p>
Because <code>LinkStrand</code> implements the <code>IDnaStrand</code> interface, all the methods you are overriding are documented with comments, so you should check there if you are wondering how any methods should work. You can also refer to the working methods in <code>SimpleStrand</code>, although they will differ in implementation because they use a StringBuilder instead of a linked list. Make sure you correctly implement every method specified in the interface. Below are some considerations for you as you begin to code:
</p>

<ul> 
Constructor and initializeFrom:
<li>Implement both with the same code (either copy the code or re-use the common code in one method)</li>
<li>When creating/initializing a new LinkStrand only one node should be created. This node represents the entire string representation of DNA.</li>
<li>Be sure to initialize the length of the simulated strand.</li>
<li> Note: DNA Benchmark requires LinkStrand to have an empty constructor, i.e. <br> <code> 
  public LinkStrand(){ this(""); }</code></li>
</ul>

<ul> 
append:
<li>Implementation should not concatenate strings, but instead create new nodes</li>
<li>If you’re appending a LinkStrand you should do so by redirecting pointers; you should not convert the strand to a string. You should be able to add any LinkStrand in O(1) time.</li>
<li>If you're appending another type of Strand, (such as a SimpleStrand), you should convert the strand to a string and then append it, you can refer to the implementation in Simple Strand. (Hint: the instanceof operator will be helpful)</li>
</ul>

<ul>
toString:
<li>Remember, your only class variables should be Node pointers and primitives. This means you should not use a <b>global</b> StringBuilder/String to keep track of the toString value (you can still use StringBuilders/Strings within the individual methods). </li>
<li>Because of the way String addition works, combining many long Strings using a StringBuilder is faster than using +=.   </li>
</ul>

<ul>
cutandSplice:
<li> This method is NOT a mutator - the LinkStrand cutAndSplice is called from should be unchanged, and the method should be returning a new instance of LinkStrand. </li>
<li> You may assume there is only one node (though it may contain a huge String of DNA). If there is more than one node throw an exception, e.g. <code>
if(myFirst.next != null)) {throw new RuntimeException("link strand has more than one node")}; </code> </li>
<li> The implementation of this method should be virtually identical to the code in SimpleStrand except you'll be creating LinkStrand objects and calling LinkStrand.append to create the recombinant strand.</li>
<li>Virtually identical means you simply replace SimpleStrand objects with LinkStrand objects and then the code should work. (Refer to the code in SimpleStrand to help you.)</li>
<li> The diagram below gives an indication as to why the operation of splicing in a new strand will be O(B) for cutting at B places. The string splicee is the same for all of the Nodes being spliced in. Creating the node is therefore O(1) because the String splicee is already created.</li>
<img src="img/link-splice-memory.png" alt="Splice" style="width:400px;height:300px"> 
</ul>

<ul>
reverse: 
<li> The SimpleStrand class uses the StringBuilder.reverse() method to reverse the simulated strand – note that this method is a mutator - it changes the StringBuider object it’s called on. </li> 
<li> In your LinkStrand class you could call .toString(), create a StringBuilder, and use .reverse() to create a single node LinkStrand object, but this will not earn you full credit. For full credit an N-node list should be reversed with an N-node list. </li>
<li> For extra credit you should only reverse each unique string in the list once. This means that if a string/simulated strand of DNA has been spliced into a LinkStrand object the string should only be reversed/represented once. </li>
</ul>

<ul>
size:
<li> Once the string is initialized, the length of a LinkStrand object should be calculated in O(1) time. Consider how you change the size of a strand in other methods. </li>
</ul>

<ul>
getStats:
<li> Make sure this method displays the number of appends like in SimpleStrand. Just do that. Hint: You can refer to SimpleStrand to see how to implement getStats.</li>
</ul>

<ul>
nodeList:
<li>The returned ArrayList should be in the same order - i.e. strand.nodeList().get(0) should be the same as the value in the head of your linked list.</li>
<li>The return value for this should be generated at the time the method is called (since you are not allowed to maintain a global ArrayList).</li>
</ul>
