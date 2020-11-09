---
layout: page
title: Roland Yu's Project Portfolio
---
      
# Project: SimplyKitchen
           
## Overview

SimplyKitchen is a desktop application for food inventory management, and is optimised for users who prefer working with a Command Line Interface (CLI), while still having the benefits of a Graphical User Interface (GUI).
With intuitive and user-friendly features, SimplyKitchen facilitates food management tasks which can be done faster and more efficiently than traditional GUI applications.
Users interact with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLoC.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org/).

## Summary of contributions

* **New Enhancements**:
    * Added the **ability to sort food items** by description, priority and expiry date
      * What it does: allows the user to sort the list of food items permanently.
      * Justification: This feature improves the product significantly because the user's experience when navigating the list of food items in the app relies on their preference on the list's order.
      * Highlights: This feature involved changes in the way the food list is processed, where a `Comparator` attribute was added to handle different ways of sorting for each of the sorting commands created. A new class `ComparatorUtil` was created to allow for scalability, when new sorting mechanisms are implemented in the future.

    * Revamped user preferences to **allow storing of sorting preferences**
      * What it does: allows the sorting preference to be stored when the user sorts the list of food items.
      * Justification: This enhancement improves the product significantly because the food items are updated dynamically according to the stored sorting preference. For instance, when a food item's priority is edited, if the current stored sorting preference is in accordance to priority, the item will be ordered to the correct position and index in the list immediately.
      Furthermore, when the user closes and reopens the application, the list of food items remains ordered according to the stored sorting preference.
      * Highlights: This enhancement affects existing commands and commands to be added in future. It involved changes in the way the food list is processed when it changes, for commands such as `add` and `edit`.

* **Enhancements to existing features**:
    * Improved aesthetics of GUI by fixing bugs (Pull request [\#176](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/176))
    * Conceptualised the GUI color scheme (Pull request [\#170](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/170))

* **Project management and contributions to team-based tasks**:
    * Took on role of Team Lead for the project
    * Conceptualised project idea (Management of food inventory to reduce food wastage)
    * Designed product icon
    * Conceptualised project theme and colour scheme
    * Scheduled and led weekly project meetings
    * Assisted in setting up the GitHub team org/repo
    * Created labels for issues and pull requests on GitHub.
    * Responsible for scheduling and tracking all project tasks
    * Responsible for maintaining the issue tracker
    * Responsible for ensuring all project deliverables and deadlines are met
    * Refactored AddressBook-Level3 project code to fit project idea (Pull request [\#59](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/59)) 
    * Managed shared team project document
    * Consolidated and vetted through all bugs from Mock Practical Exam

* **Code contributed**:
[[Functional code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=rolandyuwy&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&until=2020-11-10&tabAuthor=rolandyuwy&tabRepo=AY2021S1-CS2103T-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code)] [[Test code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=rolandyuwy&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&until=2020-11-10&tabAuthor=rolandyuwy&tabRepo=AY2021S1-CS2103T-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code)]

* **Documentation**:
    * User Guide:
        * Added documentation for the features `clear`, `edit`, `sortdesc`, `sortexpiry` and `sortpriority` (Pull requests [\#73](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/73) and [\#118](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/118))
        * Added GUI layout section (Pull request [\#205](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/205))
        * Added upcoming features section (Pull request [\#205](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/205))
    * Developer Guide:
        * Added implementation details of the sort feature. (Pull request [\#95](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/95))

* **Community and review contributions**:
  * Pull requests reviewed (with non-trivial review comments): [\#50](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/50), [\#55](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/55), [\#96](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/96), [\#123](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/123), [\#172](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/172)
  * Reported bugs and suggestions for other teams

* **Tools**:
  * Initial setup of project tools - Github organisation
  * Proposed and setup the use of the Github project board and issue tracker for project management, milestones and bugs.
