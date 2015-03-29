---
layout: page
title: "Boggle"
assignment: "boggle"

---


##**CompressedTrieLexicon**

For extra credit you must implement a lexicon based on a compressed trie data structure. The compressed trie trades space for time: it is slightly slower than a trie, but it requires less space/storage. 

The **TrieLexicon** implementation has been provided for you, we discussed the concepts in recitation, and is described below. Before you attempt the CompressedTrieLexicon make sure you understand how **TrieLexicon** works.

Your **CompressedTrieLexicon** should be a *subclass* of **TrieLexicon.** You'll write code to remove nodes with only one child as described below. A chain of nodes pointed to by one link can be compressed into a node storing a suffix rather than a single character. The picture below shows the result of compressing a trie, you should be familiar with this from recitation. 

####![Regular Trie](https://www.cs.duke.edu/courses/fall12/compsci201/assignments/boggle/trie.jpg)

####![Compressed Trie](https://www.cs.duke.edu/courses/fall12/compsci201/assignments/boggle/compressedtrie.jpg)

The red dots in the diagram indicate that the path from the root to the node represents a word. 
You can see how the regular trie works by examining the code in the **TrieLexicon** class. In particular, not that when a node has nothing below it, the path to that node represents a word that isn't a prefix of another word. Because of how the **TrieLexicon** is constructed, determining if a sequence of chatacters is a word or a prefix is fairly straightforward. Please reference the *wordStatus* method in **TrieLexicon**. Note that if the path hits a null pointer the path cannot represent either a prefix or a word since any pointer out of a node ultimately reaches a leaf that represents a word that isn't a prefix of another word. 

For example, in the tries shown above the string "toaster" would result in the code following the "t" link, then the "o", then would fail because there's no "a" link from the "o" node. 

You'll need to create a new method compress to perform this one-child compression, you'll call this method in the load method you override as below:
<code> public class CompressedTrieLexicon extends TrieLexicon {
 // some code not shown
 &nbsp;&nbsp;@Override
&nbsp;&nbsp;&nbsp; public void load(ArrayList<String> list) {
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; super.load(list);
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;compress();
&nbsp;&nbsp; }
 &nbsp;// more code not shown
}<code>

In a trie, determining whether a string is a word or prefix is an O(w) operation where W is the length of the string. (This is independent of N- the number of entries stored in the trie). 

To compress the trie you'll write code that finds every leaf. From each leaf you'll write code that follows the parent pointers back up the trie until either a node representing a word is found (case 1) or a node that has more than one child is found (case 2). 

The first case described is represented by the string "doting." We can't replace "ting" by a node with that suffix because we'd have to differentiate between "dot" and "doting" and that's hard with one node. Instead we leave "dot" and only compress "ing" below it. 

The second case is illustrated in the diagram by "drastic", "torn", and "trap." In each case the sequence of nodes with single pointers is replaced by one node with a suffix stored that represents the eliminated nodes, ie. "stic", "rn", "rap" in the diagram. Note that the number of nodes eliminated is one less than the length of the suffix stored - we need one node to store the suffix. 

The suffix of the single-node-pointing-path is stored after the parent pointers are followed. Since the trie nodes store a string, they can certainly store a suffix. You'll need to code a new version of *wordStatus* to recognize when a suffix-node is reached.