package seedu.simplykitchen.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.logic.commands.SortDescCommand.SORT_DESC_COMPARATORS;
import static seedu.simplykitchen.logic.commands.SortExpiryCommand.SORT_EXPIRY_COMPARATORS;
import static seedu.simplykitchen.logic.commands.SortPriorityCommand.SORT_PRIORITY_COMPARATORS;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.model.food.Food;


/**
 * Contains utility comparators for sorting food lists.
 */
public class ComparatorUtil {
    public static final Comparator<Food> SORT_BY_ASCENDING_EXPIRY_DATE = (food1, food2) ->
                food1.getExpiryDate().isAfter(food2.getExpiryDate())
                    ? 1
                    : food1.getExpiryDate().equals(food2.getExpiryDate())
                        ? 0
                        : -1;
    public static final Comparator<Food> SORT_BY_DESCENDING_PRIORITY = (food1, food2) ->
                food1.getPriority().isHigherPriority(food2.getPriority())
                    ? -1
                    : food1.getPriority().equals(food2.getPriority())
                        ? 0
                        : 1;
    public static final Comparator<Food> SORT_BY_LEXICOGRAPHICAL_ORDER = (food1, food2) ->
                food1.getDescription().fullDescription.compareTo(food2.getDescription().fullDescription);
    public static final Comparator<Food> SORT_BY_FIRST_CHARACTER = (food1, food2) ->
                food1.getDescription().compareFirstCharacterTo(food2.getDescription());
    public static final Comparator<Food> SORT_BY_DESC_THEN_ASC_EXPIRY = SORT_BY_ASCENDING_EXPIRY_DATE
            .thenComparing(SORT_BY_FIRST_CHARACTER).thenComparing(SORT_BY_LEXICOGRAPHICAL_ORDER);

    private static Logger logger = LogsCenter.getLogger("ComparatorUtil.class");

    /**
     * Enumerates all comparator information required for sorting commands.
     */
    public enum ComparatorInformation {
        PRIORITY("descending priority", SORT_PRIORITY_COMPARATORS),
        DESCRIPTION("description", SORT_DESC_COMPARATORS),
        EXPIRY("ascending expiry date", SORT_EXPIRY_COMPARATORS);

        private final String description;
        private final Comparator<Food>[] sortingComparators;

        /**
         * Initialises the ComparatorInformation with the {@code description} and {@code sortingComparators}.
         *
         * @param description Comparator description.
         * @param sortingComparators Array of food comparators.
         */
        ComparatorInformation(String description, Comparator<Food>[] sortingComparators) {
            this.description = description;
            this.sortingComparators = sortingComparators;
        }

        /**
         * Gets the comparator description.
         *
         * @return Comparator description.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Gets the sorting comparators.
         *
         * @return Sorting comparators.
         */
        public Comparator<Food>[] getSortingComparators() {
            return sortingComparators;
        }
    }

    /**
     * Gets the foodInventorySortingComparators information according to {@code comparatorDescription}.
     * @return The foodInventorySortingComparators information.
     */
    public static Comparator<Food>[] getComparator(String comparatorDescription) {
        requireNonNull(comparatorDescription);
        logger.log(Level.INFO, "Getting sorting comparators for: " + comparatorDescription);
        for (ComparatorInformation comparatorInformation : ComparatorInformation.values()) {
            if (comparatorDescription.equals(comparatorInformation.getDescription())) {
                return comparatorInformation.getSortingComparators();
            }
        }
        assert false : "Comparator should be valid";
        throw new IllegalArgumentException("Comparator is not valid.");
    }

    /**
     * Gets a String describing the sorting used according to {@code foodInventorySortingComparators}.
     * @return Description of sorting used.
     */
    public static String generateSortingComparatorsDescription(Comparator<Food>[] sortingComparators) {
        requireNonNull(sortingComparators);
        logger.log(Level.INFO, "Generating sorting comparators description");
        for (ComparatorInformation comparatorInformation : ComparatorInformation.values()) {
            if (sortingComparators.equals(comparatorInformation.getSortingComparators())) {
                logger.log(Level.INFO, "Sorting comparators description generated: "
                        + comparatorInformation.getDescription());
                return comparatorInformation.getDescription();
            }
        }
        assert false : "Comparator should be valid";
        throw new IllegalArgumentException("Comparator is not valid.");
    }

    /**
     * Checks if the description of sorting comparators {@code sortingComparatorsDescription} is valid.
     * @return If the description of sorting comparators is valid.
     */
    public static boolean isSortingComparatorsDescriptionValid(String sortingComparatorsDescription) {
        requireNonNull(sortingComparatorsDescription);
        for (ComparatorInformation comparatorInformation : ComparatorInformation.values()) {
            if (sortingComparatorsDescription.equals(comparatorInformation.getDescription())) {
                return true;
            }
        }
        return false;
    }
}
