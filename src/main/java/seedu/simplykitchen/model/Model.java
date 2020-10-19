package seedu.simplykitchen.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.model.food.Food;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Food> PREDICATE_SHOW_ALL_FOODS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Food inventory file path.
     */
    Path getFoodInventoryFilePath();

    /**
     * Sets the user prefs' Food inventory file path.
     */
    void setFoodInventoryFilePath(Path foodInventoryFilePath);

    /**
     * Replaces Food inventory data with the data in {@code foodInventory}.
     */
    void setFoodInventory(ReadOnlyFoodInventory foodInventory);

    /** Returns the FoodInventory */
    ReadOnlyFoodInventory getFoodInventory();

    /**
     * Returns true if a food item with the same identity as {@code food} exists in the Food inventory.
     */
    boolean hasFood(Food food);

    /**
     * Deletes the given food item.
     * The food item must exist in the Food inventory.
     */
    void deleteFood(Food target);

    /**
     * Adds the given food item.
     * {@code food} must not already exist in the Food inventory.
     */
    void addFood(Food food);

    /**
     * Replaces the given food item {@code target} with {@code editedFood}.
     * {@code target} must exist in the Food inventory.
     * The food item identity of {@code editedFood} must not be the same as another existing food item
     * in the Food inventory.
     */
    void setFood(Food target, Food editedFood);

    /** Returns an unmodifiable view of the filtered food list */
    ObservableList<Food> getFilteredFoodList();

    /**
     * Updates the filter of the filtered food list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFoodList(Predicate<Food> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoFoodInventory();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoFoodInventory();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoFoodInventory();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoFoodInventory();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitFoodInventory();
}
