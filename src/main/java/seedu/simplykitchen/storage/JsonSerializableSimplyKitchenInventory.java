package seedu.simplykitchen.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.simplykitchen.commons.exceptions.IllegalValueException;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;
import seedu.simplykitchen.model.SimplyKitchenInventory;
import seedu.simplykitchen.model.food.Food;

/**
 * An Immutable SimplyKitchenInventory that is serializable to JSON format.
 */
@JsonRootName(value = "simplyKitchen")
class JsonSerializableSimplyKitchenInventory {

    public static final String MESSAGE_DUPLICATE_FOOD = "Food list contains duplicate food(s).";

    private final List<JsonAdaptedFood> foods = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSimplyKitchenInventory} with the given food items.
     */
    @JsonCreator
    public JsonSerializableSimplyKitchenInventory(@JsonProperty("foods") List<JsonAdaptedFood> foods) {
        this.foods.addAll(foods);
    }

    /**
     * Converts a given {@code ReadOnlySimplyKitchenInventory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSimplyKitchenInventory}.
     */
    public JsonSerializableSimplyKitchenInventory(ReadOnlySimplyKitchenInventory source) {
        foods.addAll(source.getFoods().stream().map(JsonAdaptedFood::new).collect(Collectors.toList()));
    }

    /**
     * Converts this SimplyKitchen inventory into the model's {@code SimplyKitchenInventory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SimplyKitchenInventory toModelType() throws IllegalValueException {
        SimplyKitchenInventory simplyKitchenInventory = new SimplyKitchenInventory();
        for (JsonAdaptedFood jsonAdaptedFood : foods) {
            Food food = jsonAdaptedFood.toModelType();
            if (simplyKitchenInventory.hasFood(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FOOD);
            }
            simplyKitchenInventory.addFood(food);
        }
        return simplyKitchenInventory;
    }

}
