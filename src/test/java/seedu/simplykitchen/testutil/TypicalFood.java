package seedu.simplykitchen.testutil;

import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
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
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Food BAGEL = new FoodBuilder().withDescription("Bagel")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Food CARROT_CAKE = new FoodBuilder().withDescription("Carrot Cake").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Food DARK_CHOCOLATE = new FoodBuilder().withDescription("Dark Chocolate")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withAddress("10th street").withTags("friends").build();
    public static final Food EGGS = new FoodBuilder().withDescription("Eggs").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Food FRENCH_FRIES = new FoodBuilder().withDescription("French Fries").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Food GINGER = new FoodBuilder().withDescription("Ginger").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Food HUMMUS = new FoodBuilder().withDescription("Hummus").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Food ICEBERG_LETTUCE = new FoodBuilder().withDescription("Iceberg Lettuce")
            .withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Food's details found in {@code CommandTestUtil}
    public static final Food APPLE_PIE = new FoodBuilder().withDescription(VALID_DESCRIPTION_APPLE_PIE)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Food BREAD = new FoodBuilder().withDescription(VALID_DESCRIPTION_BREAD)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
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
