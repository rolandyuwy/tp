package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.model.Model.PREDICATE_SHOW_ALL_FOODS;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_ASCENDING_DESCRIPTION;

import seedu.simplykitchen.model.Model;

/**
 * Lists all food items in the Food inventory to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all food items";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        model.updateSortedFoodList(SORT_BY_ASCENDING_DESCRIPTION);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
