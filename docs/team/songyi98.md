---
layout: page
title: Song Yi's Project Portfolio Page
---

## Project: SimplyKitchen

SimplyKitchen is a desktop app for food inventory management, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). With intuitive and practical features, SimplyKitchen can get food management tasks done faster and more efficiently than traditional GUI apps.
Users interact with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org/).

* **New Feature**: Added the expiry date attribute.
  * What it does: The expiry date attribute allows the user to add an expiry date to a food item.
  * Justification: This feature is a core part of our food inventory management. It allows us to keep track of which food item is expiring or has expired.
  * Highlights: Parsing of expiry date to ensure validity is challenging as there is no single formatter in Java that can capture all formatting and invalid date errors. In the end, `SimpleDateFormat` and `DateTimeFormatter` were used.

* **New Feature**: Added the change quantity command.
  * What it does: It changes the quantity of a food item by a certain amount as entered by the user.
  * Justification: This helps to relieve the burden of having to calculate the final amount of a food item the user will have, making adding and subtracting food quantity much easier.
  * Highlights:
    * Parsing of amount is challenging as there are numerous issues when dealing with double arithmetics such as imprecise decimal placing, rounding errors and numeric overflow.
    * The regex for accepting a signed number with a maximum of 2 decimal places requires a lot of fine-tuning to get it right.
    * Methods had to be created in the Quantity class to prevent breaking the Law of Demeter while changing the quantity of a food item from the ChangeQuantityCommand class.
    * Had to coordinate with another team member who is in charge of the Quantity class regarding his implementation details to avoid integration issues when integrating his quantity attribute and my change quantity command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=songyi98&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
  * User Guide:
    * Added documentation for the `changeqty` feature (e.g. [\#99](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/99))
    * Made cosmetic and language tweaks to existing documentation of all features (e.g. [\#127](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/127))
  * Developer Guide:
    * Added use case and user stories (e.g. [\#80](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/80))
    * Added documentation and implementation details of the `changeqty` feature (e.g. [\#99](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/99), [\#191](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/191))

* **Project management and contributions to team-based tasks**:
  * Contributed to planning and ideation of application - product scope, user stories, use cases and non-functional requirements
  * Set up the GitHub team org and repo
  * Enabled assertions in the gradle file (e.g. [\#82](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/82))
  * Standardised formatting of User Guide, updated table of contents with hyperlinks and updated About this Document section (e.g. [\#127](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/127))
  * Updated Developer Guide to include use case and user stories (e.g. [\#80](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/80))
  * Added test cases to improve code coverage of existing code base (e.g. [\#209](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/209), [\#215](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/215))
  * Responsible for documentation - ensured the quality of all project documents.

* **Review contributions**:
  * Reviewed PRs (e.g. [\#59](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/59), [\#90](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/90), [\#200](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/200))
