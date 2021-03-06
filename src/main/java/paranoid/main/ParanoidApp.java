package paranoid.main;

import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import paranoid.model.settings.SettingsManager;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.layoutmanager.MainStage;

/**
 * PARANOID MAIN.
 * Stage creation.
 *
 */
public class ParanoidApp extends Application {

    /**
     * cross-platform way to find the user's home folder.
     */
    public static final String HOME = System.getProperty("user.home");

    /**
     * identifies how the operating system separates files.
     */
    public static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * the destination of the game folder.
     */
    public static final String MAIN_FOLDER = HOME + SEPARATOR + ".paranoid";

    /**
     * the folder to save the levels.
     */
    public static final String LEVEL_FOLDER = MAIN_FOLDER + SEPARATOR + "levels";

    /**
     * the folder to save current selected options.
     */
    public static final String OPTIONS = MAIN_FOLDER + SEPARATOR + "options";

    /**
     * path to the file where user information is stored.
     */
    public static final String USER = MAIN_FOLDER + SEPARATOR + "user";

    /**
     * the folder to save all users score options.
     */
    public static final String SCORE_FOLDER = MAIN_FOLDER + SEPARATOR + "scores";

    /**
     * the file to save the top story scores.
     */
    public static final String SCORE_STORY_PATH = SCORE_FOLDER + SEPARATOR + "story";

    /**
     * the default name of the story score.
     */
    public static final String SCORE_STORY_NAME = "story";
    /**
     * the file to save the top story scores.
     */
    public static final String SCORE_CUSTOM_PATH = SCORE_FOLDER + SEPARATOR + "custom";

    /**
     * maximum number of element in the score.
     */
    public static final int SCORE_MAX_ELEM = 10;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        new MainStage();

    }

    /**
     * initializes the game.
     * @param args command input
     */
    public static void main(final String[] args) {
        initSoftware();
        //unlockStoryLevel();
        launch();
    }

    /**
     * 
     * if not present, create the folder to keep the game files.
     */
    public static void initSoftware() {
        final SettingsBuilder settingsBuilder = new SettingsBuilder();
        final File mainFolder = new File(ParanoidApp.MAIN_FOLDER);
        final File levelFolder = new File(ParanoidApp.LEVEL_FOLDER);
        final File scoreFolder = new File(ParanoidApp.SCORE_FOLDER);
        final File scoreStory = new File(ParanoidApp.SCORE_STORY_PATH);
        final File scoreCustom = new File(ParanoidApp.SCORE_CUSTOM_PATH);
        if (!mainFolder.exists()) {
            try {
                final boolean mainFolderCreation = mainFolder.mkdir();
                final boolean levelFolderCreation = levelFolder.mkdir();
                final boolean scoreFolderCreation = scoreFolder.mkdir();
                final boolean scoreStoryFolderCreation = scoreStory.mkdir();
                final boolean scoreCustomInitFolderResult = scoreCustom.mkdir();
                if (mainFolderCreation && levelFolderCreation && scoreFolderCreation 
                    && scoreStoryFolderCreation && scoreCustomInitFolderResult) {
                    SettingsManager.saveOption(settingsBuilder.build());
                    ScoreManager.saveStory(new Score.Builder()
                            .defaultScore(SCORE_STORY_NAME)
                            .build());
                    UserManager.saveUser(new User());
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    private static void unlockStoryLevel() {
        Arrays.asList(LevelSelection.values()).stream()
        .map(i -> i.getLevel())
        .forEach(i -> {
            LevelManager.saveLevel(new Level(i.getBricks(),
                    i.getLevelName() + " (UNLOCK)", 
                    i.getMusic(),
                    i.getBackGround()));
        });
    }
    */
}
