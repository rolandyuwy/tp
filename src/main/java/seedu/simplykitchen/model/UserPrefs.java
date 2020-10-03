package seedu.simplykitchen.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.simplykitchen.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path simplyKitchenInventoryFilePath = Paths.get("data" , "simplykitchen.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setSimplyKitchenInventoryFilePath(newUserPrefs.getSimplyKitchenInventoryFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getSimplyKitchenInventoryFilePath() {
        return simplyKitchenInventoryFilePath;
    }

    public void setSimplyKitchenInventoryFilePath(Path simplyKitchenInventoryFilePath) {
        requireNonNull(simplyKitchenInventoryFilePath);
        this.simplyKitchenInventoryFilePath = simplyKitchenInventoryFilePath;
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
                && simplyKitchenInventoryFilePath.equals(o.simplyKitchenInventoryFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, simplyKitchenInventoryFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + simplyKitchenInventoryFilePath);
        return sb.toString();
    }

}
