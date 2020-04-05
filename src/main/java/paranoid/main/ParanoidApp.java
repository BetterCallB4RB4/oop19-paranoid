package paranoid.main;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
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
        File mainFolder = new File(ParanoidApp.MAIN_FOLDER);
        File levelFolder = new File(ParanoidApp.LEVEL_FOLDER);
        if (!mainFolder.exists()) {
            try {
                mainFolder.mkdir();
                levelFolder.mkdir();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

}
