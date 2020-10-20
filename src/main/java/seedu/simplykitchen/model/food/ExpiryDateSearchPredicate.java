package seedu.simplykitchen.model.food;

import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tests that a {@code Food}'s {@code Priority} matches the search given.
 */
public class ExpiryDateSearchPredicate implements Predicate<Food> {
    private static Logger logger = Logger.getLogger("ExpiryDateSearchPredicate_log");

    private final String expiryDateSearch;

    /**
     * @param expiryDateSearch The expiry date to search for.
     */
    public ExpiryDateSearchPredicate(String expiryDateSearch) {
        logger.log(Level.INFO, "Creating a new ExpiryDateSearchPredicate object");
        assert expiryDateSearch.length() > 0 : "Expiry date cannot be blank.";
        logger.log(Level.INFO, "Search expiry date: " + expiryDateSearch);
        this.expiryDateSearch = expiryDateSearch;
        logger.log(Level.INFO, "Object created");
    }

    @Override
    public boolean test(Food food) {
        return food.getExpiryDate().value.equals(expiryDateSearch);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDateSearchPredicate // instanceof handles nulls
                && expiryDateSearch.equals(((ExpiryDateSearchPredicate) other).expiryDateSearch)); // state check
    }

}
