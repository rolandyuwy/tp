package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.simplykitchen.model.Model.PREDICATE_SHOW_ALL_FOODS;

import java.util.List;
import java.util.Set;

import seedu.simplykitchen.commons.core.Messages;
import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.Quantity;
import seedu.simplykitchen.model.tag.Tag;

/**
 * Changes the quantity of an existing food item in the food inventory.
 */
public class ChangeQuantityCommand extends Command {

    public static final String COMMAND_WORD = "changeqty";

    public static final String MESSAGE_USAGE = " Usage: " + COMMAND_WORD + " INDEX "
            + PREFIX_AMOUNT + "AMOUNT\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AMOUNT + "+1";

    public static final String MESSAGE_SUCCESS = "The following food item has its quantity changed:\n  %1$s";
    public static final String MESSAGE_NEGATIVE_QUANTITY =
            "The quantity of the food item cannot be updated to a value less than zero.\n";
    public static final String MESSAGE_ZERO_QUANTITY =
            "The quantity of the food item cannot be updated to 0. Delete the food item instead.\n";

    private final Index index;
    private final double amount;

    /**
     * @param index of the food item in the filtered food list to edit
     * @param amount of the food item to change by
     */
    public ChangeQuantityCommand(Index index, double amount) {
        requireNonNull(index);
        assert amount != 0;

        this.index = index;
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToEdit = lastShownList.get(index.getZeroBased());
        Food editedFood = updateQuantity(foodToEdit, amount);

        model.setFood(foodToEdit, editedFood);
        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        model.commitFoodInventory();
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedFood));
    }

    private Food updateQuantity(Food originalFood, double amount) throws CommandException {
        Quantity oldQuantity = originalFood.getQuantity();
        String quantityUnit = oldQuantity.unit;
        double newQuantityValue = oldQuantity.value + amount;
        if (newQuantityValue < 0) {
            throw new CommandException(MESSAGE_NEGATIVE_QUANTITY);
        }
        if (newQuantityValue == 0) {
            throw new CommandException(MESSAGE_ZERO_QUANTITY);
        }
        assert newQuantityValue != 0;

        Description description = originalFood.getDescription();
        Priority priority = originalFood.getPriority();
        ExpiryDate expiryDate = originalFood.getExpiryDate();
        Quantity newQuantity = Quantity.of(newQuantityValue, quantityUnit);
        Set<Tag> tags = originalFood.getTags();

        return new Food(description, priority, expiryDate, newQuantity, tags);
    }
}
