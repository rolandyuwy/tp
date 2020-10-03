package seedu.simplykitchen.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;
import seedu.simplykitchen.model.SimplyKitchenInventory;

/**
 * Represents a storage for {@link SimplyKitchenInventory}.
 */
public interface SimplyKitchenStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSimplyKitchenInventoryFilePath();

    /**
     * Returns SimplyKitchenInventory data as a {@link ReadOnlySimplyKitchenInventory}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySimplyKitchenInventory> readSimplyKitchenInventory() throws DataConversionException, IOException;

    /**
     * @see #getSimplyKitchenInventoryFilePath()
     */
    Optional<ReadOnlySimplyKitchenInventory> readSimplyKitchenInventory(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySimplyKitchenInventory} to the storage.
     * @param simplyKitchenInventory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory simplyKitchenInventory) throws IOException;

    /**
     * @see #saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory)
     */
    void saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory addressBook, Path filePath) throws IOException;

}
