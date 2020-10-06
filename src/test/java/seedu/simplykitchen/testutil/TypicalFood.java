package seedu.simplykitchen.testutil;

import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.food.Food;

/**
 * A utility class containing a list of {@code Food} objects to be used in tests.
 */
public class TypicalFood {

    public static final Food ANCHOVIES = new FoodBuilder().withDescription("Anchovies")
            .withAddress("123, Jurong West Ave 6, #08-111").withExpiryDate("1-1-2021")
            .withPriority("low")
            .withTags("friends").build();
    public static final Food BAGEL = new FoodBuilder().withDescription("Bagel")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withExpiryDate("31-1-2021").withPriority("low")
            .withTags("owesMoney", "friends").build();
    public static final Food CARROT_CAKE = new FoodBuilder().withDescription("Carrot Cake").withPriority("low")
            .withExpiryDate("1-1-2020").withAddress("wall street").build();
    public static final Food DARK_CHOCOLATE = new FoodBuilder().withDescription("Dark Chocolate")
            .withPriority("high").withExpiryDate("1-12-2020")
            .withAddress("10th street").withTags("friends").build();
    public static final Food EGGS = new FoodBuilder().withDescription("Eggs").withPriority("high")
            .withExpiryDate("31-1-2020").withAddress("michegan ave").build();
    public static final Food FRENCH_FRIES = new FoodBuilder().withDescription("French Fries").withPriority("low")
            .withExpiryDate("31-12-2020").withAddress("little tokyo").build();
    public static final Food GINGER = new FoodBuilder().withDescription("Ginger").withPriority("low")
            .withExpiryDate("1-10-2020").withAddress("4th street").build();

    // Manually added
    public static final Food HUMMUS = new FoodBuilder().withDescription("Hummus").withPriority("low")
            .withExpiryDate("12-12-2020").withAddress("little india").build();
    public static final Food ICEBERG_LETTUCE = new FoodBuilder().withDescription("Iceberg Lettuce").withPriority("high")
            .withExpiryDate("12-12-2021").withAddress("chicago ave").build();

    // Manually added - Food's details found in {@code CommandTestUtil}
    public static final Food APPLE_PIE = new FoodBuilder().withDescription(VALID_DESCRIPTION_APPLE_PIE)
            .withPriority(VALID_PRIORITY_APPLE_PIE).withExpiryDate(VALID_EXPIRYDATE_APPLE_PIE)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Food BREAD = new FoodBuilder().withDescription(VALID_DESCRIPTION_BREAD)
            .withPriority(VALID_PRIORITY_BREAD).withExpiryDate(VALID_EXPIRYDATE_BREAD).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_LETTUCE = "Lettuce"; // A keyword that matches LETTUCE

    private TypicalFood() {
    } // prevents instantiation

    /**
     * Returns an {@code FoodInventory} with all the typical Foods.
     */
    public static FoodInventory getTypicalFoodInventory() {
        FoodInventory ab = new FoodInventory();
        for (Food food : getTypicalFood()) {
            ab.addFood(food);
        }
        return ab;
    }

    public static List<Food> getTypicalFood() {
        return new ArrayList<>(Arrays.asList(ANCHOVIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, EGGS, FRENCH_FRIES, GINGER));
    }
}
