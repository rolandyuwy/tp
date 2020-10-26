package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_ASCENDING_DESCRIPTION;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_ASCENDING_EXPIRY_DATE;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_DESCENDING_PRIORITY;

import seedu.simplykitchen.model.Model;


/**
 * Sorts food items in Food inventory by expiry date from oldest to newest,
 * then by priority from high to low, then lexicographically
 */
public class SortExpiryCommand extends Command {

    public static final String COMMAND_WORD = "sortexpiry";

    public static final String MESSAGE_SUCCESS = "Sorted by expiry date from oldest to newest";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFoodInventory(SORT_BY_ASCENDING_DESCRIPTION,
                SORT_BY_DESCENDING_PRIORITY,
                SORT_BY_ASCENDING_EXPIRY_DATE);
        model.commitFoodInventory();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
