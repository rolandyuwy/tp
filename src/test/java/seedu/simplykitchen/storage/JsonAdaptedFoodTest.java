package seedu.simplykitchen.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.simplykitchen.storage.JsonAdaptedFood.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.exceptions.IllegalValueException;
import seedu.simplykitchen.model.food.Address;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Name;
import seedu.simplykitchen.model.food.Priority;

public class JsonAdaptedFoodTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PRIORITY = "MEDIUM-HIGH";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EXPIRYDATE = "1-13-2020";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PRIORITY = BENSON.getPriority().toString();
    private static final String VALID_EXPIRYDATE = BENSON.getExpiryDate().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validFoodDetails_returnsFood() throws Exception {
        JsonAdaptedFood food = new JsonAdaptedFood(BENSON);
        assertEquals(BENSON, food.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(INVALID_NAME, VALID_PRIORITY, VALID_EXPIRYDATE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(null, VALID_PRIORITY, VALID_EXPIRYDATE,
                VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, INVALID_PRIORITY, VALID_EXPIRYDATE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_NAME, null, VALID_EXPIRYDATE, VALID_ADDRESS,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, VALID_PRIORITY, INVALID_EXPIRYDATE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_NAME, VALID_PRIORITY, null, VALID_ADDRESS,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, VALID_PRIORITY, VALID_EXPIRYDATE, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_NAME, VALID_PRIORITY, VALID_EXPIRYDATE, null,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, VALID_PRIORITY, VALID_EXPIRYDATE, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, food::toModelType);
    }

}
