package seedu.simplykitchen.model;

import javafx.collections.ObservableList;
import seedu.simplykitchen.model.food.Food;

/**
 * Unmodifiable view of a Food inventory.
 */
public interface ReadOnlyFoodInventory {

    /**
     * Returns an unmodifiable view of the food list.
     * This list will not contain any duplicate food items.
     */
    ObservableList<Food> getFoods();

}
