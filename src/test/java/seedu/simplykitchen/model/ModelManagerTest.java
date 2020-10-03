package seedu.simplykitchen.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.model.Model.PREDICATE_SHOW_ALL_FOODS;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.ALICE;
import static seedu.simplykitchen.testutil.TypicalFood.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.model.food.NameContainsKeywordsPredicate;
import seedu.simplykitchen.testutil.SimplyKitchenInventoryBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new SimplyKitchenInventory(), new SimplyKitchenInventory(
                modelManager.getSimplyKitchenInventory()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSimplyKitchenInventoryFilePath(Paths.get("simplyKitchen/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSimplyKitchenInventoryFilePath(Paths.get("new/simplyKitchen/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setSimplyKitchenInventoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSimplyKitchenInventoryFilePath(null));
    }

    @Test
    public void setSimplyKitchenInventoryFilePath_validPath_setsSimplyKitchenInventoryFilePath() {
        Path path = Paths.get("simplyKitchen/file/path");
        modelManager.setSimplyKitchenInventoryFilePath(path);
        assertEquals(path, modelManager.getSimplyKitchenInventoryFilePath());
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInSimplyKitchenInventory_returnsFalse() {
        assertFalse(modelManager.hasFood(ALICE));
    }

    @Test
    public void hasFood_foodInSimplyKitchenInventory_returnsTrue() {
        modelManager.addFood(ALICE);
        assertTrue(modelManager.hasFood(ALICE));
    }

    @Test
    public void getFilteredFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredFoodList().remove(0));
    }

    @Test
    public void equals() {
        SimplyKitchenInventory simplyKitchenInventory = new SimplyKitchenInventoryBuilder()
                .withFood(ALICE).withFood(BENSON).build();
        SimplyKitchenInventory differentSimplyKitchenInventory = new SimplyKitchenInventory();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(simplyKitchenInventory, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(simplyKitchenInventory, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different simplyKitchenInventory -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSimplyKitchenInventory, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredFoodList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(simplyKitchenInventory, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSimplyKitchenInventoryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(simplyKitchenInventory, differentUserPrefs)));
    }
}
