# cs201-home
This should contain links to all the Duke CS201 assignments.
 
## Creating new assignments
To create new assignments, create a folder named that assignment in lowercase, e.g. "dna".

Then add it in the _config.yml file, under assignments: e.g. "- title: dna".

Put an index.md file: this will be the first page that it goes to. Pages should have the following frontmatter:

	---
	layout: page
	title: "Title of Page"
	assignment: "name-of-assignment"
	---

Then, put the following in a file named printer-friendly.md:

	---
	layout: printer-friendly
	title: "-name of assignment- - Printer friendly"
	assignment: "-name of assignment-"
	---

Your additional new assignment pages should be numbered: e.g. "2-dna-second-page.md".

The printer-friendly page, navbar, and titles will all be taken care of automatically.

---

## TODO

- Pagination
- Custom domain (cs.duke.edu)
- Refactor assignment creation and printer-friendly code
- Clean up & update FAQs
