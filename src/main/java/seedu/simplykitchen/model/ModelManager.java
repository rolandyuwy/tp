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
 * Represents the in-memory model of the SimplyKitchen inventory data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SimplyKitchenInventory simplyKitchenInventory;
    private final UserPrefs userPrefs;
    private final FilteredList<Food> filteredFoods;

    /**
     * Initializes a ModelManager with the given simplyKitchenInventory and userPrefs.
     */
    public ModelManager(ReadOnlySimplyKitchenInventory simplyKitchenInventory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(simplyKitchenInventory, userPrefs);

        logger.fine("Initializing with SimplyKitchen inventory: " + simplyKitchenInventory
                + " and user prefs " + userPrefs);

        this.simplyKitchenInventory = new SimplyKitchenInventory(simplyKitchenInventory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFoods = new FilteredList<>(this.simplyKitchenInventory.getFoods());
    }

    public ModelManager() {
        this(new SimplyKitchenInventory(), new UserPrefs());
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
    public Path getSimplyKitchenInventoryFilePath() {
        return userPrefs.getSimplyKitchenInventoryFilePath();
    }

    @Override
    public void setSimplyKitchenInventoryFilePath(Path simplyKitchenInventoryFilePath) {
        requireNonNull(simplyKitchenInventoryFilePath);
        userPrefs.setSimplyKitchenInventoryFilePath(simplyKitchenInventoryFilePath);
    }

    //=========== SimplyKitchenInventory ==========================================================================

    @Override
    public void setSimplyKitchenInventory(ReadOnlySimplyKitchenInventory simplyKitchenInventory) {
        this.simplyKitchenInventory.resetData(simplyKitchenInventory);
    }

    @Override
    public ReadOnlySimplyKitchenInventory getSimplyKitchenInventory() {
        return simplyKitchenInventory;
    }

    @Override
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return simplyKitchenInventory.hasFood(food);
    }

    @Override
    public void deleteFood(Food target) {
        simplyKitchenInventory.removeFood(target);
    }

    @Override
    public void addFood(Food food) {
        simplyKitchenInventory.addFood(food);
        updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
    }

    @Override
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        simplyKitchenInventory.setFood(target, editedFood);
    }

    //=========== Filtered Food List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Food} backed by the internal list of
     * {@code versionedSimplyKitchenInventory}
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
        return simplyKitchenInventory.equals(other.simplyKitchenInventory)
                && userPrefs.equals(other.userPrefs)
                && filteredFoods.equals(other.filteredFoods);
    }

}
