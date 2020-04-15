package paranoid.controller;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.BoundsAccessor;
import com.sun.scenario.effect.DropShadow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.gameLoop.GameLoop;

import paranoid.model.level.Level;
import paranoid.model.level.LevelManager;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.SettingsManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.parameters.LayoutManager;

public class ChooseLevelController implements GuiController, Observer {

    private Subject subject;

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
        this.subject = sub;
        this.subject.register(this);

        this.mainPanel.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenConstant.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenConstant.SCREEN_HEIGHT);
        this.buttonContainer.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.buttonContainer.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.starter.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.starter.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.buttonContainer.setStyle("-fx-background-color:  linear-gradient(to bottom right, #E438A1, #6749D8);");
        BackgroundImage myBI2 = new BackgroundImage(new Image("backgrounds/dashboard3.png", 
                                                              ScreenConstant.SCREEN_WIDTH / 2,
                                                              ScreenConstant.SCREEN_HEIGHT,
                                                              false,
                                                              true),
                                                    BackgroundRepeat.NO_REPEAT, 
                                                    BackgroundRepeat.NO_REPEAT, 
                                                    BackgroundPosition.CENTER,
                                                    BackgroundSize.DEFAULT);
        this.starter.setBackground(new Background(myBI2));
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
        for (Level lvl : LevelManager.loadLevels()) {
            Button b = new Button(lvl.getLevelName().toUpperCase());
            b.setStyle("-fx-background-color:  linear-gradient(to bottom right, #FFE74E, #A2FD24);"
                     + "-fx-background-radius: 30;"
                     + "-fx-font-size: 20;"
                     + "-fx-font-weight: bold");
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    selectedLevel.setOpacity(100);
                    SettingsBuilder setBuilder = new SettingsBuilder();
                    setBuilder.fromSettings(SettingsManager.loadOption());
                    setBuilder.selectLevel(lvl);
                    SettingsManager.saveOption(setBuilder.build());
                    selectedLevel.setText("Nome livello : " + lvl.getLevelName() + "\n"
                                          + "Sfondo : " + lvl.getBackGround() + "\n"
                                          + "Musica : " + lvl.getMusic());
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

}
