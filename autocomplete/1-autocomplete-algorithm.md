---
layout: page
title: "The Autocomplete Algorithm"
assignment: "autocomplete"
---

Autocomplete is an algorithm used in many modern software applications, such as search engines, mobile texting/typing, and web browsers. In all of these applications, the user types text and the application suggests possible completions for that text:

<img src="img/browser_example.png" alt="Splice">
<img src="img/google_example.png" alt="Splice">

While finding terms that contain or start with a query is trivial, autocomplete applications need to then filter through those terms and pick the most likely ones (since users will likely not comb through thousands of terms, nor will obscure terms like "antidisestablishmentarianism" be useful to most users). 

Thus, autocomplete algorithms do not only need to have a way of finding terms that start with or contain the prefix, but also a way of determining how likely each one is to be useful to the user. For example, web browsers might track the number of times each page has been visited and consider the most visited pages or recently visited pages to be more likely to be useful. Google might track past searches and suggest similar terms for future searches. 

Autocomplete does not just need to suggest useful terms, but according to one study, it must do so in at most 50 milliseconds. Any slower, and the user will already be inputting the next keystroke, which will require the uncompleted algorithm to discard its results and start again (while humans do not on average actually input one keystroke every 50 milliseconds, additional time is required for factors like server communication and input delay). Furthermore, the server must run this computation for every keystroke, and for every user.

In this assignment, you will be implementing autocomplete using several different algorithms and seeing which ones are faster in certain scenarios. Our autocomplete will be different than the industrial examples described above in two ways:
<li>First off, each term will have a predetermined, constant weight/likelihood, whereas actual autocomplete algorithms might change a term's likelihood based on previous searches.</li>
<li>Second, we will only consider terms which start with the user query, whereas actual autocomplete algorithms (such as the web browser example above) might consider terms which contain but do not start with the query.</li>