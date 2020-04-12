package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import paranoid.controller.gameLoop.GameLoop;
import paranoid.model.settings.SettingsManager;
import paranoid.model.level.LevelSelection;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.parameters.LayoutManager;

/**
 * Controller of menu.fxml.
 */
public final class MenuController implements GuiController {

    @FXML
    private Button continua;

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

    @FXML
    private Button playYourLvl;

    @FXML
    public void clickContinue() {
        final Scene scene = continua.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true); //allow jvm to close the thread when close the window.
        engine.start();
    }

    /**
     * Handle start game button event.
     */
    @FXML
    private void btnStartOnClickHandler() {
        SettingsBuilder setBuilder = new SettingsBuilder();
        setBuilder.fromSettings(SettingsManager.loadOption());
        setBuilder.selectLevel(LevelSelection.LEVEL1.getLevel());
        SettingsManager.saveOption(setBuilder.build());
        final Scene scene = btnStart.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true); //allow jvm to close the thread when close the window.
        engine.start();
    }

    /**
     * Handle high score button event.
     */
    @FXML
    private void btnScoreOnClickHandler() {
        final Scene scene = btnScore.getScene();
        scene.setRoot(LayoutManager.SCORE.getLayout());
    }
    @FXML
    private void goToLevelBuilder() {
        final Scene scene = btnBuilder.getScene();
        scene.setRoot(LayoutManager.LEVEL_BUILDER.getLayout());
    }

    @FXML
    private void goToSettings() {
        final Scene scene = btSettings.getScene();
        scene.setRoot(LayoutManager.SETTINGS.getLayout());
    }

    @FXML
    private void btnTutorialOnClickHandler() {
        final Scene scene = btnTutorial.getScene();
        scene.setRoot(LayoutManager.TUTORIAL.getLayout());
    }

    @FXML
    public void clickPlayYourLvl() {
        final Scene scene = playYourLvl.getScene();
        scene.setRoot(LayoutManager.CHOOSE_LVL.getLayout());
    }

}
