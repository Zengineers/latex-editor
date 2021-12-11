# Latex Editor

Latex Editor is a Java application that was reengineered and extended during the Software Development 2 course [@cse.uoi.gr](https://www.cs.uoi.gr/).<br><br>

### Summary
<br>
 
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
<br>

Latex Editor is a simple editor aiming to assist inexperienced Latex users with Latex document
preparation. Latex is a high-quality document preparation markup language. It provides a
large variety of styles and commands that enable advanced document formatting. Formatting
documents with Latex is similar to a programming process as it involves the proper usage of
Latex commands which are embedded in the document contents. The goal of the Latex Editor
is to facilitate the usage of Latex commands for the preparation of Latex documents. One of
the prominent features that distinguishes the Latex Editor from other similar applications is its multi-strategy version tracking functionality that enables undo actions.

<br>
More about Latex:
<br>

[1] [wikipedia](https://en.wikipedia.org/wiki/LaTeX)

[2] [latex-project](https://www.latex-project.org/)

<br>

### Features
<br>

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
<br>

Run the executable jar found in the repo.
<br>
Alternatively, the application is also ready to be imported into Eclipse IDE.

<br>

### Credits
<br>

<p align="center">
  <img src="https://lh3.googleusercontent.com/xxXiDpRLiQN6Qmrp7gI-Q_eOLRIXclalbnV3PpWLgjuHjbRL_SSFkHMWwdRqhCF7VU4Ht2G2jqSo0UJ15p52fOoVU4dPXcGhGW564vFTtLG7PWlk3996Yt0TL18v3d6TJkGy1KJ3ud0o-FwqzXQHvwNu1xZCz4sRQCd3swkr1cERqIfW6yewuMWnJEuikkK5-w9UNcPYpbbV36sbrEzsEIs_qK_Isyi4iLY2sJFSjTO8b7syRg5Z1sS40jqG3Nsc6HEmUVCIWD3OsB7Cja22_46ROxd_SVSiRDF2qifxYQEspMhGeW6MSajhRjS37s5i4PGX3nzC3AW0ZrPquI8dcbhMV1c2-140I8ZXPJEDJFYDEdKlQIpvVRuMqfXGJx-4Q6mSDBniVx9i_NKFdAx5cg71kXfOEzEiOF7SfjPZ1-AlYRosh2IZnogIdM_swMiEBxxATtxzx2poFBh-mzFk39huGvVLOOvYOS94bl5o5gcGAfSxUpK4rfptnJ32wnBsYjb8s1NiP151o8vEKjMQ7akJT2IB4rm5xCIbWB8lpOu34NmaQqTmgQ0ujUutEqEtD2Zay-uogGLRlwBTowfR6bxHoOKppBG2YFGLC4ZuuVkExu0eQIqAkLon9SS0yenQrPEgydomkXu7RNTEI4dgWc8RbQwDvur1O5HzY8hvdzc_ys37E1GP75EkaEAhSI96WYfVP3E-mc9dGO2T-4uji4Pp=w365-h366-no?authuser=0" alt="Zengineers Logo" width="180" height="180">
  <br>
  <br>
  Tsiouri Angeliki
  <br>
  Antoniou Christodoulos
</p>