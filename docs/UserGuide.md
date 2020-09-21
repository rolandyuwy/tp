---
layout: page
title: User Guide
---

SimplyKitchen is a desktop app for food inventory management, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SimplyKitchen can get your food management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`d/canned tuna e/01-01-2021 p/low` : Adds a food item named `canned tuna` to the list.

   * **`delete`**`3` : Deletes the 3rd food item shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

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

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a food item: `add`

Adds a food item to the food inventory.

Format: `add d/DESCRIPTION e/EXPIRY_DATE [p/PRIORITY]`

* Adds a food item based on its description and expiry date.
* Description and expiry date fields are compulsory.
* The priority field is optional. If not specified the default priority is set to low.
* For e/EXPIRY_DATE, the field only accepts a date in the format of `DD-mm-yyyy`.

Examples:
* `add d/canned tuna e/01-01-2021 p/low`
* `add d/mushroom e/11-10-2020`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Searching for a food item: `find`

Searches for a food item inside the inventory tracker according to `KEYWORD`, `PRIORITY` or `EXPIRY_DATE`.

Format: `find k/KEYWORD p/PRIORITY e/EXPIRY_DATE`

* A minimum of 1 type of field must be specified.
* The search is case-insensitive. e.g as an input in the keyword field, `fish` will match `Fish`
* If more than 1 field is specified, the search results will be based on all the specified fields e.g. `find k/rice p/high` will match food items with description containing `rice` and having `high` priority.
* For `k/KEYWORD`, fields can have multiple keywords. e.g `find k/canned fish` will match food items having any of `canned` or `fish` in their description.
* For `k/KEYWORD`, the order of the keywords does not matter. e.g. `Tuna Fish` will match `Fish Tuna`.
* For `k/KEYWORD`, only full words will be matched. e.g. `Fish` will not match `Fishes`.
* For `k/KEYWORD`, food items matching at least one keyword will be returned (i.e. OR search). e.g. `Salty Fish` will return `Salty Rice`, `Tuna Fish`
* For `e/EXPIRY_DATE`, the field only accepts a date in the format of `DD-mm-yyyy`.


Examples:
* `find k/medium packet rice` returns `chicken rice` and `Packet Noodles`
* `find p/high` 
* `find k/tuna e/01-01-2021` 

### Deleting a food item : `delete`

Deletes the specified food item from the food inventory.

Format: `delete INDEX`

* Deletes the food item at the specified `INDEX`.
* The index refers to the index number shown in the displayed food item list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd food item in the food inventory.
* `find k/tuna` followed by `delete 1` deletes the 1st food item in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
