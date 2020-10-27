package seedu.simplykitchen.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.food.Food;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.SortExpiryCommand.MESSAGE_SUCCESS;
import static seedu.simplykitchen.model.util.ComparatorUtil.*;
import static seedu.simplykitchen.testutil.TypicalFood.*;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortExpiryCommand.
 */
public class SortExpiryCommandTest {

    private Model model;
    private Model expectedModel;

    public void setUp(Food... foodsToAdd) {
        FoodInventory unsortedFoodInventory = getTypicalFoodInventory();
        for (Food food : foodsToAdd) {
            unsortedFoodInventory.addFood(food);
        }
        model = new ModelManager(unsortedFoodInventory, new UserPrefs());
        expectedModel = new ModelManager(model.getFoodInventory(), new UserPrefs());
    }

    @Test
    public void execute_sortByExpiry_differentExpiry_success() {
        setUp();
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortExpiryCommand command = new SortExpiryCommand();
        expectedModel.sortFoodInventory(SORT_BY_LEXICOGRAPHICAL_ORDER,
                SORT_BY_FIRST_CHARACTER,
                SORT_BY_DESCENDING_PRIORITY,
                SORT_BY_ASCENDING_EXPIRY_DATE);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, FRENCH_FRIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, EGGS, GINGER), model.getFilteredFoodList());
    }

    @Test
    public void execute_sortByPriority_sameExpiryDifferentPriority_success() {
        setUp(CHEAP_EGGS);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortExpiryCommand command = new SortExpiryCommand();
        expectedModel.sortFoodInventory(SORT_BY_LEXICOGRAPHICAL_ORDER,
                SORT_BY_FIRST_CHARACTER,
                SORT_BY_DESCENDING_PRIORITY,
                SORT_BY_ASCENDING_EXPIRY_DATE);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, FRENCH_FRIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, EGGS, CHEAP_EGGS, GINGER), model.getFilteredFoodList());
    }

    @Test
    public void execute_sortByPriority_sameExpirySamePriorityDifferentDescription_success() {
        setUp(WHITE_CHOCOLATE);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortExpiryCommand command = new SortExpiryCommand();
        expectedModel.sortFoodInventory(SORT_BY_LEXICOGRAPHICAL_ORDER,
                SORT_BY_FIRST_CHARACTER,
                SORT_BY_DESCENDING_PRIORITY,
                SORT_BY_ASCENDING_EXPIRY_DATE);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, FRENCH_FRIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, WHITE_CHOCOLATE, EGGS, GINGER), model.getFilteredFoodList());
    }
}
