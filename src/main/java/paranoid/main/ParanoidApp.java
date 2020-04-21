package paranoid.main;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import paranoid.model.settings.SettingsManager;
import paranoid.model.level.LevelManager;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.MainStage;
import paranoid.model.level.Level;
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
     * 
     */
    public static final String USER = MAIN_FOLDER + SEPARATOR + "user";

    /**
     * 
     */
    public static final String SCORE_FOLDER = MAIN_FOLDER + SEPARATOR + "scores";
    /**
     * the file to save the top story scores.
     */
    public static final String SCORE_STORY = SCORE_FOLDER + SEPARATOR + "story";

    /**
     * the file to save the top story scores.
     */
    public static final String SCORE_CUSTOM = SCORE_FOLDER + SEPARATOR + "custom";

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
     * @param args
     */
    public static void main(final String[] args) {
        initSoftware();
        launch();
    }

    /**
     * 
     * if not present, create the folder to keep the game files.
     */
    public static void initSoftware() {
        SettingsBuilder settingsBuilder = new SettingsBuilder();
        File mainFolder = new File(ParanoidApp.MAIN_FOLDER);
        File levelFolder = new File(ParanoidApp.LEVEL_FOLDER);
        File scoreFolder = new File(ParanoidApp.SCORE_FOLDER);
        File scoreStory = new File(ParanoidApp.SCORE_STORY);
        File scoreCustom = new File(ParanoidApp.SCORE_CUSTOM);
        if (!mainFolder.exists()) {
            try {
                mainFolder.mkdir();
                levelFolder.mkdir();
                scoreFolder.mkdir();
                scoreStory.mkdir();
                scoreCustom.mkdir();
                SettingsManager.saveOption(settingsBuilder.build());
                ScoreManager.saveStory(new Score.Builder()
                        .defaultScore("storia")
                        .build());
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        final List<Level> levelList = LevelManager.loadLevels();
        levelList.forEach(i -> {
            final String levelName = i.getLevelName();
            if (!new File(ParanoidApp.SCORE_CUSTOM + SEPARATOR + levelName).exists()) {
                ScoreManager.saveCustom(new Score.Builder()
                        .defaultScore(levelName)
                        .build());
            }
        });
    }

}
