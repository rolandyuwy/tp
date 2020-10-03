package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.SimplyKitchenInventory;

/**
 * Clears the SimplyKitchen inventory.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "SimplyKitchen inventory has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSimplyKitchenInventory(new SimplyKitchenInventory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
