package paranoid.main;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import paranoid.model.settings.SettingsManager;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.MainStage;

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
     * the file to save the top scores.
     */
    public static final String SCORE = MAIN_FOLDER + SEPARATOR + "score";

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
        File highScore = new File(SCORE);
        if (!mainFolder.exists()) {
            try {
                mainFolder.mkdir();
                levelFolder.mkdir();
                SettingsManager.saveOption(settingsBuilder.build());
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        if (!highScore.exists()) {
            ScoreManager.saveScore(new Score.Builder().defaultScore().build());

        }
    }

}
