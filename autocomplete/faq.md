---
layout: page
title: "FAQs"
assignment: "autocomplete"
---

####Should I account for capitalization at all? 

No, do not worry about capitalization in your Autocompletor implementations. The GUI handles capitalized search terms for you, by translating the search terms and user input into lowercase before passing them to the Autocompletor, and then translating the results back to their original casing for the user display. Thus, it should never be the case when called from the GUI that your Autocompletor has to match lowercase and uppercase terms properly. In addition, our test suites will not ever test for capitalization-based cases.

####Can I use Collections.binarySearch to complete BinarySearchAutocomplete?

You can, but your solution will either be incorrect or inefficient - <code>Collections.binarySearch</code> makes no guarantees about what index it will return if there are multiple matching elements, whereas we specifically need the first or last index. You could use <code>Collections.binarySearch</code> to find the index of one matching element, and then go backwards/forwards until the first/last matching element is found, but this causes your runtime to go from O(log n) to potentially O(n) because you are now doing a linear search.

####When I try to test/benchmark my implementations, nothing happens/it runs forever on a certain test. Why is this?

This will happen if a single method call takes more than a few seconds (unlikely, since BruteAutocomplete can run in <.005s per call and the others should only improve on this time), or if there is an infinite loop. You are most likely to get infinite loops in TrieAutocomplete, since you will often be using while loops or for loops to navigate through the trie. Check the exit conditions of said loops and make sure they are achievable.

####Why do I get a NullPointerException in TrieAutocomplete?

Usually this happens when you go "off" of the trie - that is, try to visit a node that does not exist/is null - and do not realize it/check for it in your code. 

Suppose your trie contains the word "ape" but not the word "app" (or anything starting with "app") and you try to navigate to the node representing "app". At the node representing "ap", you would call <code>children.get("p")</code>, but since that node doesn't exist the call will return null, which can then lead to a NullPointerException if, for example, you try to visit one of its children or look at its weight.

To fix this, before you ever visit a node that isn't the root, check to make sure that node exists/is not null. If it is null, then react appropriately based on what method you're currently in.

Note that iterating over the key set of <code>children</code> should never cause a NullPointerException. 

####Why should my TrieAutocomplete be able to add a word that already exists?

More than anything this is a test of your ability to traverse a trie using parent pointers, but you can think of it as an 'update' method, because as mentioned, real autocomplete implementations need to update the weight of their terms frequently.