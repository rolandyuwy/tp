package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.ALICE;
import static seedu.simplykitchen.testutil.TypicalFood.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.food.exceptions.DuplicateFoodException;
import seedu.simplykitchen.model.food.exceptions.FoodNotFoundException;
import seedu.simplykitchen.testutil.FoodBuilder;

public class UniqueFoodListTest {

    private final UniqueFoodList uniqueFoodList = new UniqueFoodList();

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.contains(null));
    }

    @Test
    public void contains_foodNotInList_returnsFalse() {
        assertFalse(uniqueFoodList.contains(ALICE));
    }

    @Test
    public void contains_foodInList_returnsTrue() {
        uniqueFoodList.add(ALICE);
        assertTrue(uniqueFoodList.contains(ALICE));
    }

    @Test
    public void contains_foodWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFoodList.add(ALICE);
        Food editedAlice = new FoodBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueFoodList.contains(editedAlice));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.add(null));
    }

    @Test
    public void add_duplicateFood_throwsDuplicateFoodException() {
        uniqueFoodList.add(ALICE);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.add(ALICE));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(null, ALICE));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(ALICE, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> uniqueFoodList.setFood(ALICE, ALICE));
    }

    @Test
    public void setFood_editedFoodIsSameFood_success() {
        uniqueFoodList.add(ALICE);
        uniqueFoodList.setFood(ALICE, ALICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(ALICE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasSameIdentity_success() {
        uniqueFoodList.add(ALICE);
        Food editedAlice = new FoodBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueFoodList.setFood(ALICE, editedAlice);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(editedAlice);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasDifferentIdentity_success() {
        uniqueFoodList.add(ALICE);
        uniqueFoodList.setFood(ALICE, BOB);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(BOB);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasNonUniqueIdentity_throwsDuplicateFoodException() {
        uniqueFoodList.add(ALICE);
        uniqueFoodList.add(BOB);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.setFood(ALICE, BOB));
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.remove(null));
    }

    @Test
    public void remove_foodDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> uniqueFoodList.remove(ALICE));
    }

    @Test
    public void remove_existingFood_removesFood() {
        uniqueFoodList.add(ALICE);
        uniqueFoodList.remove(ALICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullUniqueFoodList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFoods((UniqueFoodList) null));
    }

    @Test
    public void setFoods_uniqueFoodList_replacesOwnListWithProvidedUniqueFoodList() {
        uniqueFoodList.add(ALICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(BOB);
        uniqueFoodList.setFoods(expectedUniqueFoodList);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFoods((List<Food>) null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        uniqueFoodList.add(ALICE);
        List<Food> foodList = Collections.singletonList(BOB);
        uniqueFoodList.setFoods(foodList);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(BOB);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_listWithDuplicateFoods_throwsDuplicateFoodException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.setFoods(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFoodList.asUnmodifiableObservableList().remove(0));
    }
}
