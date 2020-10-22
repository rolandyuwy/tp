package seedu.simplykitchen.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedPositiveInteger ----------------------------

    @Test
    public void isNonZeroUnsignedPositiveInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedPositiveInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedPositiveInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedPositiveInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedPositiveInteger("10"));
    }

    //---------------- Tests for isNonZeroSignedDouble ---------------------------------------

    @Test
    public void isNonZeroSignedDouble() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroSignedDouble("")); // Boundary value
        assertFalse(StringUtil.isNonZeroSignedDouble("  "));

        // EP: not a double
        assertFalse(StringUtil.isNonZeroSignedDouble("a"));
        assertFalse(StringUtil.isNonZeroSignedDouble("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroSignedDouble("0"));
        assertFalse(StringUtil.isNonZeroSignedDouble("+0"));
        assertFalse(StringUtil.isNonZeroSignedDouble("-0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroSignedDouble("+01"));

        // EP: unsigned doubles
        assertFalse(StringUtil.isNonZeroSignedDouble("2"));
        assertFalse(StringUtil.isNonZeroSignedDouble("2.0"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroSignedDouble(" +10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroSignedDouble("+ 10")); // Space between sign and magnitude
        assertFalse(StringUtil.isNonZeroSignedDouble("+1 0")); // Spaces in the middle of a double

        // EP: valid signed doubles, should return true
        assertTrue(StringUtil.isNonZeroSignedDouble("+1")); // Integer will be converted to a double
        assertTrue(StringUtil.isNonZeroSignedDouble("-1"));
        assertTrue(StringUtil.isNonZeroSignedDouble("+2.0"));
        assertTrue(StringUtil.isNonZeroSignedDouble("-2.0"));
    }

    //---------------- Tests for isValidRangeOfDouble ----------------------------------------

    @Test
    public void isValidRangeOfDouble() {
        // EP: numbers smaller than -Double.MAX_VALUE and larger than Double.MAX_VALUE
        assertFalse(StringUtil.isValidRangeOfDouble("+" + Double.MAX_VALUE));
        assertFalse(StringUtil.isValidRangeOfDouble("+" + Double.POSITIVE_INFINITY));
        assertFalse(StringUtil.isValidRangeOfDouble("-" + Double.MAX_VALUE));
        assertFalse(StringUtil.isValidRangeOfDouble(String.valueOf(Double.NEGATIVE_INFINITY)));
    }

    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsWordIgnoreCase("    ", "123"));

        // Matches a partial word only
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

}
