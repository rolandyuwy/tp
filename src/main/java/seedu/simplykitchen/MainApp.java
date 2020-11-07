package seedu.simplykitchen;

import static seedu.simplykitchen.model.util.ComparatorUtil.isSortingComparatorsDescriptionValid;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.simplykitchen.commons.core.Config;
import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.commons.core.Version;
import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.commons.util.ConfigUtil;
import seedu.simplykitchen.commons.util.StringUtil;
import seedu.simplykitchen.logic.Logic;
import seedu.simplykitchen.logic.LogicManager;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;
import seedu.simplykitchen.model.ReadOnlyUserPrefs;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.util.SampleDataUtil;
import seedu.simplykitchen.storage.FoodInventoryStorage;
import seedu.simplykitchen.storage.JsonFoodInventoryStorage;
import seedu.simplykitchen.storage.JsonUserPrefsStorage;
import seedu.simplykitchen.storage.Storage;
import seedu.simplykitchen.storage.StorageManager;
import seedu.simplykitchen.storage.UserPrefsStorage;
import seedu.simplykitchen.ui.Ui;
import seedu.simplykitchen.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {
    public static final Version VERSION = new Version(1, 4, 0, true);

    public static final String INVALID_USER_PREFS_SORTING_DESCRIPTION =
            "Sorting description not valid. Food list will be sorted by description.\n";
    private static final String INVALID_DATA_FORMAT = "Data file is not in the correct format. "
            + "Will be starting with an empty Food Inventory.\n"
            + "Please double check the data file and fix all incorrect formatting and restart the app.\n"
            + "If you add a new food item now, all previous data will be lost.\n";
    private static final String DATA_FILE_NOT_FOUND = "Data file not found. Will be starting with "
            + "a sample Food Inventory.\n";
    private static final String DATA_FILE_IO_ERROR = "Problem while reading from the file."
            + "Will be starting with an empty Food Inventory.\n";

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing SimplyKitchen ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FoodInventoryStorage foodInventoryStorage =
                new JsonFoodInventoryStorage(userPrefs.getFoodInventoryFilePath());
        storage = new StorageManager(foodInventoryStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s food inventory
     * and {@code userPrefs}. <br>
     * The data from the sample food inventory will be used instead if
     * {@code storage}'s food inventory is not found,
     * or an empty food inventory will be used instead if errors occur
     * when reading {@code storage}'s food inventory.
     * Default sorting is used if sorting comparators description in {@code userPrefs} is invalid.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyFoodInventory> simplyKitchenInventoryOptional;
        ReadOnlyFoodInventory initialData;
        ReadOnlyUserPrefs inputUserPrefs;
        try {
            if (!isSortingComparatorsDescriptionValid(userPrefs.getSortingComparatorsDescription())) {
                logger.info(INVALID_USER_PREFS_SORTING_DESCRIPTION);
            }
            simplyKitchenInventoryOptional = storage.readFoodInventory();
            initialData = simplyKitchenInventoryOptional.orElseGet(SampleDataUtil::getSampleFoodInventory);

            if (!simplyKitchenInventoryOptional.isPresent()) {
                logger.info(DATA_FILE_NOT_FOUND);
                return new ModelManager(initialData, userPrefs, true, DATA_FILE_NOT_FOUND);
            }
            return new ModelManager(initialData, userPrefs);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. "
                    + "Will be starting with an empty FoodInventory");
            initialData = new FoodInventory();
            return new ModelManager(initialData, userPrefs, true, INVALID_DATA_FORMAT);
        } catch (IOException e) {
            logger.warning(DATA_FILE_IO_ERROR);
            initialData = new FoodInventory();
            return new ModelManager(initialData, userPrefs, true, DATA_FILE_IO_ERROR);
        }

    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. "
                    + "Will be starting with an empty FoodInventory");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting SimplyKitchen " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping SimplyKitchen ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
