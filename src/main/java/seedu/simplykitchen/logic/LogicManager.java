package seedu.simplykitchen.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.logic.commands.Command;
import seedu.simplykitchen.logic.commands.CommandResult;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.logic.parser.SimplyKitchenParser;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ReadOnlySimplyKitchenInventory;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final SimplyKitchenParser simplyKitchenParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        simplyKitchenParser = new SimplyKitchenParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = simplyKitchenParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveSimplyKitchenInventory(model.getSimplyKitchenInventory());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlySimplyKitchenInventory getSimplyKitchenInventory() {
        return model.getSimplyKitchenInventory();
    }

    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return model.getFilteredFoodList();
    }

    @Override
    public Path getSimplyKitchenInventoryFilePath() {
        return model.getSimplyKitchenInventoryFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
