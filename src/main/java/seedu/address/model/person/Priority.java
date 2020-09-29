package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Food item priority level in the food inventory system.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(Level)}
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
     * Constructs a {@code Phone}.
     *
     * @param priority A valid priority level.
     */
    public Priority(Level priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPriority(Level priority) {
        return priority == Level.HIGH || priority == Level.MEDIUM || priority == Level.LOW;
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
