package seedu.simplykitchen.logic.commands;

import seedu.simplykitchen.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_ASCENDING_DESCRIPTION;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_ASCENDING_EXPIRY_DATE;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_DESCENDING_PRIORITY;

/**
 * Sorts food items in FoodInventory lexicographically, by expiry date from oldest to newest,
 * then by priority from high to low.
 */
public class SortDescCommand extends Command {

    public static final String COMMAND_WORD = "sortdesc";

    public static final String MESSAGE_SUCCESS = "Sorted by expiry date from oldest to newest";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFoodInventory(SORT_BY_DESCENDING_PRIORITY,
                SORT_BY_ASCENDING_EXPIRY_DATE,
                SORT_BY_ASCENDING_DESCRIPTION);
        model.commitFoodInventory();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
