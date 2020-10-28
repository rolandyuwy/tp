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
        value = padDayAndMonthWithZero(replaceSlashWithDash(expiryDateString));
    }

    /**
     * Returns if a given string is a valid expiry date.
     */
    public static boolean isValidExpiryDate(String testExpiryDateString) {
        try {
            testExpiryDateString = replaceSlashWithDash(testExpiryDateString);

            // check for invalid formatting of expiry date
            SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DATE_PATTERN);
            simpleDateFormatter.setLenient(false);
            simpleDateFormatter.parse(testExpiryDateString);

            // check for shortened year or invalid expiry date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDate.parse(testExpiryDateString, formatter);

            return true;
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
        if (split.length != 3) {
            // invalid formatting of expiry date
            return MESSAGE_CONSTRAINTS;
        }
        if (!isFourDigitYear(split[2])) {
            // year of expiry date is not 4 digits long
            return MESSAGE_SHORTENED_YEAR;
        }

        // expiry date does not exist
        return MESSAGE_INVALID_DATE;
    }

    /**
     * Replaces all occurrences of '/' with '-'.
     */
    private static String replaceSlashWithDash(String expiryDateString) {
        return expiryDateString.replace('/', '-');
    }

    /**
     * Pads the day and month string with zero
     * Guarantees: The date string is formatted correctly.
     */
    private static String padDayAndMonthWithZero(String dateString) {
        assert isValidExpiryDate(dateString);
        dateString = replaceSlashWithDash(dateString);
        String[] split = dateString.split("-");
        String day = split[0];
        String month = split[1];
        String year = split[2];

        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }

        return day + "-" + month + "-" + year;
    }

    /**
     * Guarantees: The date string is formatted correctly.
     */
    private static boolean isFourDigitYear(String yearString) {
        return yearString.length() == 4;
    }

    /**
     * Returns true if a given {@code expiryDate}'s expiry date is after the expiry date.
     */
    public boolean isAfter(ExpiryDate expiryDate) {
        try {
            String expiryDateString1 = padDayAndMonthWithZero(replaceSlashWithDash(this.value));
            String expiryDateString2 = padDayAndMonthWithZero(replaceSlashWithDash(expiryDate.value));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDate localDate1 = LocalDate.parse(expiryDateString1, formatter);
            LocalDate localDate2 = LocalDate.parse(expiryDateString2, formatter);

            return localDate1.isAfter(localDate2);
        } catch (DateTimeParseException e) {
            assert false : "Expiry Dates should be valid";
            return false;
        }
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
