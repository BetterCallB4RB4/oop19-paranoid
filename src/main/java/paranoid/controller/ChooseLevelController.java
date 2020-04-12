package paranoid.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.gameLoop.GameLoop;

import paranoid.model.level.Level;
import paranoid.model.level.LevelManager;

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

        this.buttonContainer.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.buttonContainer.setMaxWidth(ScreenConstant.SCREEN_WIDTH);

        this.scroller.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.scroller.setMaxWidth(ScreenConstant.SCREEN_WIDTH);
        this.scroller.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.update();
    }

    /**
     * implement pattern observer.
     */
    @Override
    public void update() {
        this.buttonContainer.getChildren().clear();
        for (Level lvl : LevelManager.loadLevels()) {
            Button b = new Button(lvl.getLevelName());
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    SettingsBuilder setBuilder = new SettingsBuilder();
                    setBuilder.fromSettings(SettingsManager.loadOption());
                    setBuilder.selectLevel(lvl);
                    SettingsManager.saveOption(setBuilder.build());
                    final Scene scene = b.getScene();
                    final Thread engine = new Thread(new GameLoop(scene));
                    engine.setDaemon(true);
                    engine.start();
                }
            });
            this.buttonContainer.getChildren().add(b);
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
