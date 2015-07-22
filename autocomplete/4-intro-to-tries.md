---
layout: page
title: "Introduction to Tries"
assignment: "autocomplete"
---

You may already have some familiarity with what a trie is, and you should have familiarity with what a tree is. Regardless, completing TrieAutocomplete is very difficult without a mastery of tries, so this page will cover the basics of Tries and their functionality.

###What Is A Trie?

A trie is simply a special version of a tree. In some trees, each node has a defined number of children, and a fixed way to refer to each of them. For example, in a binary tree, each node has two children (each of which could be null), and we refer to these as a left and a right child. Tries are different in that each node can have an arbitrary number of children, and rather than having named pointers to children, the pointers are the values in a key-value map. 

So, while a node in a Java binary tree might be defined as 

<code>
public class Node{<br>
&nbsp;&nbsp;&nbsp;&nbsp;Node myLeft, myRight;<br>
}
</code>

A node in a Java trie might look like

<code>
public class Node{<br>
&nbsp;&nbsp;&nbsp;&nbsp;Map<Character, Node> children;<br>
}
</code>

(Note that the Node class given to you has much more information than this)

The keys of the map will also correspond to some value. For example, for a trie that stores many Strings (as we wish to in this assignment), the keys will be characters. In order to reach the node representing a word in a trie, we simply follow the series of pointers corresponding to the characters in the String. Below is a drawing of a sample word trie: 

<img src = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Trie_example.svg/400px-Trie_example.svg.png">

>######From the [Wikipedia page on Tries](https://en.wikipedia.org/wiki/Trie)

The top node is the root. It has three children, and to get to these children we have to use the keys t, A, and i. The word each node represents is the concatenation of the keys of pointers you have to take from the root to get to that node. So, to get to "tea" from the root, we have to follow the root's t pointer, then the e pointer, then the a pointer. 

More generally, to get to a node representing a word in the String str, given a root pointer we might use the following code loop:

<code>
&nbsp;&nbsp;&nbsp;&nbsp;Node curr = root;<br>
&nbsp;&nbsp;&nbsp;&nbsp;for (int i = 0; i < str.length(); i++){<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;curr = curr.children.get(str.charAt(i));<br>
&nbsp;&nbsp;&nbsp;&nbsp;}
</code>

###Trie Functionality And Utility

In creating a trie, we will of course have to add values to it. Adding a value to a trie is very similar to navigating to it. To add a value, simply try navigating to that value, but anytime a node on the path to that value is missing, create that node yourself. The code for adding the word in str to a trie might look like this:

<code>
&nbsp;&nbsp;&nbsp;&nbsp;Node curr = root;<br>
&nbsp;&nbsp;&nbsp;&nbsp;for (int i = 0; i < str.length(); i++){<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (!curr.containsKey(str.charAt(i)){<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;curr.children.put(str.charAt(i), new Node());<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;curr = curr.children.get(str.charAt(i));<br>
&nbsp;&nbsp;&nbsp;&nbsp;}
</code>

(Again, please note that the Node class given to you is more detailed and this code alone is not a solution to this assignment)

We use tries because they have some useful properties, which we will take advantage of in this project:
<li>The time it takes to find an entry in a trie is independent of how many entries are in that trie - more specifically, to navigate to a node corresponding to a word of length w in a n-element trie takes O(w) time as opposed to O(f(n)) for some f(n). This is because every navigation to the same word passes through the same set of nodes, and thus takes the same time regardless of what other nodes exist.</li>
<li>All words represented in the subtrie rooted at the node representing the word str start with str. That is, the node representing "apple" will always be below the node representing "app" or more generally, every node below the node representing "app" represents a word starting with "app". For this reason, tries are sometimes called prefix trees. Given that the autocomplete algorithm is searching for words starting with a given prefix, this structure proves very useful in performing autocomplete.</li>
