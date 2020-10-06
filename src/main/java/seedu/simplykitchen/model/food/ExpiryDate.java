package seedu.simplykitchen.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Food Item's expiry date in Simply Kitchen.
 * Guarantees: immutable; is valid as declared in {@link #isValidExpiryDate(String)}
 */
public class ExpiryDate {

    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format dd-MM-yyyy or dd/MM/yyyy. ";
    public static final String DATE_PATTERN = "d-M-yyyy";

    public final String value;

    /**
     * Constructs an {@code ExpiryDate}.
     *
     * @param expiryDateString A valid expiry date.
     */
    public ExpiryDate(String expiryDateString) {
        requireNonNull(expiryDateString);
        checkArgument(isValidExpiryDate(expiryDateString), MESSAGE_CONSTRAINTS);
        value = replaceSlashWithDash(expiryDateString);
    }

    /**
     * Returns if a given string is a valid expiry date.
     */
    public static boolean isValidExpiryDate(String testExpiryDateString) {
        try {
            testExpiryDateString = replaceSlashWithDash(testExpiryDateString);
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDate.parse(testExpiryDateString, inputFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Replaces all occurrences of '/' with '-'.
     */
    private static String replaceSlashWithDash(String expiryDateString) {
        return expiryDateString.replace('/', '-');
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDate // instanceof handles nulls
                && value.equals(((ExpiryDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
