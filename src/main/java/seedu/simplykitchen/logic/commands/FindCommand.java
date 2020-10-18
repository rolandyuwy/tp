package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.simplykitchen.commons.core.Messages;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.PrioritySearchPredicate;

/**
 * Finds and lists all food items in Food inventory whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Usage : " + COMMAND_WORD + " KEYWORD [MORE_KEYWORDS]...\n  "
            + "Example: " + COMMAND_WORD + " apple bread carrot";

    private final DescriptionContainsKeywordsPredicate descriptionPredicate;
    private final PrioritySearchPredicate priorityPredicate;

    public FindCommand(DescriptionContainsKeywordsPredicate descriptionPredicate,
                       PrioritySearchPredicate priorityPredicate) {
        this.descriptionPredicate = descriptionPredicate;
        this.priorityPredicate = priorityPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFoodList(descriptionPredicate.and(priorityPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_FOODS_LISTED_OVERVIEW, model.getFilteredFoodList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && descriptionPredicate.equals(((FindCommand) other).descriptionPredicate)
                && priorityPredicate.equals(((FindCommand) other).priorityPredicate)); // state check
    }
}
