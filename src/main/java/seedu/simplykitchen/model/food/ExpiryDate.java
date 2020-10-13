package seedu.simplykitchen.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Food Item's expiry date in Simply Kitchen.
 * Guarantees: immutable; is valid as declared in {@link #isValidExpiryDate(String)}
 */
public class ExpiryDate {

    public static final String MESSAGE_CONSTRAINTS =
            "The expiry date should be of the format DD-MM-YYYY or DD/MM/YYYY. ";
    public static final String MESSAGE_PAST_EXPIRY_DATE = "This food item has already expired! ";
    public static final String MESSAGE_SHORTENED_YEAR = "The year should be 4 digits long. ";
    public static final String MESSAGE_INVALID_DATE = "The expiry date does not exist. ";
    public static final String DATE_PATTERN = "d-M-yyyy";

    public final String value;

    /**
     * Constructs an {@code ExpiryDate}.
     *
     * @param expiryDateString A valid expiry date.
     */
    public ExpiryDate(String expiryDateString) {
        requireNonNull(expiryDateString);
        checkArgument(isValidExpiryDate(expiryDateString), generateErrorMessage(expiryDateString));
        value = replaceSlashWithDash(expiryDateString);
    }

    /**
     * Returns if a given string is a valid expiry date.
     */
    public static boolean isValidExpiryDate(String testExpiryDateString) {
        try {
            testExpiryDateString = replaceSlashWithDash(testExpiryDateString);

            // check for invalid formatting of expiry date
            formatExpiryDate(testExpiryDateString);

            // check for shortened year or invalid expiry date
            LocalDate expiryDate = parseExpiryDate(testExpiryDateString);

            // check for past expiry date
            return !isBeforeToday(expiryDate);
        } catch (ParseException | DateTimeParseException e) {
            return false;
        }
    }

    private static void formatExpiryDate(String expiryDateString) throws ParseException {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DATE_PATTERN);
        simpleDateFormatter.setLenient(false);
        simpleDateFormatter.parse(expiryDateString);
    }

    private static LocalDate parseExpiryDate(String expiryDateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(expiryDateString, formatter);
    }

    private static boolean isBeforeToday(LocalDate expiryDate) throws DateTimeParseException {
        LocalDate todayDate = LocalDate.now();
        return todayDate.isAfter(expiryDate);
    }

    /**
     * Generates an error message based on why the expiry date is invalid.
     *
     * @param invalidExpiryDateString An invalid expiry date.
     * @return A string describing the error message.
     */
    public static String generateErrorMessage(String invalidExpiryDateString) {
        try {
            formatExpiryDate(invalidExpiryDateString);

            LocalDate expiryDate = parseExpiryDate(invalidExpiryDateString);
            if (isBeforeToday(expiryDate)) {
                return MESSAGE_PAST_EXPIRY_DATE;
            }

            return MESSAGE_CONSTRAINTS;
        } catch (ParseException e) {
            return MESSAGE_CONSTRAINTS;
        } catch (DateTimeParseException e) {
            if (!isFourDigitYear(invalidExpiryDateString)) {
                return MESSAGE_SHORTENED_YEAR;
            } else {
                return MESSAGE_INVALID_DATE;
            }
        }
    }

    /**
     * Guarantees: The date String is formatted correctly.
     */
    private static boolean isFourDigitYear(String dateString) {
        dateString = replaceSlashWithDash(dateString);
        String[] split = dateString.split("-");
        return split[2].length() == 4;
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
