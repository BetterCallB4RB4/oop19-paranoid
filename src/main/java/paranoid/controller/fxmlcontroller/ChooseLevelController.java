package paranoid.controller.fxmlcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import paranoid.common.ScreenConstant;
import paranoid.controller.gameloop.GameLoop;
import paranoid.model.level.Level;
import paranoid.model.level.LevelManager;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.SettingsManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.layoutmanager.LayoutManager;

public class ChooseLevelController implements GuiController, Observer {

    private Level currentSelectedLevel;

    @FXML
    private SplitPane mainPanel;

    @FXML
    private ScrollPane scroller;

    @FXML
    private VBox buttonContainer;

    @FXML
    private VBox starter;

    @FXML
    private Label selectedLevel;

    @FXML
    private Button startBtn;

    @FXML
    private Button back;

    /**
     * initializes the window by setting dimensions.
     * @param sub connect the observer to the subject.
     */
    @FXML
    public void initialize(final Subject sub) {
        sub.register(this);
        this.mainPanel.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenConstant.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenConstant.SCREEN_HEIGHT);
        this.buttonContainer.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.buttonContainer.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.starter.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.starter.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scroller.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scroller.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scroller.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scroller.setVbarPolicy(ScrollBarPolicy.NEVER);
        selectedLevel.setOpacity(0);
        this.update();
    }

    /**
     * implementation of pattern observer.
     * reads the new generated levels and creates a button as a reference
     * by copying the style from an existing one
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.buttonContainer.getChildren().clear();
        for (final Level lvl : LevelManager.loadLevels()) {
            final Button b = new Button(lvl.getLevelName());
            this.setButtonStyle(b, back);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    selectedLevel.setOpacity(100);
                    currentSelectedLevel = lvl;
                    selectedLevel.setText("Level name : " + lvl.getLevelName() + "\n"
                                          + "Background : " + lvl.getBackGround().getName() + "\n"
                                          + "Music : " + lvl.getMusic().getName());
                }
            });
            this.buttonContainer.getChildren().add(b);
        }
    }

    /**
     * loads the currently selected level and starts the gameloop cycle.
     */
    @FXML
    public void startMatch() {
        if (!selectedLevel.getText().isBlank()) {
            UserManager.saveUser(new User());
            final Scene scene = startBtn.getScene();
            final SettingsBuilder setBuilder = new SettingsBuilder();
            setBuilder.fromSettings(SettingsManager.loadOption());
            setBuilder.selectLevel(currentSelectedLevel);
            SettingsManager.saveOption(setBuilder.build());
            final Thread engine = new Thread(new GameLoop(scene));
            engine.setDaemon(true);
            engine.start();
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("No level has been selected");
            alert.showAndWait();
        }
    }

    /**
     * allow to return to the main menu.
     */
    @FXML
    public void backToMenu() {
        final Scene scene = back.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }

    /**
     * copy the style of a button to another button.
     * @param subject the button that takes the new style
     * @param reference the button that gives the new style
     */
    private void setButtonStyle(final Button subject, final Button reference) {
        subject.setStyle(reference.getStyle());
        subject.setEffect(reference.getEffect());
        subject.setFont(reference.getFont());
    }
}
