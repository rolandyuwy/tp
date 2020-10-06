package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.Food;

/**
 * Adds a food item to the Food inventory.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a food to the food inventory. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PRIORITY + "PRIORITY "
            + PREFIX_EXPIRYDATE + "EXPIRY DATE "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Canned Tuna "
            + PREFIX_PRIORITY + "HIGH "
            + PREFIX_EXPIRYDATE + "1-1-2020 "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New food item added: %1$s";
    public static final String MESSAGE_DUPLICATE_FOOD = "This food item already exists in the food inventory";

    private final Food toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Food}
     */
    public AddCommand(Food food) {
        requireNonNull(food);
        toAdd = food;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFood(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOOD);
        }

        model.addFood(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
