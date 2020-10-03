package seedu.simplykitchen.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.exceptions.IllegalValueException;
import seedu.simplykitchen.commons.util.JsonUtil;
import seedu.simplykitchen.model.SimplyKitchenInventory;
import seedu.simplykitchen.testutil.TypicalFood;

public class JsonSerializableSimplyKitchenInventoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableSimplyKitchenInventoryTest");
    private static final Path TYPICAL_FOOD_FILE = TEST_DATA_FOLDER.resolve("typicalFoodSimplyKitchenInventory.json");
    private static final Path INVALID_FOOD_FILE = TEST_DATA_FOLDER.resolve("invalidFoodSimplyKitchenInventory.json");
    private static final Path DUPLICATE_FOOD_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateFoodSimplyKitchenInventory.json");

    @Test
    public void toModelType_typicalFoodFile_success() throws Exception {
        JsonSerializableSimplyKitchenInventory dataFromFile = JsonUtil.readJsonFile(TYPICAL_FOOD_FILE,
                JsonSerializableSimplyKitchenInventory.class).get();
        SimplyKitchenInventory simplyKitchenInventoryFromFile = dataFromFile.toModelType();
        SimplyKitchenInventory typicalFoodSimplyKitchenInventory = TypicalFood.getTypicalSimplyKitchenInventory();
        assertEquals(simplyKitchenInventoryFromFile, typicalFoodSimplyKitchenInventory);
    }

    @Test
    public void toModelType_invalidFoodFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSimplyKitchenInventory dataFromFile = JsonUtil.readJsonFile(INVALID_FOOD_FILE,
                JsonSerializableSimplyKitchenInventory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFood_throwsIllegalValueException() throws Exception {
        JsonSerializableSimplyKitchenInventory dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FOOD_FILE,
                JsonSerializableSimplyKitchenInventory.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSimplyKitchenInventory.MESSAGE_DUPLICATE_FOOD,
                dataFromFile::toModelType);
    }

}
