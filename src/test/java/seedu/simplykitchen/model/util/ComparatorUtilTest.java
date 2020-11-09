package seedu.simplykitchen.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.model.util.ComparatorUtil.ComparatorInformation;
import static seedu.simplykitchen.model.util.ComparatorUtil.generateSortingComparatorsDescription;
import static seedu.simplykitchen.model.util.ComparatorUtil.getComparator;
import static seedu.simplykitchen.model.util.ComparatorUtil.isSortingComparatorsDescriptionValid;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.food.Food;

public class ComparatorUtilTest {

    @Test
    public void getComparator_nullComparatorDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> getComparator(null));
    }

    @Test
    public void generateSortingComparatorsDescription_nullSortingComparators_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> generateSortingComparatorsDescription(null));
    }

    @Test
    public void isSortingComparatorsDescriptionValid_nullSortingComparatorsDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> isSortingComparatorsDescriptionValid(null));
    }

    @Test
    public void getComparator_invalidComparatorDescription_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> getComparator("invalid"));
    }

    @Test
    public void generateSortingComparatorsDescription_invalidSortingComparators_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> generateSortingComparatorsDescription(new Comparator[]{}));
    }

    @Test
    public void getComparator_success() {
        String priorityComparatorDescription = ComparatorInformation.PRIORITY.getDescription();
        String expiryComparatorDescription = ComparatorInformation.EXPIRY.getDescription();
        String descriptionComparatorDescription = ComparatorInformation.DESCRIPTION.getDescription();

        assertEquals(ComparatorInformation.PRIORITY.getSortingComparators(),
                getComparator(priorityComparatorDescription));
        assertEquals(ComparatorInformation.EXPIRY.getSortingComparators(),
                getComparator(expiryComparatorDescription));
        assertEquals(ComparatorInformation.DESCRIPTION.getSortingComparators(),
                getComparator(descriptionComparatorDescription));
    }

    @Test
    public void getComparator_failure() {
        String priorityComparatorDescription = ComparatorInformation.PRIORITY.getDescription();
        String expiryComparatorDescription = ComparatorInformation.EXPIRY.getDescription();
        String descriptionComparatorDescription = ComparatorInformation.DESCRIPTION.getDescription();

        assertNotEquals(ComparatorInformation.EXPIRY.getSortingComparators(),
                getComparator(priorityComparatorDescription));
        assertNotEquals(ComparatorInformation.DESCRIPTION.getSortingComparators(),
                getComparator(expiryComparatorDescription));
        assertNotEquals(ComparatorInformation.PRIORITY.getSortingComparators(),
                getComparator(descriptionComparatorDescription));
    }

    @Test
    public void generateSortingComparatorsDescription_success() {
        Comparator<Food>[] prioritySortingComparators = ComparatorInformation.PRIORITY.getSortingComparators();
        Comparator<Food>[] expirySortingComparators = ComparatorInformation.EXPIRY.getSortingComparators();
        Comparator<Food>[] descriptionSortingComparators = ComparatorInformation.DESCRIPTION.getSortingComparators();

        assertEquals(ComparatorInformation.PRIORITY.getDescription(),
                generateSortingComparatorsDescription(prioritySortingComparators));
        assertEquals(ComparatorInformation.EXPIRY.getDescription(),
                generateSortingComparatorsDescription(expirySortingComparators));
        assertEquals(ComparatorInformation.DESCRIPTION.getDescription(),
                generateSortingComparatorsDescription(descriptionSortingComparators));
    }

    @Test
    public void generateSortingComparatorsDescription_failure() {
        Comparator<Food>[] prioritySortingComparators = ComparatorInformation.PRIORITY.getSortingComparators();
        Comparator<Food>[] expirySortingComparators = ComparatorInformation.EXPIRY.getSortingComparators();
        Comparator<Food>[] descriptionSortingComparators = ComparatorInformation.DESCRIPTION.getSortingComparators();

        assertNotEquals(ComparatorInformation.EXPIRY.getDescription(),
                generateSortingComparatorsDescription(prioritySortingComparators));
        assertNotEquals(ComparatorInformation.DESCRIPTION.getDescription(),
                generateSortingComparatorsDescription(expirySortingComparators));
        assertNotEquals(ComparatorInformation.PRIORITY.getDescription(),
                generateSortingComparatorsDescription(descriptionSortingComparators));
    }

    @Test
    public void isSortingComparatorsDescriptionValid_returnsTrue() {
        String priorityComparatorDescription = "descending priority";
        String expiryComparatorDescription = "ascending expiry date";
        String descriptionComparatorDescription = "description";

        assertTrue(isSortingComparatorsDescriptionValid(priorityComparatorDescription));
        assertTrue(isSortingComparatorsDescriptionValid(expiryComparatorDescription));
        assertTrue(isSortingComparatorsDescriptionValid(descriptionComparatorDescription));
    }

    @Test
    public void isSortingComparatorsDescriptionValid_invalidDescription_returnsFalse() {
        assertFalse(isSortingComparatorsDescriptionValid("invalid"));
    }
}
