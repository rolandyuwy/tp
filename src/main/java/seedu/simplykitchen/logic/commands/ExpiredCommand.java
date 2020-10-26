package seedu.simplykitchen.logic.commands;

import seedu.simplykitchen.model.Model;

/**
 * Format command message for every expired command for display.
 */
public class ExpiredCommand extends Command {

    public static final String COMMAND_WORD = "expired";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all expired food items.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened expired food items window.";
    public static final String NO_EXPIRED_ITEMS = "There is currently no expired food item.";

    @Override
    public CommandResult execute(Model model) {
        if (model.getFilteredExpiredFoodList().size() > 0) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        } else {
            return new CommandResult(NO_EXPIRED_ITEMS, true, false);
        }
    }
}
