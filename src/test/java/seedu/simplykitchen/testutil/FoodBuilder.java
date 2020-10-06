package seedu.simplykitchen.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.simplykitchen.model.food.Address;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Name;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.model.util.SampleDataUtil;

/**
 * A utility class to help with building Food objects.
 */
public class FoodBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PRIORITY = "medium";
    public static final String DEFAULT_EXPIRYDATE = "1-1-2020";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Priority priority;
    private ExpiryDate expiryDate;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code FoodBuilder} with the default details.
     */
    public FoodBuilder() {
        name = new Name(DEFAULT_NAME);
        priority = new Priority(DEFAULT_PRIORITY);
        expiryDate = new ExpiryDate(DEFAULT_EXPIRYDATE);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FoodBuilder with the data of {@code foodToCopy}.
     */
    public FoodBuilder(Food foodToCopy) {
        name = foodToCopy.getName();
        priority = foodToCopy.getPriority();
        expiryDate = foodToCopy.getExpiryDate();
        address = foodToCopy.getAddress();
        tags = new HashSet<>(foodToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Food} that we are building.
     */
    public FoodBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Food} that we are building.
     */
    public FoodBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Food} that we are building.
     */
    public FoodBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Food} that we are building.
     */
    public FoodBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code Food} that we are building.
     */
    public FoodBuilder withExpiryDate(String expiryDate) {
        this.expiryDate = new ExpiryDate(expiryDate);
        return this;
    }

    public Food build() {
        return new Food(name, priority, expiryDate, address, tags);
    }

}
