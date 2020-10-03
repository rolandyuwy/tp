package seedu.simplykitchen.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.UniqueFoodList;

/**
 * Wraps all data at the SimplyKitchen-inventory level
 * Duplicates are not allowed (by .isSameFood comparison)
 */
public class SimplyKitchenInventory implements ReadOnlySimplyKitchenInventory {

    private final UniqueFoodList foods;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        foods = new UniqueFoodList();
    }

    public SimplyKitchenInventory() {}

    /**
     * Creates an SimplyKitchenInventory using the Persons in the {@code toBeCopied}
     */
    public SimplyKitchenInventory(ReadOnlySimplyKitchenInventory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the food list with {@code foods}.
     * {@code foods} must not contain duplicate foods.
     */
    public void setFoods(List<Food> foods) {
        this.foods.setFoods(foods);
    }

    /**
     * Resets the existing data of this {@code SimplyKitchenInventory} with {@code newData}.
     */
    public void resetData(ReadOnlySimplyKitchenInventory newData) {
        requireNonNull(newData);

        setFoods(newData.getFoods());
    }

    //// food-level operations

    /**
     * Returns true if a food item with the same identity as {@code food} exists in the SimplyKitchen inventory.
     */
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return foods.contains(food);
    }

    /**
     * Adds a food item to the SimplyKitchen inventory.
     * The food item must not already exist in the SimplyKitchen inventory.
     */
    public void addFood(Food food) {
        foods.add(food);
    }

    /**
     * Replaces the given food item {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in the SimplyKitchen inventory.
     * The food item identity of {@code editedFood} must not be the same as another existing food item
     * in the SimplyKitchen inventory.
     */
    public void setFood(Food target, Food editedFood) {
        requireNonNull(editedFood);

        foods.setFood(target, editedFood);
    }

    /**
     * Removes {@code key} from this {@code SimplyKitchenInventory}.
     * {@code key} must exist in the SimplyKitchen inventory.
     */
    public void removeFood(Food key) {
        foods.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return foods.asUnmodifiableObservableList().size() + " food items";
        // TODO: refine later
    }

    @Override
    public ObservableList<Food> getFoods() {
        return foods.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SimplyKitchenInventory // instanceof handles nulls
                && foods.equals(((SimplyKitchenInventory) other).foods));
    }

    @Override
    public int hashCode() {
        return foods.hashCode();
    }
}
