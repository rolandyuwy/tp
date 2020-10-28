package seedu.simplykitchen.model.util;

import java.util.Comparator;

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
}
