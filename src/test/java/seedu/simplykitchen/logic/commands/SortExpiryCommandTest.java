package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.SortExpiryCommand.MESSAGE_SUCCESS;
import static seedu.simplykitchen.logic.commands.SortExpiryCommand.SORT_EXPIRY_COMPARATORS;
import static seedu.simplykitchen.testutil.TypicalFood.ANCHOVIES;
import static seedu.simplykitchen.testutil.TypicalFood.BAGEL;
import static seedu.simplykitchen.testutil.TypicalFood.CARROT_CAKE;
import static seedu.simplykitchen.testutil.TypicalFood.CHEAP_EGGS;
import static seedu.simplykitchen.testutil.TypicalFood.DARK_CHOCOLATE;
import static seedu.simplykitchen.testutil.TypicalFood.EGGS;
import static seedu.simplykitchen.testutil.TypicalFood.FRENCH_FRIES;
import static seedu.simplykitchen.testutil.TypicalFood.GINGER;
import static seedu.simplykitchen.testutil.TypicalFood.WHITE_CHOCOLATE;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.food.Food;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortExpiryCommand.
 */
public class SortExpiryCommandTest {

    private Model model;
    private Model expectedModel;

    public void setUp(Food... foodsToAdd) {
        FoodInventory preSortedFoodInventory = getTypicalFoodInventory();
        FoodInventory expectedPreSortedFoodInventory = getTypicalFoodInventory();
        for (Food food : foodsToAdd) {
            preSortedFoodInventory.addFood(food);
            expectedPreSortedFoodInventory.addFood(food);
        }
        model = new ModelManager(preSortedFoodInventory, new UserPrefs());
        expectedModel = new ModelManager(expectedPreSortedFoodInventory, new UserPrefs());
    }

    @Test
    public void execute_sortByExpiryDifferentExpiry_success() {
        setUp();
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortExpiryCommand command = new SortExpiryCommand();

        expectedModel.sortFoodInventory(SORT_EXPIRY_COMPARATORS);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, FRENCH_FRIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, EGGS, GINGER), model.getFilteredFoodList());
    }

    @Test
    public void execute_sortByExpirySameExpiryDifferentPriority_success() {
        setUp(CHEAP_EGGS);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortExpiryCommand command = new SortExpiryCommand();

        expectedModel.sortFoodInventory(SORT_EXPIRY_COMPARATORS);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, FRENCH_FRIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, EGGS, CHEAP_EGGS, GINGER), model.getFilteredFoodList());
    }

    @Test
    public void execute_sortByExpirySameExpirySamePriorityDifferentDescription_success() {
        setUp(WHITE_CHOCOLATE);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortExpiryCommand command = new SortExpiryCommand();

        expectedModel.sortFoodInventory(SORT_EXPIRY_COMPARATORS);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, FRENCH_FRIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, WHITE_CHOCOLATE, EGGS, GINGER), model.getFilteredFoodList());
    }
}
