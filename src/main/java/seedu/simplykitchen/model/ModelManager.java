package seedu.simplykitchen.model;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_ASCENDING_DESCRIPTION;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.model.food.Food;

/**
 * Represents the in-memory model of the Food inventory data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedFoodInventory versionedFoodInventory;
    private final UserPrefs userPrefs;
    private final FilteredList<Food> filteredFoods;
    private final SortedList<Food> sortedFoods;

    /**
     * Initializes a ModelManager with the given Food Inventory and userPrefs.
     */
    public ModelManager(ReadOnlyFoodInventory foodInventory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(foodInventory, userPrefs);

        logger.fine("Initializing with Food inventory: " + foodInventory
                + " and user prefs " + userPrefs);

        this.versionedFoodInventory = new VersionedFoodInventory(foodInventory);
        this.userPrefs = new UserPrefs(userPrefs);

        sortedFoods = new SortedList<>(this.versionedFoodInventory.getFoods());
        updateSortedFoodList(SORT_BY_ASCENDING_DESCRIPTION);
        filteredFoods = new FilteredList<>(sortedFoods);
    }

    public ModelManager() {
        this(new FoodInventory(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFoodInventoryFilePath() {
        return userPrefs.getFoodInventoryFilePath();
    }

    @Override
    public void setFoodInventoryFilePath(Path foodInventoryFilePath) {
        requireNonNull(foodInventoryFilePath);
        userPrefs.setFoodInventoryFilePath(foodInventoryFilePath);
    }

    //=========== FoodInventory ==========================================================================

    @Override
    public void setFoodInventory(ReadOnlyFoodInventory foodInventory) {
        versionedFoodInventory.resetData(foodInventory);
    }

    @Override
    public ReadOnlyFoodInventory getFoodInventory() {
        return versionedFoodInventory;
    }

    @Override
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return versionedFoodInventory.hasFood(food);
    }

    @Override
    public void deleteFood(Food target) {
        versionedFoodInventory.removeFood(target);
    }

    @Override
    public void addFood(Food food) {
        versionedFoodInventory.addFood(food);
        updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        updateSortedFoodList(SORT_BY_ASCENDING_DESCRIPTION);
    }

    @Override
    public void sortFood(Food food, Comparator<Food> comparator) {
        versionedFoodInventory.sortFood(food); //updates foods aka internalList
//        updateSortedFoodList(comparator); //updates sortedFoods aka internalUnmodifiableList
    }

    @Override
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        versionedFoodInventory.setFood(target, editedFood);
    }

    //=========== Filtered Food List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Food} backed by the internal list of
     * {@code versionedFoodInventory}
     */
    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return filteredFoods;
    }

    @Override
    public void updateFilteredFoodList(Predicate<Food> predicate) {
        requireNonNull(predicate);
        filteredFoods.setPredicate(predicate);
    }

    @Override
    public void updateSortedFoodList(Comparator<Food> comparator) {
        requireNonNull(comparator);
        sortedFoods.setComparator(comparator);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoFoodInventory() {
        return versionedFoodInventory.canUndo();
    }

    @Override
    public boolean canRedoFoodInventory() {
        return versionedFoodInventory.canRedo();
    }

    @Override
    public void undoFoodInventory() {
        versionedFoodInventory.undo();
    }

    @Override
    public void redoFoodInventory() {
        versionedFoodInventory.redo();
    }

    @Override
    public void commitFoodInventory() {
        versionedFoodInventory.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedFoodInventory.equals(other.versionedFoodInventory)
                && userPrefs.equals(other.userPrefs)
                && filteredFoods.equals(other.filteredFoods);
    }

}
