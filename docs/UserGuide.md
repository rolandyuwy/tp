---
layout: page
title: User Guide
---

SimplyKitchen is a desktop app for food inventory management, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SimplyKitchen can get your food management tasks done faster than traditional GUI apps.

Table of Contents:<br>
* [Quick start](https://github.com/AY2021S1-CS2103T-F13-4/tp/blob/master/docs/UserGuide.md#quick-start-coming-soon)
* [Features](https://github.com/AY2021S1-CS2103T-F13-4/tp/blob/master/docs/UserGuide.md#features)
* [FAQs](https://github.com/AY2021S1-CS2103T-F13-4/tp/blob/master/docs/UserGuide.md#faq)
* [Command Summary](https://github.com/AY2021S1-CS2103T-F13-4/tp/blob/master/docs/UserGuide.md#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add d/DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `add d/cucumber`.

* Items in square brackets are optional.<br>
  e.g `d/DESCRIPTION e/EXPIRY_DATE [p/PRIORITY]` can be used as `d/bread e/30-09-2020 p/low` or as `d/bread e/30-09-2020`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `d/DESCRIPTION e/EXPIRY_DATE`, `e/EXPIRY_DATE d/DESCRIPTION` is also acceptable.

</div>

### Viewing help : `help`

Shows a help message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a food item: `add`

Adds a food item to the food inventory.

Format: `add d/DESCRIPTION e/EXPIRY_DATE q/QUANTITY [p/PRIORITY] [t/TAG]…​`

* Adds a food item based on its description, expiry date and tags.
* A food item with the same description, expriy date and tags as another food item is considered a duplicate.
* Description and tags are case-insensitive (i.e `d/Apple e/30-12-2020 q/1 t/Red` is the same item as `d/apple e/30-12-2020 q/2 t/red`).
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

### Listing all food items : `list`

Shows a list of all food items in the food inventory.

Format: `list`

### Searching for a food item: `find`

Searches for food items in the inventory with descriptions matching any of the given keywords.

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

### Deleting a food item : `delete`

Deletes the specified food item from the food inventory.

Format: `delete INDEX`

* Deletes the food item at the specified `INDEX`.
* The index refers to the index number shown in the displayed food item list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd food item in the food inventory.
* `find tuna` followed by `delete 1` deletes the 1st food item in the results of the `find` command.

### Editing a food item : `edit`

Edits the details of an existing food item in the food inventory.

Format: `edit INDEX [d/DESCRIPTION] [p/PRIORITY] [q/QUANTITY] [e/EXPIRY DATE] [t/TAG]...`

* Edits the food item at the specified `INDEX`.
* The index refers to the index number shown in the displayed food item list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the food item will be removed i.e adding of tags is not cumulative.
* You can remove all the tags of a food item by typing `t/` without specifying any tags after it.
* Similar to [`add`](#adding-a-food-item) command, a food item with the same description, expriy date and tags as another food item is considered a duplicate.

Examples:
* `edit 1 d/baked beans e/1-1-2020` Edits the food description and expiry date of the 1st food item to be `baked beans` and `1-1-2020` respectively.
* `edit 2 d/canned tuna q/0.5 can t/` Edits the food description of the 2nd food item to be `canned tuna`, quantity to `0.5 can` and clears all existing tags.

### Changing the quantity of a food item: `changeqty`

Changes the quantity of an existing food item in the food inventory.

Format: `changeqty INDEX a/AMOUNT`

* The index refers to the number shown in the displayed food list. It must be a positive unsigned integer i.e. 1, 2, 3 and so on.
* The amount is the quantity of a food item you want to change by. It is compulsory and can be any non-zero signed number.
* Choose an amount such that the final quantity should not be less than or equal to zero.
* The amount can be specified up to a maximum of 2 decimal places.
* Do not specify the unit of the food item. The existing unit is used instead.

Examples:
* `changeqty 1 a/+1` increases the quantity of the 1st food item by 1.
* `changeqty 2 a/-2` decreases the quantity of the 2nd food item by 2.

### Undoing previous command: `undo`
Restores the food inventory to a state before an undoable command was executed.

* Undoable commands: commands that modify the food inventory's content (`add`, `delete`, `edit` and `clear`)

Format: `undo`

Examples:
* `delete 1` then `undo` will reverse the delete command.
* `delete 1` `clear` then `undo` will reverse the `clear` command.

### Redoing previously undone command: `redo`
Restores the food inventory to a state before an undo command was executed.

Format: `redo`

Examples:
* `add d/Donut p/medium e/21-2-2021` `undo` then `redo` will reverse the state to when the food was added.
* `clear` `undo` then `redo` will redo the `clear` command.

### Viewing expired items : `expired`

Shows a popup window listing all the expired food items if present in the inventory.

![expired popup](images/expiredPopup.png)

Format: `expired`

### Clearing all entries : `clear`
Clears all entries from the food inventory.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Food Inventory data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SimplyKitchen home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

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
**Expired** | `expired`
