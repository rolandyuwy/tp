package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.testutil.EditFoodDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_DESCRIPTION_APPLE_PIE = "Apple Pie";
    public static final String VALID_DESCRIPTION_BREAD = "Bread";
    public static final String VALID_PRIORITY_APPLE_PIE = "high";
    public static final String VALID_PRIORITY_BREAD = "medium";
    public static final String VALID_EXPIRYDATE_APPLE_PIE = "1-1-2021";
    public static final String VALID_EXPIRYDATE_BREAD = "31-12-2021";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String DESCRIPTION_DESC_APPLE_PIE = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_APPLE_PIE;
    public static final String DESCRIPTION_DESC_BREAD = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BREAD;
    public static final String PRIORITY_DESC_APPLE_PIE = " " + PREFIX_PRIORITY + VALID_PRIORITY_APPLE_PIE;
    public static final String PRIORITY_DESC_BREAD = " " + PREFIX_PRIORITY + VALID_PRIORITY_BREAD;
    public static final String EXPIRYDATE_DESC_APPLE_PIE = " " + PREFIX_EXPIRYDATE + VALID_EXPIRYDATE_APPLE_PIE;
    public static final String EXPIRYDATE_DESC_BREAD = " " + PREFIX_EXPIRYDATE + VALID_EXPIRYDATE_BREAD;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION
            + "D&nut"; // '&' not allowed in descriptions
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "lower"; // 'lower' not valid priority
    public static final String INVALID_EXPIRYDATE_DESC = " " + PREFIX_EXPIRYDATE + "1-13-2021"; // invalid month
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFoodDescriptor DESC_APPLE_PIE;
    public static final EditCommand.EditFoodDescriptor DESC_BREAD;

    static {
        DESC_APPLE_PIE = new EditFoodDescriptorBuilder().withDescription(VALID_DESCRIPTION_APPLE_PIE)
                .withPriority(VALID_PRIORITY_APPLE_PIE).withExpiryDate(VALID_EXPIRYDATE_APPLE_PIE)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BREAD = new EditFoodDescriptorBuilder().withDescription(VALID_DESCRIPTION_BREAD)
                .withPriority(VALID_PRIORITY_BREAD).withExpiryDate(VALID_EXPIRYDATE_BREAD)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the SimplyKitchen inventory, filtered food list and selected food in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FoodInventory expectedFoodInventory =
                new FoodInventory(actualModel.getFoodInventory());
        List<Food> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFoodList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFoodInventory, actualModel.getFoodInventory());
        assertEquals(expectedFilteredList, actualModel.getFilteredFoodList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the food at the given {@code targetIndex} in the
     * {@code model}'s food inventory.
     */
    public static void showFoodAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFoodList().size());

        Food food = model.getFilteredFoodList().get(targetIndex.getZeroBased());
        final String[] splitDescription = food.getDescription().fullDescription.split("\\s+");
        model.updateFilteredFoodList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitDescription[0])));

        assertEquals(1, model.getFilteredFoodList().size());
    }

}
