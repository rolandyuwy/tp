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
import static seedu.simplykitchen.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PRIORITY_DESC_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PRIORITY_DESC_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.simplykitchen.testutil.TypicalFood.AMY;
import static seedu.simplykitchen.testutil.TypicalFood.BOB;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.logic.commands.AddCommand;
import seedu.simplykitchen.model.food.Address;
import seedu.simplykitchen.model.food.Email;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Name;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.testutil.FoodBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Food expectedFood = new FoodBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedFood));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedFood));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PRIORITY_DESC_AMY + PRIORITY_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedFood));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedFood));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedFood));

        // multiple tags - all accepted
        Food expectedFoodMultipleTags = new FoodBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        assertParseSuccess(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedFoodMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Food expectedFood = new FoodBuilder(AMY).withPriority("low").withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedFood));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PRIORITY_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PRIORITY_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PRIORITY_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Priority.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PRIORITY_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PRIORITY_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
