package seedu.simplykitchen.testutil;

import seedu.simplykitchen.model.SimplyKitchenInventory;
import seedu.simplykitchen.model.food.Food;

/**
 * A utility class to help with building SimplyKitchenInventory objects.
 * Example usage: <br>
 *     {@code SimplyKitchenInventory ab = new SimplyKitchenInventoryBuilder().withFood("John", "Doe").build();}
 */
public class SimplyKitchenInventoryBuilder {

    private SimplyKitchenInventory simplyKitchenInventory;

    public SimplyKitchenInventoryBuilder() {
        simplyKitchenInventory = new SimplyKitchenInventory();
    }

    public SimplyKitchenInventoryBuilder(SimplyKitchenInventory simplyKitchenInventory) {
        this.simplyKitchenInventory = simplyKitchenInventory;
    }

    /**
     * Adds a new {@code Food} to the {@code SimplyKitchenInventory} that we are building.
     */
    public SimplyKitchenInventoryBuilder withFood(Food food) {
        simplyKitchenInventory.addFood(food);
        return this;
    }

    public SimplyKitchenInventory build() {
        return simplyKitchenInventory;
    }
}
