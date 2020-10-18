package seedu.simplykitchen.model.tag;

import java.util.Set;
import java.util.function.Predicate;

import seedu.simplykitchen.commons.util.StringUtil;
import seedu.simplykitchen.model.food.Food;

/**
 * Tests that a {@code Food}'s {@code Description} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Food> {
    private final Set<Tag> keywords;

    public TagsContainsKeywordsPredicate(Set<Tag> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Food food) {
        return keywords.stream()
                .anyMatch(keyword -> {
                    for (Tag tag : food.getTags()) {
                        if (StringUtil.containsWordIgnoreCase(tag.tagName, keyword.tagName)) {
                            return true;
                        }
                    }
                    return false;
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
