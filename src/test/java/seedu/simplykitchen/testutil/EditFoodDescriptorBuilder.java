package seedu.simplykitchen.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.simplykitchen.logic.commands.EditCommand;
import seedu.simplykitchen.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.simplykitchen.model.food.Address;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Name;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.tag.Tag;

/**
 * A utility class to help with building EditFoodDescriptor objects.
 */
public class EditFoodDescriptorBuilder {

    private EditFoodDescriptor descriptor;

    public EditFoodDescriptorBuilder() {
        descriptor = new EditFoodDescriptor();
    }

    public EditFoodDescriptorBuilder(EditCommand.EditFoodDescriptor descriptor) {
        this.descriptor = new EditFoodDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFoodDescriptor} with fields containing {@code food}'s details
     */
    public EditFoodDescriptorBuilder(Food food) {
        descriptor = new EditFoodDescriptor();
        descriptor.setName(food.getName());
        descriptor.setPriority(food.getPriority());
        descriptor.setExpiryDate(food.getExpiryDate());
        descriptor.setAddress(food.getAddress());
        descriptor.setTags(food.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withExpiryDate(String expiryDate) {
        descriptor.setExpiryDate(new ExpiryDate(expiryDate));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditFoodDescriptor}
     * that we are building.
     */
    public EditFoodDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditFoodDescriptor build() {
        return descriptor;
    }
}
