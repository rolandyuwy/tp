package seedu.simplykitchen.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.simplykitchen.model.food.Food;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Food} which is expiring soon.
 */
public class ExpiredFoodListCard extends UiPart<Region> {

    private static final String FXML = "ExpiredFoodListCard.fxml";

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
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FoodCard} with the given {@code Food} and index to display.
     */
    public ExpiredFoodListCard(Food food) {
        super(FXML);
        this.food = food;
        description.setText(food.getDescription().fullDescription);
        expiryDate.setText(food.getExpiryDate().value);
        food.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpiredFoodListCard)) {
            return false;
        }

        // state check
        ExpiredFoodListCard card = (ExpiredFoodListCard) other;
        return food.equals(card.food);
    }
}
