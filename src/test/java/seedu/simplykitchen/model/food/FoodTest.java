package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.ALICE;
import static seedu.simplykitchen.testutil.TypicalFood.BOB;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.testutil.FoodBuilder;

public class FoodTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Food food = new FoodBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> food.getTags().remove(0));
    }

    @Test
    public void isSameFood() {
        // same object -> returns true
        assertTrue(ALICE.isSameFood(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameFood(null));

        // different priority and email -> returns false
        Food editedAlice = new FoodBuilder(ALICE).withPriority(VALID_PRIORITY_BOB)
                .withExpiryDate(VALID_EXPIRYDATE_BOB).build();
        assertFalse(ALICE.isSameFood(editedAlice));

        // different name -> returns false
        editedAlice = new FoodBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameFood(editedAlice));

        // same name, same priority, different attributes -> returns true
        editedAlice = new FoodBuilder(ALICE).withExpiryDate(VALID_EXPIRYDATE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameFood(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new FoodBuilder(ALICE).withPriority(VALID_PRIORITY_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameFood(editedAlice));

        // same name, same priority, same email, different attributes -> returns true
        editedAlice = new FoodBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameFood(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Food aliceCopy = new FoodBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different food -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Food editedAlice = new FoodBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different priority -> returns false
        editedAlice = new FoodBuilder(ALICE).withPriority(VALID_PRIORITY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new FoodBuilder(ALICE).withExpiryDate(VALID_EXPIRYDATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new FoodBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new FoodBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
