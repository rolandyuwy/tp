package seedu.simplykitchen.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.ALICE;
import static seedu.simplykitchen.testutil.TypicalFood.HOON;
import static seedu.simplykitchen.testutil.TypicalFood.IDA;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalSimplyKitchenInventory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;
import seedu.simplykitchen.model.SimplyKitchenInventory;

public class JsonSimplyKitchenInventoryStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSimplyKitchenStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSimplyKitchenInventory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSimplyKitchenInventory(null));
    }

    private java.util.Optional<ReadOnlySimplyKitchenInventory> readSimplyKitchenInventory(String filePath)
            throws Exception {
        return new JsonSimplyKitchenStorage(Paths.get(filePath))
                .readSimplyKitchenInventory(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSimplyKitchenInventory("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSimplyKitchenInventory(
                "notJsonFormatSimplyKitchenInventory.json"));
    }

    @Test
    public void readSimplyKitchenInventory_invalidFoodSimplyKitchenInventory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSimplyKitchenInventory(
                "invalidFoodSimplyKitchenInventory.json"));
    }

    @Test
    public void readSimplyKitchenInventory_invalidAndValidFoodSimplyKitchenInventory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSimplyKitchenInventory(
                "invalidAndValidFoodSimplyKitchenInventory.json"));
    }

    @Test
    public void readAndSaveSimplyKitchenInventory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSimplyKitchenInventory.json");
        SimplyKitchenInventory original = getTypicalSimplyKitchenInventory();
        JsonSimplyKitchenStorage jsonSimplyKitchenStorage = new JsonSimplyKitchenStorage(filePath);

        // Save in new file and read back
        jsonSimplyKitchenStorage.saveSimplyKitchenInventory(original, filePath);
        ReadOnlySimplyKitchenInventory readBack = jsonSimplyKitchenStorage.readSimplyKitchenInventory(filePath).get();
        assertEquals(original, new SimplyKitchenInventory(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFood(HOON);
        original.removeFood(ALICE);
        jsonSimplyKitchenStorage.saveSimplyKitchenInventory(original, filePath);
        readBack = jsonSimplyKitchenStorage.readSimplyKitchenInventory(filePath).get();
        assertEquals(original, new SimplyKitchenInventory(readBack));

        // Save and read without specifying file path
        original.addFood(IDA);
        jsonSimplyKitchenStorage.saveSimplyKitchenInventory(original); // file path not specified
        readBack = jsonSimplyKitchenStorage.readSimplyKitchenInventory().get(); // file path not specified
        assertEquals(original, new SimplyKitchenInventory(readBack));

    }

    @Test
    public void saveSimplyKitchenInventory_nullSimplyKitchenInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSimplyKitchenInventory(
                null, "SomeFile.json"));
    }

    /**
     * Saves {@code simplyKitchenInventory} at the specified {@code filePath}.
     */
    private void saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory simplyKitchenInventory, String filePath) {
        try {
            new JsonSimplyKitchenStorage(Paths.get(filePath))
                    .saveSimplyKitchenInventory(simplyKitchenInventory, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSimplyKitchenInventory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSimplyKitchenInventory(
                new SimplyKitchenInventory(), null));
    }
}
