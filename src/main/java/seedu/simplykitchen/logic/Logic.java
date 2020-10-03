package seedu.simplykitchen.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.logic.commands.CommandResult;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;
import seedu.simplykitchen.model.food.Food;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the SimplyKitchenInventory.
     *
     * @see seedu.simplykitchen.model.Model#getSimplyKitchenInventory()
     */
    ReadOnlySimplyKitchenInventory getSimplyKitchenInventory();

    /** Returns an unmodifiable view of the filtered list of food items */
    ObservableList<Food> getFilteredFoodList();

    /**
     * Returns the user prefs' SimplyKitchen inventory file path.
     */
    Path getSimplyKitchenInventoryFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
