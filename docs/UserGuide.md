---
layout: page
title: User Guide
---

SimplyKitchen is a desktop app for food inventory management, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
With intuitive and practical features, SimplyKitchen can get your food management tasks done faster and more efficiently than traditional GUI apps.

SimplyKitchen aims to aid the domestic individuals who manage their kitchens at home by providing an apt food inventory management system.
We have taken into consideration the common problems our target audience may face while managing their kitchen, and have created specialized features in our application in order to address those problems.
Since it is meant for general households, care has been taken to make the app more intuitive and not overwhelming to facilitate comfortable usage for all.
We hope to make SimplyKitchen a household name in Singapore and appreciate your assistance in helping us do so!

Table of Contents:<br>
* [About this document](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#about-this-document)
* [Quick start](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#quick-start)
* [Features](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features)
* [FAQs](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#faq)
* [Command Summary](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#command-summary)

--------------------------------------------------------------------------------------------------------------------

## [About this document](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html)

This document is a User Guide meant to assist you in the entire process of using SimplyKitchen to manage your food inventory.

The `Quick start` section of this document guides you in setting up and launching the SimplyKitchen app on your computer.

The `Features` section of this document can help you understand and use the features of SimplyKitchen. 
It gives clear examples for each of the features to ensure that the purpose and constraints of the features are evident.

The `FAQs` section contains some Frequently Asked Questions with regard to SimplyKitchen.

Finally, the `Command Summary` section acts as a quick reference point for all the features of SimplyKitchen.
 
--------------------------------------------------------------------------------------------------------------------
## [Quick start](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html)

* Ensure that you have Java 11 or above installed on your computer.
* Download the most recent SimplyKitchen.jar from [this link](https://github.com/AY2021S1-CS2103T-F13-4/tp/releases).
* Copy the downloaded jar file into a folder which will be your home folder for SimplyKitchen.
* Double click on the SimplyKitchen.jar file to launch the app. The window of the app should appear in a few seconds. 
  ![GUI for the app](images/Ui.png)
  
  Notice how the app is populated with some sample data.
* Start using the app by typing a command in the command box at the bottom. For example, typing `help` and then pressing enter will open the help window.
* Refer to the features section in [this guide](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html) for help on how to use the app.
--------------------------------------------------------------------------------------------------------------------

## [Features](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html)

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add d/DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `add d/cucumber`.

* Items in square brackets are optional.<br>
  e.g `d/DESCRIPTION e/EXPIRY_DATE [p/PRIORITY]` can be used as `d/bread e/30-09-2020 p/low` or as `d/bread e/30-09-2020`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `d/DESCRIPTION e/EXPIRY_DATE`, `e/EXPIRY_DATE d/DESCRIPTION` is also acceptable.

</div>

### [Viewing help](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `help`

Shows a help message explaining how to access the help page. 

![help message](images/helpMessage.png)

Format: `help`

### [Adding a food item](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `add`

Adds a food item to the food inventory, to start tracking your food item.

Format: `add d/DESCRIPTION e/EXPIRY_DATE q/QUANTITY [p/PRIORITY] [t/TAG]…​`

* Adds a food item based on its description and expiry date.
* Description, expiry date and quantity fields are compulsory.
* The priority field can be either `high`, `medium` or `low`.
* The priority field is optional. If not specified the default priority is set to `low`.
* The quantity field consists of 2 entities - `value` and `unit`. The `value` should come before the `unit`.
* The `value` in the quantity field is compulsory. It must be a positive number. It can be an integer or a decimal value.
* The `unit` in quantity field is optional. If provided, it must consist of only alphabets. Numbers, space and special characters are not permitted.
* If the `unit` in the quantity field is not provided, the default unit - `unit` - will be given.
* For `e/EXPIRY_DATE`, the field only accepts a date in the format of `DD-mm-yyyy`.
* The tag field accepts `alphanumeric`, `whitespaces` and these special characters: `#$%&-()`.
* Tags with only whitespace(s) will not be allowed.
* A food item can have any number of tags (including 0).

Examples:
* `add d/canned tuna e/01-01-2021 q/1.1 can p/low`
* `add d/apple pie e/11-10-2020 q/2 p/medium t/frozen t/$15 t/contains nuts`

### [Listing all food items](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `list`

Shows a list of all food items in the food inventory, which lets you have an overview of the food inventory.

Format: `list`

### [Searching for a food item](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `find`

Searches for food items in the inventory with descriptions matching any of the given keywords. This lets you find a specific food item, or a group of food items easily.

Format: `find [d/DESCRIPTION [MORE_DESCRIPTIONS]...] [p/PRIORITY] [e/EXPIRY DATE] [t/TAG]...`

* The search will return food items with matching description, priority, expiry date and tags (i.e `[d/DESCRIPTION OR [MORE_DESCRIPTIONS]...] AND [p/PRIORITY] AND [e/EXPIRY DATE] AND [t/TAG] OR ...`).
* The search is case-insensitive. e.g `fish` will match `Fish`.
* The order of the descriptions does not matter. e.g. `Cake Fish` will match `Fish Cake`.
* Only full words in description will be matched e.g. `fis` will not match `fish`.
* Food items with description matching at least one keyword will be returned (i.e `OR` search). e.g. `fish` will return `Fish Cake`, `Tuna Fish`.
* The priority field can be either `high`, `medium` or `low`.
* For `e/EXPIRY_DATE`, the field only accepts a date in the format of `DD-mm-yyyy`.
* The tag field accepts `alphanumeric`, `whitespaces` and these special characters: `#$%&-()`.
* Tags with only whitespace(s) will not be allowed.
* Only full tags will be matched e.g. `nuts` will not match `contains nuts`.
* Food items with tags matching at least one of the search tags will be returned (i.e `OR` search). e.g. `frozen` will return all food items with tags `frozen` regardless of their other tags.

Examples:
* `find d/chocolate` returns `Chocolate Pie` and `Chocolate Cake`.
* `find d/apple tuna` returns `Apple Pie` and `Tuna Can`.
* `find d/apple p/high` returns `Apple Pie` and `Apple Jam`, both items have `HIGH` priority.
* `find e/30-12-2020` returns  all food items with expiry date on `30-12-2020`.
* `find t/cat t/dog` returns all food items with the tag `cat` or `dog`.
* `find d/biscuits p/medium e/30-12-2020 t/cat t/dog` returns food items with `biscuits` in the description, `MEDIUM` priority, expires on `30-12-2020` and have either `cat` or `dog` as tags.
  ![result for 'find d/biscuits p/medium e/30-12-2020 t/cat t/dog'](images/findBiscuitsCatDog.png)

### [Deleting a food item](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `delete`

Deletes the specified food item from the food inventory, so that you can stop tracking the food item.

Format: `delete INDEX`

* Deletes the food item at the specified `INDEX`.
* The index refers to the index number shown in the displayed food item list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd food item in the food inventory.
* `find tuna` followed by `delete 1` deletes the 1st food item in the results of the `find` command.

### [Editing a food item](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `edit`

Edits the details of an existing food item in the food inventory. If an entry was incorrect, you can easily edit the entry, without deleting and re-adding the food item.

Format: `edit INDEX [d/DESCRIPTION] [p/PRIORITY] [q/QUANTITY] [e/EXPIRY DATE] [t/TAG]...`

* Edits the food item at the specified `INDEX`.
* The index refers to the index number shown in the displayed food item list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the food item will be removed i.e adding of tags is not cumulative.
* You can remove all the tags of a food item by typing `t/` without specifying any tags after it.

Examples:
* `edit 1 d/baked beans e/1-1-2020` Edits the food description and expiry date of the 1st food item to be `baked beans` and `1-1-2020` respectively.
* `edit 2 d/canned tuna q/0.5 can t/` Edits the food description of the 2nd food item to be `canned tuna`, quantity to `0.5 can` and clears all existing tags.

### [Changing the quantity of a food item](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `changeqty`

Changes the quantity of an existing food item in the food inventory. This lets you easily update the current quantity of a food item if you used or stocked up on it.

Format: `changeqty INDEX a/AMOUNT`

* The index refers to the number shown in the displayed food list. It must be a positive unsigned integer i.e. 1, 2, 3 and so on.
* The amount is the quantity of a food item you want to change by. It is compulsory and can be any non-zero signed number.
* Choose an amount such that the final quantity should not be less than or equal to zero.
* The amount can be specified up to a maximum of 2 decimal places.
* Do not specify the unit of the food item. The existing unit is used instead.

Examples:
* `changeqty 1 a/+1` increases the quantity of the 1st food item by 1.
* `changeqty 2 a/-2` decreases the quantity of the 2nd food item by 2.

### [Undoing previous command](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `undo`
Restores the food inventory to a state before an undoable command was executed. This lets you easily correct mistakes made on the food inventory.

* Undoable commands: commands that modify the food inventory's content (`add`, `delete`, `edit` and `clear`)

Format: `undo`

Examples:
* `delete 1` then `undo` will reverse the delete command.
* `delete 1` `clear` then `undo` will reverse the `clear` command.

### [Redoing previously undone command](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `redo`
Restores the food inventory to a state before an undo command was executed. This lets you easily redo the command that was incorrectly undone.

Format: `redo`

Examples:
* `add d/Donut p/medium e/21-2-2021` `undo` then `redo` will reverse the state to when the food was added.
* `clear` `undo` then `redo` will redo the `clear` command.

### [Clearing all entries](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `clear`
Clears all entries from the food inventory. This lets you easily clear the starting data, or start on a clean food inventory.

Format: `clear`

### [Exiting the program](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) : `exit`

Exits the program.

Format: `exit`

### [Saving the data](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features)

Food Inventory data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## [FAQ](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html)

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SimplyKitchen home folder.

--------------------------------------------------------------------------------------------------------------------

## [Command summary](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html)

Action | Format, Examples
--------|------------------
**Add** | `add d/DESCRIPTION e/EXPIRY_DATE q/QUANTITY [p/PRIORITY] [t/TAG]…` <br> e.g., `add d/cereal e/31-10-2020 q/2 p/medium t/corn flakes`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [d/DESCRIPTION] [p/PRIORITY] [q/QUANTITY] [e/EXPIRY DATE] [t/TAG]…` <br> e.g., `edit 1 d/baked beans e/1-1-2020 q/1.5 can`
**Change quantity** | `changeqty INDEX a/AMOUNT` <br> e.g. `changeqty 1 a/+1.50`
**Find** | `find [d/DESCRIPTION [MORE_DESCRIPTIONS]...] [p/PRIORITY] [e/EXPIRY DATE] [t/TAG]...`<br> e.g., `find d/biscuits p/medium e/30-12-2020 t/cat t/dog`
**List** | `list`
**Undo** | `undo`
**Redo** | `redo`
**Help** | `help`
**Exit** | `exit`
