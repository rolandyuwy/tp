package seedu.simplykitchen.model.util;

import seedu.simplykitchen.model.food.Food;

import java.util.Comparator;

import static seedu.simplykitchen.model.food.ExpiryDate.isAfter;

/**
 * Contains utility comparators for sorting food lists.
 */
public class ComparatorUtil {
    public static final Comparator<Food> SORT_BY_DESCENDING_EXPIRY_DATE = (
            food1, food2) ->
                isAfter(food1.getExpiryDate(), food2.getExpiryDate())
                    ? food1.getExpiryDate().equals(food2.getExpiryDate())
                        ? 0
                        : -1
                    : 1;
    public static final Comparator<Food> SORT_BY_ASCENDING_EXPIRY_DATE = ( //v1.3
            food1, food2) ->
                isAfter(food1.getExpiryDate(), food2.getExpiryDate())
                    ? food1.getExpiryDate().equals(food2.getExpiryDate())
                        ? 0
                        : 1
                    : -1;
    public static final Comparator<Food> SORT_BY_ASCENDING_PRIORITY = (
            food1, food2) ->
                food1.getPriority().isHigherPriority(food2.getPriority())
                    ? food1.getPriority().equals(food2.getPriority())
                        ? 0
                        : 1
                    : -1;
    public static final Comparator<Food> SORT_BY_DESCENDING_PRIORITY = ( //v1.3
            food1, food2) ->
                food1.getPriority().isHigherPriority(food2.getPriority())
                    ? food1.getPriority().equals(food2.getPriority())
                        ? 0
                        : -1
                    : 1;
    public static final Comparator<Food> SORT_BY_DESCENDING_DESCRIPTION = (
            food1, food2) -> food2.getDescription().fullDescription.compareTo(food1.getDescription().fullDescription);
    public static final Comparator<Food> SORT_BY_ASCENDING_DESCRIPTION = ( //v1.3
            food1, food2) -> food1.getDescription().fullDescription.compareTo(food2.getDescription().fullDescription);
}
