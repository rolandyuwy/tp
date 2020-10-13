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

    // A date has three values - day, month and year
    public static final int NUM_OF_VALUES = 3;
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

            // check for invalid expiry date
            SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DATE_PATTERN);
            simpleDateFormatter.setLenient(false);
            simpleDateFormatter.parse(testExpiryDateString);

            // check for shortened year
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDate localDate = LocalDate.parse(testExpiryDateString, dateTimeFormatter);

            // check for past expiry date
            return !isBeforeToday(localDate);
        } catch (ParseException | DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Generates an error message based on why the expiry date is invalid.
     *
     * @param invalidExpiryDateString An invalid expiry date.
     * @return A string describing the error message.
     */
    public static String generateErrorMessage(String invalidExpiryDateString) {
        invalidExpiryDateString = replaceSlashWithDash(invalidExpiryDateString);
        String[] split = invalidExpiryDateString.split("-");

        if (split.length != NUM_OF_VALUES) {
            // the expiry date format is wrong
            return MESSAGE_CONSTRAINTS;
        } else if (isBeforeToday(invalidExpiryDateString)) {
            // the expiry date is before today
            return MESSAGE_PAST_EXPIRY_DATE;
        } else if (!isFourDigitYear(split[2])) {
            // the year is not 4 digits long
            return MESSAGE_SHORTENED_YEAR;
        } else {
            // the expiry date does not exist
            return MESSAGE_INVALID_DATE;
        }
    }

    private static boolean isBeforeToday(LocalDate expiryDate) {
        LocalDate todayDate = LocalDate.now();
        return todayDate.isAfter(expiryDate);
    }

    /**
     * Guarantees: the expiry date String is correctly formatted
     */
    private static boolean isBeforeToday(String expiryDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate expiryDate = LocalDate.parse(expiryDateString, formatter);
        return isBeforeToday(expiryDate);
    }

    private static boolean isFourDigitYear(String year) {
        return year.length() == 4;
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
