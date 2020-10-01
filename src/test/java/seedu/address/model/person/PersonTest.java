package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ANCHOVIES;
import static seedu.address.testutil.TypicalPersons.BREAD;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ANCHOVIES.isSamePerson(ANCHOVIES));

        // null -> returns false
        assertFalse(ANCHOVIES.isSamePerson(null));

        // different phone and email -> returns false
        Person editedAnchovies = new PersonBuilder(ANCHOVIES).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ANCHOVIES.isSamePerson(editedAnchovies));

        // different description -> returns false
        editedAnchovies = new PersonBuilder(ANCHOVIES).withDescription(VALID_DESCRIPTION_BREAD).build();
        assertFalse(ANCHOVIES.isSamePerson(editedAnchovies));

        // same description, same phone, different attributes -> returns true
        editedAnchovies = new PersonBuilder(ANCHOVIES).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ANCHOVIES.isSamePerson(editedAnchovies));

        // same description, same email, different attributes -> returns true
        editedAnchovies = new PersonBuilder(ANCHOVIES).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ANCHOVIES.isSamePerson(editedAnchovies));

        // same description, same phone, same email, different attributes -> returns true
        editedAnchovies = new PersonBuilder(ANCHOVIES).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ANCHOVIES.isSamePerson(editedAnchovies));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person anchoviesCopy = new PersonBuilder(ANCHOVIES).build();
        assertTrue(ANCHOVIES.equals(anchoviesCopy));

        // same object -> returns true
        assertTrue(ANCHOVIES.equals(ANCHOVIES));

        // null -> returns false
        assertFalse(ANCHOVIES.equals(null));

        // different type -> returns false
        assertFalse(ANCHOVIES.equals(5));

        // different person -> returns false
        assertFalse(ANCHOVIES.equals(BREAD));

        // different description -> returns false
        Person editedAlice = new PersonBuilder(ANCHOVIES).withDescription(VALID_DESCRIPTION_BREAD).build();
        assertFalse(ANCHOVIES.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ANCHOVIES).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ANCHOVIES.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ANCHOVIES).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ANCHOVIES.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ANCHOVIES).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ANCHOVIES.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ANCHOVIES).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ANCHOVIES.equals(editedAlice));
    }
}
