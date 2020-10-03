package seedu.simplykitchen.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalSimplyKitchenInventory;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;
import seedu.simplykitchen.model.SimplyKitchenInventory;
import seedu.simplykitchen.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonSimplyKitchenStorage simplyKitchenInventoryStorage = new JsonSimplyKitchenStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(simplyKitchenInventoryStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void simplyKitchenInventoryReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonSimplyKitchenStorage} class.
         * More extensive testing of UserPref saving/reading is done in
         * {@link JsonSimplyKitchenInventoryStorageTest} class.
         */
        SimplyKitchenInventory original = getTypicalSimplyKitchenInventory();
        storageManager.saveSimplyKitchenInventory(original);
        ReadOnlySimplyKitchenInventory retrieved = storageManager.readSimplyKitchenInventory().get();
        assertEquals(original, new SimplyKitchenInventory(retrieved));
    }

    @Test
    public void getSimplyKitchenInventoryFilePath() {
        assertNotNull(storageManager.getSimplyKitchenInventoryFilePath());
    }

}
