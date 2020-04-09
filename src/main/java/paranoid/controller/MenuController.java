package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import paranoid.controller.gameLoop.GameLoop;
import paranoid.view.parameters.LayoutManager;

/**
 * Controller of menu.fxml.
 */
public final class MenuController implements GuiController {

    @FXML
    private Button btnStart;

    @FXML
    private Button btnScore;

    @FXML
    private Button btnBuilder;

    @FXML
    private Button btSettings;

    @FXML
    private Button btnTutorial;

    /**
     * Handle start game button event.
     */
    @FXML
    public void btnStartOnClickHandler() {
        final Scene scene = btnStart.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true); //allow jvm to close the thread when close the window.
        engine.start();
    }

    /**
     * Handle high score button event.
     */
    @FXML
    public void btnScoreOnClickHandler() {
        final Scene scene = btnScore.getScene();
        scene.setRoot(LayoutManager.SCORE.getLayout());
    }
    @FXML
    public void goToLevelBuilder() {
        final Scene scene = btnBuilder.getScene();
        scene.setRoot(LayoutManager.LEVEL_BUILDER.getLayout());
    }

    @FXML
    public void goToSettings() {
        final Scene scene = btSettings.getScene();
        scene.setRoot(LayoutManager.SETTINGS.getLayout());
    }

    @FXML
    public void btnTutorialOnClickHandler() {
        final Scene scene = btnTutorial.getScene();
        scene.setRoot(LayoutManager.TUTORIAL.getLayout());
    }

}
