package seedu.simplykitchen.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // valid tag
        assertTrue(Tag.isValidTagName("a1#$%&-()")); // acceptable characters
        assertTrue(Tag.isValidTagName("aaaaaaaaaabbbbbbbbbbcccccccccc")); // 30 characters

        // invalid tag
        assertFalse(Tag.isValidTagName("")); // empty tag
        assertFalse(Tag.isValidTagName("@")); // unacceptable character
        assertFalse(Tag.isValidTagName("aaaaaaaaaabbbbbbbbbbccccccccccd")); // 31 characters
    }

}
