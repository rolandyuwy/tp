package seedu.simplykitchen.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.model.food.Food;

/**
 * Panel containing the list of food items.
 */
public class ExpiredFoodListPanel extends UiPart<Region> {
    private static final String FXML = "ExpiredFoodListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpiredFoodListPanel.class);

    @javafx.fxml.FXML
    private ListView<Food> expiredListView;

    /**
     * Creates a {@code FoodListPanel} with the given {@code ObservableList}.
     */
    public ExpiredFoodListPanel(ObservableList<Food> foodList) {
        super(FXML);
        expiredListView.setItems(foodList);
        expiredListView.setCellFactory(listView -> new ExpiredListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Food} using a {@code FoodCard}.
     */
    class ExpiredListViewCell extends ListCell<Food> {
        @Override
        protected void updateItem(Food food, boolean empty) {
            super.updateItem(food, empty);

            if (empty || food == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpiredFoodListCard(food, getIndex() + 1).getRoot());
            }
        }
    }

}
