package seedu.simplykitchen.testutil;

import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.simplykitchen.logic.commands.AddCommand;
import seedu.simplykitchen.logic.commands.EditCommand;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.tag.Tag;

/**
 * A utility class for Food.
 */
public class FoodUtil {

    /**
     * Returns an add command string for adding the {@code food}.
     */
    public static String getAddCommand(Food food) {
        return AddCommand.COMMAND_WORD + " " + getFoodDetails(food);
    }

    /**
     * Returns the part of command string for the given {@code food}'s details.
     */
    public static String getFoodDetails(Food food) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + food.getName().fullName + " ");
        sb.append(PREFIX_PRIORITY + food.getPriority().toString() + " ");
        sb.append(PREFIX_EXPIRYDATE + food.getExpiryDate().value + " ");
        sb.append(PREFIX_ADDRESS + food.getAddress().value + " ");
        food.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFoodDescriptor}'s details.
     */
    public static String getEditFoodDescriptorDetails(EditCommand.EditFoodDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPriority().ifPresent(priority -> sb.append(PREFIX_PRIORITY)
                .append(priority.toString()).append(" "));
        descriptor.getExpiryDate().ifPresent(expiryDate -> sb.append(PREFIX_EXPIRYDATE).append(expiryDate.value)
                .append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
