package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_ASCENDING_EXPIRY_DATE;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_DESCENDING_PRIORITY;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_FIRST_CHARACTER;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_LEXICOGRAPHICAL_ORDER;

import seedu.simplykitchen.model.Model;
/**
 * Sorts food items in FoodInventory by description, by expiry date from oldest to newest,
 * then by priority from high to low.
 *
 * Sorting by description consists of first sorting lexicographically, then by the description's first characters.
 * (A possible valid ordering: 'a', 'aa', 'aaa', 'ai', 'A').
 */
public class SortDescCommand extends Command {

    public static final String COMMAND_WORD = "sortdesc";

    public static final String MESSAGE_SUCCESS = "Sorted by expiry date from oldest to newest";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFoodInventory(SORT_BY_DESCENDING_PRIORITY,
                SORT_BY_ASCENDING_EXPIRY_DATE,
                SORT_BY_LEXICOGRAPHICAL_ORDER,
                SORT_BY_FIRST_CHARACTER);
        model.commitFoodInventory();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
