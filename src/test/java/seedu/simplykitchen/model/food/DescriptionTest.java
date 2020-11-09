package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDescription("peanut*")); // contains non-alphanumeric characters
        assertFalse(Description.isValidDescription(
                "aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeef")); // more than 50 characters

        // valid description
        assertTrue(Description.isValidDescription("peanut butter")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("TWG 1864")); // alphanumeric characters
        assertTrue(Description.isValidDescription("Capital Tan")); // with capital letters
        assertTrue(Description.isValidDescription("Lay's Sour Cream Flavoured Potato Chips")); // long description
        assertTrue(Description.isValidDescription(
                "aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeee")); // 50 characters
    }

    @Test
    public void compareFirstCharacterTo_nullDescription_throwsNullPointerException() {
        Description currentDescription = new Description("description");
        assertThrows(NullPointerException.class, () -> currentDescription.compareFirstCharacterTo(null));
    }

    @Test
    public void compareFirstCharacterToHigherPrecedence_success() {
        Description currentDescription = new Description("apple");

        //upper case
        assertEquals(-1, currentDescription.compareFirstCharacterTo(new Description("Apple")));

        //higher precedence lexicographically
        assertEquals(-1, currentDescription.compareFirstCharacterTo(new Description("banana")));
        assertEquals(-1, currentDescription.compareFirstCharacterTo(new Description("cabbage")));
    }

    @Test
    public void compareFirstCharacterToEqualPrecedence_success() {
        Description currentDescription1 = new Description("apple");
        Description currentDescription2 = new Description("Apple");

        //both lower case
        assertEquals(0, currentDescription1.compareFirstCharacterTo(new Description("a")));
        assertEquals(0, currentDescription1.compareFirstCharacterTo(new Description("apricot")));

        //both upper case
        assertEquals(0, currentDescription2.compareFirstCharacterTo(new Description("Apricot")));
    }

    @Test
    public void compareFirstCharacterToLowerPrecedence_success() {
        Description currentDescription = new Description("Cabbage");

        //lower case
        assertEquals(1, currentDescription.compareFirstCharacterTo(new Description("carrot")));

        //lower precedence lexicographically
        assertEquals(1, currentDescription.compareFirstCharacterTo(new Description("Apple")));
        assertEquals(1, currentDescription.compareFirstCharacterTo(new Description("banana")));
    }
}
