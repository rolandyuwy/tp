package seedu.simplykitchen.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

/**
 * Represents a Food's Quantity in the Food inventory.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)}
 */
public class Quantity {
    public static final String QUANTITY_VALUE_CONSTRAINTS =
            "The value in the quantity field must be a positive number.";
    public static final String QUANTITY_UNIT_CONSTRAINTS =
            "The unit in the quantity field is optional. If provided, unit should only contain alphabets.";
    public static final String MESSAGE_CONSTRAINTS =
            "The quantity field must contain a positive number followed by an optional unit. "
                    + "\nIt should not be blank.";

    public static final String UNIT_VALIDATION_REGEX = "[a-zA-z]*";
    public static final String VALUE_VALIDATION_REGEX = "[0-9]*[.]?[0-9]*";
    public static final String DEFAULT_UNIT = "unit";
    public final double value;
    public final String unit;

    /**
     * Constructor when no unit is provided.
     *
     * @param quantity a quantity string
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), generateErrorMessage(quantity));
        int indexOfSpace = quantity.indexOf(" ");
        String value = quantity;
        String unit = DEFAULT_UNIT;
        if (indexOfSpace > 0) {
            value = quantity.substring(0, indexOfSpace);
            unit = quantity.substring(indexOfSpace).trim();
        }
        this.value = Double.parseDouble(value);
        this.unit = unit;
    }

    /**
     * Returns if given parameters is a valid quantity string.
     */
    public static boolean isValidQuantity(String quantity) {
        if (quantity.length() == 0) {
            return false;
        }
        int indexOfSpace = quantity.indexOf(" ");
        String value = quantity;
        String unit = DEFAULT_UNIT;
        if (indexOfSpace > 0) {
            value = quantity.substring(0, indexOfSpace);
            unit = quantity.substring(indexOfSpace).trim();
        }

        if (unit.matches(UNIT_VALIDATION_REGEX) && value.matches(VALUE_VALIDATION_REGEX)) {
            if (Double.parseDouble(value) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates an error message based on why the quantity is invalid.
     *
     * @param quantity invalid quantity string
     * @return A string describing the error message.
     */
    public static String generateErrorMessage(String quantity) {
        if (quantity.length() == 0) {
            return MESSAGE_CONSTRAINTS;
        }
        int indexOfSpace = quantity.indexOf(" ");
        String value = quantity;
        String unit = DEFAULT_UNIT;
        if (indexOfSpace > 0) {
            value = quantity.substring(0, indexOfSpace);
            unit = quantity.substring(indexOfSpace).trim();
        }

        if (!value.matches(VALUE_VALIDATION_REGEX)) {
            // invalid quantity value
            return QUANTITY_VALUE_CONSTRAINTS;
        }

        if (Double.parseDouble(value) <= 0) {
            // if the value is not a positive number
            return QUANTITY_VALUE_CONSTRAINTS;
        }

        if (!unit.matches(UNIT_VALIDATION_REGEX)) {
            // unit does not match the validation regular expression
            return QUANTITY_UNIT_CONSTRAINTS;
        }

        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && value == (((Quantity) other).value)
                && unit.equals(((Quantity) other).unit)); // state check
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }
}
