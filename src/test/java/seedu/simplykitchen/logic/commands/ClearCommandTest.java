package seedu.simplykitchen.logic.commands;

import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalSimplyKitchenInventory;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.SimplyKitchenInventory;
import seedu.simplykitchen.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptySimplyKitchenInventory_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySimplyKitchenInventory_success() {
        Model model = new ModelManager(getTypicalSimplyKitchenInventory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSimplyKitchenInventory(), new UserPrefs());
        expectedModel.setSimplyKitchenInventory(new SimplyKitchenInventory());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
