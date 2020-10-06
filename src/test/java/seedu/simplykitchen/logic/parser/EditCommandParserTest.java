package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PRIORITY_DESC_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PRIORITY_DESC_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_FIRST_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_SECOND_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_THIRD_FOOD;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.EditCommand;
import seedu.simplykitchen.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.simplykitchen.model.food.Address;
import seedu.simplykitchen.model.food.Email;
import seedu.simplykitchen.model.food.Name;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.testutil.EditFoodDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS); // invalid priority
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid priority followed by valid email
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC + EMAIL_DESC_AMY, Priority.MESSAGE_CONSTRAINTS);

        // valid priority followed by invalid priority. The test case for invalid priority followed by valid priority
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PRIORITY_DESC_BOB + INVALID_PRIORITY_DESC,
                Priority.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Food} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                + VALID_PRIORITY_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FOOD;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPriority(VALID_PRIORITY_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC_BOB + EMAIL_DESC_AMY;

        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withPriority(VALID_PRIORITY_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_FOOD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_AMY;
        descriptor = new EditFoodDescriptorBuilder().withPriority(VALID_PRIORITY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditFoodDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditFoodDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditFoodDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {

        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PRIORITY_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PRIORITY_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withPriority(VALID_PRIORITY_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + INVALID_PRIORITY_DESC + PRIORITY_DESC_BOB;
        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withPriority(VALID_PRIORITY_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PRIORITY_DESC + ADDRESS_DESC_BOB
                + PRIORITY_DESC_BOB;
        descriptor = new EditFoodDescriptorBuilder().withPriority(VALID_PRIORITY_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_FOOD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
