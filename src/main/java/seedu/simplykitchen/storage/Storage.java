package seedu.simplykitchen.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;
import seedu.simplykitchen.model.ReadOnlyUserPrefs;
import seedu.simplykitchen.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SimplyKitchenStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSimplyKitchenInventoryFilePath();

    @Override
    Optional<ReadOnlySimplyKitchenInventory> readSimplyKitchenInventory() throws DataConversionException, IOException;

    @Override
    void saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory simplyKitchenInventory) throws IOException;

}
