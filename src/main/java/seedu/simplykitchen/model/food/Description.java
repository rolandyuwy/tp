package seedu.simplykitchen.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

/**
 * Represents a Food's description in the Food inventory.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces and it should not be blank.";
    public static final String MESSAGE_EXCEED_LIMIT = "Description should not exceed 50 characters.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}' ]*";

    public final String fullDescription;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), generateErrorMessage(description));
        fullDescription = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        if (test.length() > 50) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Generates an error message based on why the description is invalid.
     *
     * @param invalidDescription An invalid description.
     * @return A string describing the error message.
     */
    public static String generateErrorMessage(String invalidDescription) {
        if (invalidDescription.length() > 50) {
            return MESSAGE_EXCEED_LIMIT;
        }
        return MESSAGE_CONSTRAINTS;
    }


    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && fullDescription.toLowerCase().equals(((Description) other).fullDescription.toLowerCase()));
        // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }

}
