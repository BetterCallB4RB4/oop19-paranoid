package paranoid.controller;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.gameloop.GameLoop;
import paranoid.model.level.Level;
import paranoid.model.level.LevelManager;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.SettingsManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.parameters.LayoutManager;

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
     * init the window.
     * @param sub of pattern observer
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
     * implement pattern observer.
     */
    @Override
    public void update() {
        this.buttonContainer.getChildren().clear();
        for (final Level lvl : LevelManager.loadLevels()) {
            Button b = new Button(lvl.getLevelName());
            this.setButtonStyle(b, back);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    selectedLevel.setOpacity(100);
                    currentSelectedLevel = lvl;
                    selectedLevel.setText("Level name : " + lvl.getLevelName() + "\n"
                                          + "Background : " + lvl.getBackGround() + "\n"
                                          + "Music : " + lvl.getMusic());
                }
            });
            this.buttonContainer.getChildren().add(b);
        }
    }

    /**
     * 
     */
    @FXML
    public void startMatch() {
        if (!selectedLevel.getText().isBlank()) {
            UserManager.saveUser(new User());
            final Scene scene = startBtn.getScene();
            SettingsBuilder setBuilder = new SettingsBuilder();
            setBuilder.fromSettings(SettingsManager.loadOption());
            setBuilder.selectLevel(currentSelectedLevel);
            SettingsManager.saveOption(setBuilder.build());
            final Thread engine = new Thread(new GameLoop(scene));
            engine.setDaemon(true);
            engine.start();
        } else {
            JOptionPane.showMessageDialog(null, "devi selezionare un livello");
        }
    }

    /**
     * 
     */
    @FXML
    public void backToMenu() {
        final Scene scene = back.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }

    private void setButtonStyle(final Button subject, final Button reference) {
        subject.setStyle(reference.getStyle());
        subject.setEffect(reference.getEffect());
        subject.setFont(reference.getFont());
    }
}
