package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Set;

import seedu.simplykitchen.logic.commands.FindCommand;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.ExpiryDateSearchPredicate;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.PrioritySearchPredicate;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_EXPIRY_DATE, PREFIX_TAG);

        DescriptionContainsKeywordsPredicate descriptionContainsKeywordsPredicate;
        PrioritySearchPredicate prioritySearchPredicate;
        ExpiryDateSearchPredicate expiryDateSearchPredicate;
        TagContainsKeywordsPredicate tagContainsKeywordsPredicate;

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
            String trimmedArgs = description.toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] descriptionKeywords = trimmedArgs.split("\\s+");
            descriptionContainsKeywordsPredicate =
                    new DescriptionContainsKeywordsPredicate(Arrays.asList(descriptionKeywords));
        } else {
            descriptionContainsKeywordsPredicate = null;
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            Priority priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            prioritySearchPredicate = new PrioritySearchPredicate(priority.value);
        } else {
            prioritySearchPredicate = null;
        }

        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            ExpiryDate expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
            expiryDateSearchPredicate = new ExpiryDateSearchPredicate(expiryDate.value);
        } else {
            expiryDateSearchPredicate = null;
        }

        if (argMultimap.getAllValues(PREFIX_TAG).size() != 0) {
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            tagContainsKeywordsPredicate = new TagContainsKeywordsPredicate(tagList);
        } else {
            tagContainsKeywordsPredicate = null;
        }

        return new FindCommand(descriptionContainsKeywordsPredicate, prioritySearchPredicate,
                expiryDateSearchPredicate, tagContainsKeywordsPredicate);
    }

}
