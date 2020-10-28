package seedu.simplykitchen.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.simplykitchen.model.food.Food;

/**
 * An UI component that displays information of a {@code Food} which is expiring soon.
 */
public class ExpiringFoodCard extends UiPart<Region> {

    private static final String FXML = "ExpiringFoodCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">
     * The issue on FoodInventory level 4</a>
     */

    public final Food food;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label expiryDate;

    /**
     * Creates a {@code FoodCard} with the given {@code Food} and index to display.
     */
    public ExpiringFoodCard(Food food) {
        super(FXML);
        this.food = food;
        description.setText(food.getDescription().fullDescription);
        expiryDate.setText(food.getExpiryDate().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpiringFoodCard)) {
            return false;
        }

        // state check
        ExpiringFoodCard card = (ExpiringFoodCard) other;
        return food.equals(card.food);
    }
}
