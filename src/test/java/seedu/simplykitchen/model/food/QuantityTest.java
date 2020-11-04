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
        // EP: null quantity
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // EP: invalid quantity values
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity(" 1 ")); // number with whitespace
        assertFalse(Quantity.isValidQuantity("1 1")); // spacing between a number
        assertFalse(Quantity.isValidQuantity("9011p041")); // alphabets within digits
        assertFalse(Quantity.isValidQuantity(String.valueOf(Quantity.ZERO_VALUE))); // lower bound
        assertFalse(Quantity.isValidQuantity("-0.01")); // below lower bound
        assertFalse(Quantity.isValidQuantity("100000.01")); // above upper bound
        assertFalse(Quantity.isValidQuantity("150000.00"));
        assertFalse(Quantity.isValidQuantity("100000.")); // ends with decimal point
        assertFalse(Quantity.isValidQuantity("0.500")); // more than 2 decimal places

        // EP: invalid quantity units
        assertFalse(Quantity.isValidQuantity("1 @u")); // special character in unit
        assertFalse(Quantity.isValidQuantity("1 2u")); // number in unit
        assertFalse(Quantity.isValidQuantity("1 u n")); // space in unit

        // EP: valid quantities
        assertTrue(Quantity.isValidQuantity("01")); // zero as prefix
        assertTrue(Quantity.isValidQuantity("1")); // value with zero decimal place, default unit
        assertTrue(Quantity.isValidQuantity("1 kg")); // value with zero decimal place, custom unit
        assertTrue(Quantity.isValidQuantity("1.50")); // value with 2 decimal places, default unit
        assertTrue(Quantity.isValidQuantity("1.50 kg")); // value with 2 decimal places, custom unit
        assertTrue(Quantity.isValidQuantity(".5 kg")); // decimal missing leading digit, custom unit
        assertTrue(Quantity.isValidQuantity(String.valueOf(Quantity.ZERO_VALUE + 0.01))); // lower bound
        assertTrue(Quantity.isValidQuantity(String.valueOf(Quantity.MAX_VALUE))); // upper bound
        assertTrue(Quantity.isValidQuantity("100000")); // upper bound converted to integer
    }
}
