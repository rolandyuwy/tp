package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExpiryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpiryDate(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String invalidExpiryDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate(invalidExpiryDate));
    }

    @Test
    public void isValidExpiryDate() {
        // null expiry date
        assertThrows(NullPointerException.class, () -> ExpiryDate.isValidExpiryDate(null));

        // blank expiry date
        assertFalse(ExpiryDate.isValidExpiryDate("")); // empty string
        assertFalse(ExpiryDate.isValidExpiryDate(" ")); // spaces only

        // invalid expiry date
        assertFalse(ExpiryDate.isValidExpiryDate("06/2020")); // missing day
        assertFalse(ExpiryDate.isValidExpiryDate("1-6-20")); // shortened year
        assertFalse(ExpiryDate.isValidExpiryDate("32-9-2020")); // day does not exist
        assertFalse(ExpiryDate.isValidExpiryDate("1-13-2020")); // month does not exist
        assertFalse(ExpiryDate.isValidExpiryDate("31-9-20202")); // year does not exist
        assertFalse(ExpiryDate.isValidExpiryDate("1--10-2020")); // extra dash
        assertFalse(ExpiryDate.isValidExpiryDate("1//10/2020")); // extra slash
        assertFalse(ExpiryDate.isValidExpiryDate("2020-1-1")); // wrong date format

        // valid expiry date
        assertTrue(ExpiryDate.isValidExpiryDate("1-9-2020"));
        assertTrue(ExpiryDate.isValidExpiryDate("01-09-2020"));
        assertTrue(ExpiryDate.isValidExpiryDate("1/9/2020"));
        assertTrue(ExpiryDate.isValidExpiryDate("01/09/2020"));
    }
}
