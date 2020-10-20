package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void isValidQuantity() {
        // null quantity
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // invalid quantity values
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity("-91")); // negative number
        assertFalse(Quantity.isValidQuantity("0")); // zero
        assertFalse(Quantity.isValidQuantity("9011p041")); // alphabets within digits

        // invalid quantity units
        assertFalse(Quantity.isValidQuantity("1 @u")); // special character in unit
        assertFalse(Quantity.isValidQuantity("1 2u")); // number in unit
        assertFalse(Quantity.isValidQuantity("1 u n")); // space in unit

        // valid quantities
        assertTrue(Quantity.isValidQuantity("1")); // integer, default unit
        assertTrue(Quantity.isValidQuantity("1 kg")); // integer, custom unit
        assertTrue(Quantity.isValidQuantity("1.4 lb")); // decimal, custom unit
        assertTrue(Quantity.isValidQuantity(".4 lb")); // decimal missing leading digit, custom unit
    }
}
