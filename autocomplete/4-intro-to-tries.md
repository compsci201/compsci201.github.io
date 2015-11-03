---
layout: page
title: "Introduction to Tries"
assignment: "autocomplete"
---

You may already have some familiarity with what a trie is, and you should have familiarity with what a tree is. Regardless, completing TrieAutocomplete is very difficult without a mastery of tries, so this page will cover the basics of Tries and their functionality.

###What Is A Trie?

A trie is simply a special version of a tree. In some trees, each node has a defined number of children, and a fixed way to refer to each of them. For example, in a binary tree, each node has two children (each of which could be null), and we refer to these as a left and a right child. Tries are different in that each node can have an arbitrary number of children, and rather than having named pointers to children, the pointers are the values in a key-value map. 

So, while a node in a Java binary tree might be defined as 

<pre><tt>
public class Node{
    Node myLeft, myRight;
}
</tt></pre>

A node in a Java trie might look like

<code>
public class Node{
    Map<Character, Node> children;
}
</code>

(Note that the <code><a href="code/Node.html">Node</a></code> class given to you has much more information than this)

The keys of the map will also correspond to some value. For example, for a trie that stores many Strings (as we wish to in this assignment), the keys will be characters. In order to reach the node representing a word in a trie, we simply follow the series of pointers corresponding to the characters in the String. Below is a drawing of a sample word trie: 

<img src = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Trie_example.svg/400px-Trie_example.svg.png">

>######From the [Wikipedia page on Tries](https://en.wikipedia.org/wiki/Trie)

The top node is the root. It has three children, and to get to these children we have to use the keys t, A, and i. The word each node represents is the concatenation of the keys of pointers you have to take from the root to get to that node. So, to get to "tea" from the root, we have to follow the root's t pointer, then the e pointer, then the a pointer. 

More generally, to get to a node representing a word in the String str, given a root pointer we might use the following code loop:

<pre><tt>
<font color="#000000">    </font><font color="#000000">Node</font><font color="#000000"> curr </font><font color="#000000">=</font><font color="#000000"> root</font><font color="#000000">;</font>
<font color="#000000">    </font><b><font color="#7f0055">for</font></b><font color="#000000"> </font><font color="#000000">(</font><b><font color="#7f0055">int</font></b><font color="#000000"> k </font><font color="#000000">=</font><font color="#000000"> </font><font color="#000000">0</font><font color="#000000">;</font><font color="#000000"> k </font><font color="#000000">&lt;</font><font color="#000000"> str</font><font color="#000000">.</font><font color="#000000">length</font><font color="#000000">();</font><font color="#000000"> k</font><font color="#000000">++)</font><font color="#000000"> </font><font color="#000000">{</font>
<font color="#000000">        </font><font color="#000000">curr </font><font color="#000000">=</font><font color="#000000"> curr</font><font color="#000000">.</font><font color="#000000">children</font><font color="#000000">.</font><font color="#000000">get</font><font color="#000000">(</font><font color="#000000"> str</font><font color="#000000">.</font><font color="#000000">charAt</font><font color="#000000">(</font><font color="#000000">k</font><font color="#000000">)<font color="#000000">);</font>
<font color="#000000">    </font><font color="#000000">}</font>
</tt></pre>

###Trie Functionality And Utility

In creating a trie, we will of course have to add values to it. Adding a value to a trie is very similar to navigating to it. To add a value, simply try navigating to that value, but anytime a node on the path to that value is missing, create that node yourself. The code for adding the word in str to a trie might look like this:

<pre><tt><font color="#000000">        </font><font color="#000000">Node</font><font color="#000000"> curr </font><font color="#000000">=</font><font color="#000000"> root</font><font color="#000000">;</font>
<font color="#000000">        </font><b><font color="#7f0055">for</font></b><font color="#000000"> </font><font color="#000000">(</font><b><font color="#7f0055">int</font></b><font color="#000000"> k </font><font color="#000000">=</font><font color="#000000"> </font><font color="#000000">0</font><font color="#000000">;</font><font color="#000000"> k </font><font color="#000000">&lt;</font><font color="#000000"> str</font><font color="#000000">.</font><font color="#000000">length</font><font color="#000000">();</font><font color="#000000"> k</font><font color="#000000">++)</font><font color="#000000"> </font><font color="#000000">{</font>
<font color="#000000">            </font><b><font color="#7f0055">if</font></b><font color="#000000"> </font><font color="#000000">(!</font><font color="#000000">curr</font><font color="#000000">.</font><font color="#000000">containsKey</font><font color="#000000">(</font><font color="#000000">str</font><font color="#000000">.</font><font color="#000000">charAt</font><font color="#000000">(</font><font color="#000000">k</font><font color="#000000">)))</font><font color="#000000"> </font><font color="#000000">{</font>
<font color="#000000">                curr</font><font color="#000000">.</font><font color="#000000">children</font><font color="#000000">.</font><font color="#000000">put</font><font color="#000000">(</font><font color="#000000">str</font><font color="#000000">.</font><font color="#000000">charAt</font><font color="#000000">(</font><font color="#000000">i</font><font color="#000000">),</font><font color="#000000"> </font><b><font color="#7f0055">new</font></b><font color="#000000"> </font><font color="#000000">Node</font><font color="#000000">());</font>
<font color="#000000">            </font><font color="#000000">}</font>
<font color="#000000">            curr </font><font color="#000000">=</font><font color="#000000"> curr</font><font color="#000000">.</font><font color="#000000">children</font><font color="#000000">.</font><font color="#000000">get</font><font color="#000000">(</font><font color="#000000">str</font><font color="#000000">.</font><font color="#000000">charAt</font><font color="#000000">(</font><font color="#000000">i</font><font color="#000000">));</font>
<font color="#000000">        </font><font color="#000000">}</font>
</tt></pre>

(Again, please note that the Node class given to you is more detailed and this code alone is not a solution to this assignment)

We use tries because they have some useful properties, which we will take
advantage of in this project: <ul> <li>The time it takes to find an entry
in a trie is independent of how many entries are in that trie - more
specifically, to navigate to a node corresponding to a word of length
<i>w</i> in a trie that stores <i>n</i> words takes O(<i>w</i>) time as
opposed to O(f(<i>n</i>)) for some f(<i>n</i>). This improvement is
possible because every navigation to the same word passes through the same
set of nodes, and thus takes the same time regardless of what other nodes
exist.</li>

<li>All words represented in the subtrie rooted at the node representing some word start with that word. That is, the node representing "apple" will always be below the node representing "app" or more generally, every node below the node representing "app" represents a word starting with "app". For this reason, tries are sometimes called prefix trees. Given that the autocomplete algorithm is searching for words starting with a given prefix, this structure proves very useful.</li>
</ul>
