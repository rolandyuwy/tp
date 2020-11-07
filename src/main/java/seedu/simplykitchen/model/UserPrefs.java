package seedu.simplykitchen.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.model.util.ComparatorUtil;
import seedu.simplykitchen.model.util.ComparatorUtil.ComparatorInformation;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path foodInventoryFilePath = Paths.get("data" , "foodInventory.json");
    private String sortingComparatorsDescription = ComparatorInformation.DESCRIPTION.getDescription();

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     * Sets to default sorting by description if sorting comparators description in {@code userPrefs} is invalid.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     * Sets to default sorting by description if sorting comparators description in {@code userPrefs} is invalid.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setFoodInventoryFilePath(newUserPrefs.getFoodInventoryFilePath());
        setSortingComparatorsDescription(newUserPrefs.getSortingComparatorsDescription());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFoodInventoryFilePath() {
        return foodInventoryFilePath;
    }

    public void setFoodInventoryFilePath(Path foodInventoryFilePath) {
        requireNonNull(foodInventoryFilePath);
        this.foodInventoryFilePath = foodInventoryFilePath;
    }

    public String getSortingComparatorsDescription() {
        return sortingComparatorsDescription;
    }

    /**
     * Checks if the description of sorting comparators {@code sortingComparatorsDescription} is valid.
     */
    public boolean isSortingComparatorsDescriptionValid(String sortingComparatorsDescription) {
        requireNonNull(sortingComparatorsDescription);
        return ComparatorUtil.isSortingComparatorsDescriptionValid(sortingComparatorsDescription);
    }

    public void setSortingComparatorsDescription(String sortingComparatorsDescription) {
        requireNonNull(sortingComparatorsDescription);
        if (isSortingComparatorsDescriptionValid(sortingComparatorsDescription)) {
            this.sortingComparatorsDescription = sortingComparatorsDescription;
        } else {
            this.sortingComparatorsDescription = ComparatorInformation.DESCRIPTION.getDescription();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && foodInventoryFilePath.equals(o.foodInventoryFilePath)
                && sortingComparatorsDescription.equals(o.sortingComparatorsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, foodInventoryFilePath, sortingComparatorsDescription);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + foodInventoryFilePath);
        sb.append("\n" + sortingComparatorsDescription);
        return sb.toString();
    }

}
