package seedu.simplykitchen.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedFoodInventory extends FoodInventory {

    private final List<ReadOnlyFoodInventory> foodInventoryStateList;
    private int currentStatePointer;

    /**
     * Initializes a VersionedFoodInventory with given initial state.
     */
    public VersionedFoodInventory(ReadOnlyFoodInventory initialState) {
        super(initialState);

        foodInventoryStateList = new ArrayList<>();
        foodInventoryStateList.add(new FoodInventory(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code FoodInventory} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        foodInventoryStateList.add(new FoodInventory(this));
        currentStatePointer++;
        indicateModified();
    }

    private void removeStatesAfterCurrentPointer() {
        foodInventoryStateList.subList(currentStatePointer + 1, foodInventoryStateList.size()).clear();
    }

    /**
     * Restores the food inventory to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(foodInventoryStateList.get(currentStatePointer));
    }

    /**
     * Restores the food inventory to its previously undone state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(foodInventoryStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has food inventory states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has food inventory states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < foodInventoryStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedFoodInventory)) {
            return false;
        }

        VersionedFoodInventory otherVersionedFoodInventory = (VersionedFoodInventory) other;

        // state check
        return super.equals(otherVersionedFoodInventory)
                && foodInventoryStateList.equals(otherVersionedFoodInventory.foodInventoryStateList)
                && currentStatePointer == otherVersionedFoodInventory.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of foodInventoryState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of foodInventoryState list, unable to redo.");
        }
    }
}
