package paranoid.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.gameloop.GameLoop;
import paranoid.model.settings.SettingsManager;
import paranoid.model.level.LevelSelection;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.parameters.LayoutManager;

/**
 * Controller of menu.fxml.
 */
public final class MenuController implements GuiController, Subject {

    private List<Observer> observers;

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
    private SplitPane backGroundPane;

    @FXML
    private VBox backDash;

    @FXML
    public void initialize() {
        this.observers = new ArrayList<>();
        this.backGroundPane.setMinWidth(ScreenConstant.CANVAS_WIDTH);
        this.backGroundPane.setMaxWidth(ScreenConstant.CANVAS_WIDTH);
        this.backGroundPane.setMinHeight(ScreenConstant.CANVAS_HEIGHT);
        this.backGroundPane.setMaxHeight(ScreenConstant.CANVAS_HEIGHT);
    }

    @FXML
    public void clickContinue() {
        final Scene scene = continua.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true);
        engine.start();
    }

    /**
     * Handle start game button event.
     */
    @FXML
    public void btnStartOnClickHandler() {
        final SettingsBuilder setBuilder = new SettingsBuilder();
        setBuilder.fromSettings(SettingsManager.loadOption());
        setBuilder.selectLevel(LevelSelection.LEVEL1.getLevel());
        SettingsManager.saveOption(setBuilder.build());
        UserManager.saveUser(new User());
        final Scene scene = btnStart.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true);
        engine.start();
    }

    /**
     * Handle high score button event.
     */
    @FXML
    public void btnScoreOnClickHandler() {
        this.notifyObserver();
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

    @FXML
    public void clickPlayYourLvl() {
        this.notifyObserver();
        final Scene scene = playYourLvl.getScene();
        scene.setRoot(LayoutManager.CHOOSE_LVL.getLayout());
    }

    @Override
    public void register(final Observer obs) {
        this.observers.add(obs);

    }

    @Override
    public void unregister(final Observer obs) {
        this.observers.remove(obs);

    }

    @Override
    public void notifyObserver() {
        this.observers.forEach(Observer::update);

    }

}
