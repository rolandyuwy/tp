package seedu.simplykitchen.model.food;

import static seedu.simplykitchen.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.simplykitchen.model.tag.Tag;

/**
 * Represents a Food item in the Food inventory.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Food {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final ExpiryDate expiryDate;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Food(Name name, Phone phone, ExpiryDate expiryDate, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, expiryDate, address, tags);
        this.name = name;
        this.phone = phone;
        this.expiryDate = expiryDate;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both food items of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two food items.
     */
    public boolean isSameFood(Food otherFood) {
        if (otherFood == this) {
            return true;
        }

        return otherFood != null
                && otherFood.getName().equals(getName())
                && (otherFood.getPhone().equals(getPhone()) || otherFood.getExpiryDate().equals(getExpiryDate()));
    }

    /**
     * Returns true if both food items have the same identity and data fields.
     * This defines a stronger notion of equality between two food items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Food)) {
            return false;
        }

        Food otherFood = (Food) other;
        return otherFood.getName().equals(getName())
                && otherFood.getPhone().equals(getPhone())
                && otherFood.getExpiryDate().equals(getExpiryDate())
                && otherFood.getAddress().equals(getAddress())
                && otherFood.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, expiryDate, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" ExpiryDate: ")
                .append(getExpiryDate())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
