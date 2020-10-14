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

Format: `add d/DESCRIPTION e/EXPIRY_DATE [p/PRIORITY]`

* Adds a food item based on its description and expiry date.
* Description and expiry date fields are compulsory.
* The priority field can be either `high`, `medium` or `low`.
* The priority field is optional. If not specified the default priority is set to `low`.
* For `e/EXPIRY_DATE`, the field only accepts a date in the format of `DD-mm-yyyy`.

Examples:
* `add d/canned tuna e/01-01-2021 p/low`
* `add d/mushroom e/11-10-2020`

### Listing all food items : `list`

Shows a list of all food items in the food inventory.

Format: `list`

### Searching for a food item: `find`

Searches for food items in the inventory with descriptions matching any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `fish` will match `Fish`
* The order of the keywords does not matter. e.g. `Cake Fish` will match `Fish Cake`
* Only the description is searched.
* Only full words will be matched e.g. `fis` will not match `fish`.
* Food items matching at least one keyword will be returned (i.e `OR` search). e.g. `fish` will return `Fish Cake`, `Tuna Fish`

Examples:
* `find chocolate` returns `Chocolate Pie` and `Chocolate Cake`.
* `find apple tuna` returns `Apple Pie` and `Tuna Can`.
  ![result for 'find apple tuna'](images/findAppleTunaResult.png)

### Deleting a food item : `delete`

Deletes the specified food item from the food inventory.

Format: `delete INDEX`

* Deletes the food item at the specified `INDEX`.
* The index refers to the index number shown in the displayed food item list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd food item in the food inventory.
* `find tuna` followed by `delete 1` deletes the 1st food item in the results of the `find` command.

### Clearing all entries : `clear` [coming soon]

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
**Add** | `add d/DESCRIPTION e/EXPIRY_DATE [p/PRIORITY]` <br> e.g., `add d/cereal e/31-10-2020 p/medium`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find apple tuna`
**List** | `list`
**Help** | `help`
