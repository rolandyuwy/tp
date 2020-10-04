package seedu.simplykitchen.model;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.model.food.Food;

/**
 * Represents the in-memory model of the Food inventory data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FoodInventory foodInventory;
    private final UserPrefs userPrefs;
    private final FilteredList<Food> filteredFoods;

    /**
     * Initializes a ModelManager with the given Food Inventory and userPrefs.
     */
    public ModelManager(ReadOnlyFoodInventory foodInventory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(foodInventory, userPrefs);

        logger.fine("Initializing with Food inventory: " + foodInventory
                + " and user prefs " + userPrefs);

        this.foodInventory = new FoodInventory(foodInventory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFoods = new FilteredList<>(this.foodInventory.getFoods());
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
        this.foodInventory.resetData(foodInventory);
    }

    @Override
    public ReadOnlyFoodInventory getFoodInventory() {
        return foodInventory;
    }

    @Override
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return foodInventory.hasFood(food);
    }

    @Override
    public void deleteFood(Food target) {
        foodInventory.removeFood(target);
    }

    @Override
    public void addFood(Food food) {
        foodInventory.addFood(food);
        updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
    }

    @Override
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        foodInventory.setFood(target, editedFood);
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
        return foodInventory.equals(other.foodInventory)
                && userPrefs.equals(other.userPrefs)
                && filteredFoods.equals(other.filteredFoods);
    }

}
