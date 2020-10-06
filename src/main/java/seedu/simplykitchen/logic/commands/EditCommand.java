package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.simplykitchen.model.Model.PREDICATE_SHOW_ALL_FOODS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.simplykitchen.commons.core.Messages;
import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.commons.util.CollectionUtil;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.Address;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Phone;
import seedu.simplykitchen.model.tag.Tag;

/**
 * Edits the details of an existing food item in the Food inventory.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the food item identified "
            + "by the index number used in the displayed food list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EXPIRYDATE + "EXPIRY DATE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EXPIRYDATE + "1-11-2021";

    public static final String MESSAGE_EDIT_FOOD_SUCCESS = "Edited Food Item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FOOD = "This food item already exists in the food inventory.";

    private final Index index;
    private final EditFoodDescriptor editFoodDescriptor;

    /**
     * @param index of the food item in the filtered food list to edit
     * @param editFoodDescriptor details to edit the food item with
     */
    public EditCommand(Index index, EditFoodDescriptor editFoodDescriptor) {
        requireNonNull(index);
        requireNonNull(editFoodDescriptor);

        this.index = index;
        this.editFoodDescriptor = new EditFoodDescriptor(editFoodDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToEdit = lastShownList.get(index.getZeroBased());
        Food editedFood = createEditedFood(foodToEdit, editFoodDescriptor);

        if (!foodToEdit.isSameFood(editedFood) && model.hasFood(editedFood)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOOD);
        }

        model.setFood(foodToEdit, editedFood);
        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        return new CommandResult(String.format(MESSAGE_EDIT_FOOD_SUCCESS, editedFood));
    }

    /**
     * Creates and returns a {@code Food} with the details of {@code foodToEdit}
     * edited with {@code editFoodDescriptor}.
     */
    private static Food createEditedFood(Food foodToEdit, EditFoodDescriptor editFoodDescriptor) {
        assert foodToEdit != null;

        Description updatedDescription = editFoodDescriptor.getDescription().orElse(foodToEdit.getDescription());
        Phone updatedPhone = editFoodDescriptor.getPhone().orElse(foodToEdit.getPhone());
        ExpiryDate updatedExpiryDate = editFoodDescriptor.getExpiryDate().orElse(foodToEdit.getExpiryDate());
        Address updatedAddress = editFoodDescriptor.getAddress().orElse(foodToEdit.getAddress());
        Set<Tag> updatedTags = editFoodDescriptor.getTags().orElse(foodToEdit.getTags());


        return new Food(updatedDescription, updatedPhone, updatedExpiryDate, updatedAddress, updatedTags);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFoodDescriptor.equals(e.editFoodDescriptor);
    }

    /**
     * Stores the details to edit the food item with. Each non-empty field value will replace the
     * corresponding field value of the food item.
     */
    public static class EditFoodDescriptor {
        private Description description;
        private Phone phone;
        private ExpiryDate expiryDate;
        private Address address;
        private Set<Tag> tags;

        public EditFoodDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFoodDescriptor(EditFoodDescriptor toCopy) {
            setDescription(toCopy.description);
            setPhone(toCopy.phone);
            setExpiryDate(toCopy.expiryDate);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {

            return CollectionUtil.isAnyNonNull(description, phone, expiryDate, address, tags);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setExpiryDate(ExpiryDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Optional<ExpiryDate> getExpiryDate() {
            return Optional.ofNullable(expiryDate);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFoodDescriptor)) {
                return false;
            }

            // state check
            EditFoodDescriptor e = (EditFoodDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getPhone().equals(e.getPhone())
                    && getExpiryDate().equals(e.getExpiryDate())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
