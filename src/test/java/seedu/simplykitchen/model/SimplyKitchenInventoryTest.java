package seedu.simplykitchen.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.ALICE;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalSimplyKitchenInventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.exceptions.DuplicateFoodException;
import seedu.simplykitchen.testutil.FoodBuilder;

public class SimplyKitchenInventoryTest {

    private final SimplyKitchenInventory simplyKitchenInventory = new SimplyKitchenInventory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), simplyKitchenInventory.getFoods());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> simplyKitchenInventory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySimplyKitchenInventory_replacesData() {
        SimplyKitchenInventory newData = getTypicalSimplyKitchenInventory();
        simplyKitchenInventory.resetData(newData);
        assertEquals(newData, simplyKitchenInventory);
    }

    @Test
    public void resetData_withDuplicateFoods_throwsDuplicateFoodException() {
        // Two foods with the same identity fields
        Food editedAlice = new FoodBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Food> newFoods = Arrays.asList(ALICE, editedAlice);
        SimplyKitchenInventoryStub newData = new SimplyKitchenInventoryStub(newFoods);

        assertThrows(DuplicateFoodException.class, () -> simplyKitchenInventory.resetData(newData));
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> simplyKitchenInventory.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInSimplyKitchenInventory_returnsFalse() {
        assertFalse(simplyKitchenInventory.hasFood(ALICE));
    }

    @Test
    public void hasFood_foodInSimplyKitchenInventory_returnsTrue() {
        simplyKitchenInventory.addFood(ALICE);
        assertTrue(simplyKitchenInventory.hasFood(ALICE));
    }

    @Test
    public void hasFood_foodWithSameIdentityFieldsInSimplyKitchenInventory_returnsTrue() {
        simplyKitchenInventory.addFood(ALICE);
        Food editedAlice = new FoodBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(simplyKitchenInventory.hasFood(editedAlice));
    }

    @Test
    public void getFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> simplyKitchenInventory.getFoods().remove(0));
    }

    /**
     * A stub ReadOnlySimplyKitchenInventory whose foods list can violate interface constraints.
     */
    private static class SimplyKitchenInventoryStub implements ReadOnlySimplyKitchenInventory {
        private final ObservableList<Food> foods = FXCollections.observableArrayList();

        SimplyKitchenInventoryStub(Collection<Food> foods) {
            this.foods.setAll(foods);
        }

        @Override
        public ObservableList<Food> getFoods() {
            return foods;
        }
    }

}
