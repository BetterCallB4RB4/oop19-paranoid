package paranoid.main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PARANOID MAIN.
 * Creazione dello stage.
 *
 */
public class ParanoidApp extends Application {

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
        launch();
    }

}
