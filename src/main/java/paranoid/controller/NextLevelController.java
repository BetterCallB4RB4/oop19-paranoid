package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import paranoid.controller.gameloop.GameLoop;
import paranoid.model.level.Level;
import paranoid.model.score.User;
import paranoid.view.parameters.LayoutManager;

public class NextLevelController implements GuiController{

    @FXML
    private Label lblLevel;

    @FXML
    private Label lblLives;

    @FXML
    private Label lblScore;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnNext;

    @FXML
    private void btnMenuOnClickHandler() {
        final Scene scene = btnMenu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }

    @FXML
    private void btnNextOnClickHandler() {
        final Scene scene = btnNext.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true); //allow jvm to close the thread when close the window.
        engine.start();
    }

    public void update(final Level lvl, final User user) {
        this.lblLevel.setText(lvl.getLevelName());
        this.lblLives.setText("VITE RIMASTE: " + user.getLives().toString());
        this.lblScore.setText("PUNTEGGIO: " + user.getScore().toString());
    }
}
