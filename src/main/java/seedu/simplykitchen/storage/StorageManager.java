package seedu.simplykitchen.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;
import seedu.simplykitchen.model.ReadOnlyUserPrefs;
import seedu.simplykitchen.model.UserPrefs;

/**
 * Manages storage of SimplyKitchenInventory data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SimplyKitchenStorage simplyKitchenStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SimplyKitchenStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SimplyKitchenStorage simplyKitchenStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.simplyKitchenStorage = simplyKitchenStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ SimplyKitchenInventory methods ==============================

    @Override
    public Path getSimplyKitchenInventoryFilePath() {
        return simplyKitchenStorage.getSimplyKitchenInventoryFilePath();
    }

    @Override
    public Optional<ReadOnlySimplyKitchenInventory> readSimplyKitchenInventory()
            throws DataConversionException, IOException {
        return readSimplyKitchenInventory(simplyKitchenStorage.getSimplyKitchenInventoryFilePath());
    }

    @Override
    public Optional<ReadOnlySimplyKitchenInventory> readSimplyKitchenInventory(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return simplyKitchenStorage.readSimplyKitchenInventory(filePath);
    }

    @Override
    public void saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory simplyKitchenInventory) throws IOException {
        saveSimplyKitchenInventory(simplyKitchenInventory, simplyKitchenStorage.getSimplyKitchenInventoryFilePath());
    }

    @Override
    public void saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory addressBook, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        simplyKitchenStorage.saveSimplyKitchenInventory(addressBook, filePath);
    }

}
