package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX;
import static seedu.simplykitchen.commons.util.StringUtil.isNonPositiveUnsignedInteger;

import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.DeleteCommand;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(generateParseExceptionMessage(args), pe);
        }
    }

    /**
     * Generate the error message for the invalid index number.
     */
    private String generateParseExceptionMessage(String args) {
        if (isNonPositiveUnsignedInteger(args.trim())) {
            return MESSAGE_INVALID_FOOD_DISPLAYED_INDEX;
        } else {
            return String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
        }
    }

}
