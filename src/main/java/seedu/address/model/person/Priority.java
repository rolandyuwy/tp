package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Food item priority level in the food inventory system.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {
    public enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Priority should be either high, medium or low.";
//    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final Level value;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority level.
     */
    public Priority(String priority) {
        priority = priority.toLowerCase();
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        switch(priority) {
        case "low":
            value = Level.LOW;
            break;
        case "medium":
            value = Level.MEDIUM;
            break;
        case "high":
            value = Level.HIGH;
            break;
        default:
            value = null;
        }
    }

    /**
     * Returns true if a given string is a valid priority number.
     */
    public static boolean isValidPriority(String priority) {
        priority = priority.toLowerCase();
        return priority.equals("low") || priority.equals("medium") || priority.equals("high");
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
