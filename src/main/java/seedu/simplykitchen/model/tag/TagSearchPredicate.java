package seedu.simplykitchen.model.tag;

import java.util.Set;
import java.util.function.Predicate;

import seedu.simplykitchen.commons.util.StringUtil;
import seedu.simplykitchen.model.food.Food;

/**
 * Tests that a {@code Food}'s {@code Description} matches any of the keywords given.
 */
public class TagSearchPredicate implements Predicate<Food> {
    private final Set<Tag> tags;

    public TagSearchPredicate(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Food food) {
        return tags.stream()
                .anyMatch(tag -> {
                    for (Tag tag : food.getTags()) {
                        if (StringUtil.containsWordIgnoreCase(tag.tagName, tag.tagName)) {
                            return true;
                        }
                    }
                    return false;
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagSearchPredicate // instanceof handles nulls
                && tags.equals(((TagSearchPredicate) other).tags)); // state check
    }

}
