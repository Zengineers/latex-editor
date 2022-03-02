# Latex Editor

Latex Editor is a Java application that was reengineered and extended during the [Software Development 2](https://www.cs.uoi.gr/course/software-engineering-ii/?lang=en) course [@cse.uoi.gr](https://www.cs.uoi.gr/).<br><br>

### Summary
 
The goal of the project was to reengineer and extend an existing Java application. The
application is a simple Latex editor for inexperienced Latex users. The first phase of the project revolved around understanding the application’s legacy architecture. We studied the
documentation, user stories and source code and set up a mock installation of the application.
Moreover, test cases were prepared for each of the user stories and the design was captured
in UML package and class diagrams. In the second phase refactoring and extension was
performed, aiming to improve code quality and extensibility as well as usability for both
developers and users. Refactoring techniques such as [Extract Method](https://refactoring.guru/extract-method), [Move Method](https://refactoring.guru/move-method),
[Substitute Algorithm](https://refactoring.guru/substitute-algorithm), [Singleton Pattern](https://refactoring.guru/design-patterns/singleton), [Remove Middle Man](https://refactoring.guru/remove-middle-man) and [Externalize Strings](https://stackoverflow.com/questions/38578507/what-is-string-externalization)
were used to address issues such as [Duplicate Code](https://refactoring.guru/smells/duplicate-code), [Long Method](https://refactoring.guru/smells/long-method), [Dead Code](https://refactoring.guru/smells/dead-code), [Message Chains](https://refactoring.guru/smells/message-chains) and [Misplaced Responsibilities](https://moderatemisbehaviour.github.io/clean-code-smells-and-heuristics/general/g17-misplaced-responsibility.html). The application was also extended to support two more user stories regarding HTML document import and export. The refactored design was
captured in UML package and class diagrams as well as CRC cards.

<br>

### The Latex Editor Application

Latex Editor is a simple editor aiming to assist inexperienced Latex users with Latex document
preparation. Latex is a high-quality document preparation markup language. It provides a
large variety of styles and commands that enable advanced document formatting. Formatting
documents with Latex is similar to a programming process as it involves the proper usage of
Latex commands which are embedded in the document contents. The goal of the Latex Editor
is to facilitate the usage of Latex commands for the preparation of Latex documents. One of
the prominent features that distinguishes the Latex Editor from other similar applications is its multi-strategy version tracking functionality that enables undo actions.

<br>
More about Latex:

[1] [wikipedia](https://en.wikipedia.org/wiki/LaTeX)<br>
[2] [latex-project](https://www.latex-project.org/)

<br>

### Features

Users can:
-   Create a Latex document based on a document tamplate.
-   Edit the contents of a Latex document.
-   Add Latex commands in the Latex document using application's UI.
-   Active automatic version tracking that keeps track of the document evolution history.
-   Select between two different strategies of document version tracking.
-   Disable the automatic version tracking at any time.
-   Rollback to previous version of the Latex document based on the document evolution history.
-   Save the Latex document on disk storage.
-   Load a Latex document from disk storage.
-   Import an HTML document as a Latex document.
-   Export a Latex document as an HTML document.

<br>

### Usage

Run the executable jar found in releases.
<br>
Alternatively, the application is also ready to be imported into Eclipse IDE.

<br>

### Credits

<p align="center">
  <img src="https://avatars.githubusercontent.com/u/94444618?s=400&u=665ca7ded45c1a0edc43742040bd8bf5813083c9&v=4" alt="Zengineers Logo" width="300" height="300">
  <br>
  <br>
  Tsiouri Angeliki
  <br>
  Antoniou Christodoulos
</p>
