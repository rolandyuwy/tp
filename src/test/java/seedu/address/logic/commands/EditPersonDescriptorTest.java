package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPLE_PIE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BREAD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_APPLE_PIE);
        assertTrue(DESC_APPLE_PIE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPLE_PIE.equals(DESC_APPLE_PIE));

        // null -> returns false
        assertFalse(DESC_APPLE_PIE.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPLE_PIE.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPLE_PIE.equals(DESC_BREAD));

        // different description -> returns false
        EditPersonDescriptor editedApplePie = new EditPersonDescriptorBuilder(DESC_APPLE_PIE)
                .withDescription(VALID_DESCRIPTION_BREAD).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));

        // different phone -> returns false
        editedApplePie = new EditPersonDescriptorBuilder(DESC_APPLE_PIE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));

        // different email -> returns false
        editedApplePie = new EditPersonDescriptorBuilder(DESC_APPLE_PIE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));

        // different address -> returns false
        editedApplePie = new EditPersonDescriptorBuilder(DESC_APPLE_PIE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));

        // different tags -> returns false
        editedApplePie = new EditPersonDescriptorBuilder(DESC_APPLE_PIE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));
    }
}
