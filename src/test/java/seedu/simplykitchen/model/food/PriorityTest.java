package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null priority number
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priority numbers
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("91")); // numeric
        assertFalse(Priority.isValidPriority("priority")); // non-numeric
        assertFalse(Priority.isValidPriority("9011p041")); // alphabets within digits
        assertFalse(Priority.isValidPriority("9312 1534")); // spaces within digits

        // valid priority numbers
        assertTrue(Priority.isValidPriority("low")); // low priority
        assertTrue(Priority.isValidPriority("medium")); // medium priority
        assertTrue(Priority.isValidPriority("high")); // high priority
    }

    @Test
    public void isHigherPriority_nullOtherPriority_throwsNullPointerException() {
        Priority currentPriority = new Priority("high");
        assertThrows(NullPointerException.class, () -> currentPriority.isHigherPriority(null));
    }

    @Test
    public void isHigherPriorityHigherPriority_returnsTrue() {
        Priority highPriority = new Priority("high");
        Priority mediumPriority = new Priority("medium");
        Priority lowPriority = new Priority("low");

        assertTrue(highPriority.isHigherPriority(mediumPriority));
        assertTrue(highPriority.isHigherPriority(lowPriority));
        assertTrue(mediumPriority.isHigherPriority(lowPriority));
    }

    @Test
    public void isHigherPriorityLowerPriority_returnsFalse() {
        Priority highPriority = new Priority("high");
        Priority mediumPriority = new Priority("medium");
        Priority lowPriority = new Priority("low");

        assertFalse(highPriority.isHigherPriority(highPriority));
        assertFalse(mediumPriority.isHigherPriority(mediumPriority));
        assertFalse(lowPriority.isHigherPriority(lowPriority));
        assertFalse(lowPriority.isHigherPriority(mediumPriority));
        assertFalse(mediumPriority.isHigherPriority(highPriority));
        assertFalse(lowPriority.isHigherPriority(highPriority));
    }
}
