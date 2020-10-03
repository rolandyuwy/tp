package seedu.simplykitchen.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.commons.exceptions.IllegalValueException;
import seedu.simplykitchen.commons.util.FileUtil;
import seedu.simplykitchen.commons.util.JsonUtil;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;

/**
 * A class to access SimplyKitchenInventory data stored as a json file on the hard disk.
 */
public class JsonSimplyKitchenStorage implements SimplyKitchenStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSimplyKitchenStorage.class);

    private Path filePath;

    public JsonSimplyKitchenStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSimplyKitchenInventoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySimplyKitchenInventory> readSimplyKitchenInventory() throws DataConversionException {
        return readSimplyKitchenInventory(filePath);
    }

    /**
     * Similar to {@link #readSimplyKitchenInventory()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySimplyKitchenInventory> readSimplyKitchenInventory(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSimplyKitchenInventory> jsonSimplyKitchenInventory = JsonUtil.readJsonFile(
                filePath, JsonSerializableSimplyKitchenInventory.class);
        if (!jsonSimplyKitchenInventory.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSimplyKitchenInventory.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory simplyKitchenInventory) throws IOException {
        saveSimplyKitchenInventory(simplyKitchenInventory, filePath);
    }

    /**
     * Similar to {@link #saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSimplyKitchenInventory(ReadOnlySimplyKitchenInventory simplyKitchenInventory, Path filePath)
            throws IOException {
        requireNonNull(simplyKitchenInventory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSimplyKitchenInventory(simplyKitchenInventory), filePath);
    }

}
