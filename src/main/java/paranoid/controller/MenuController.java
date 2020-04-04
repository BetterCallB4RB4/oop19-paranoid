package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import paranoid.controller.gameLoop.GameLoop;

/**
 * Controller of menu.fxml.
 */
public final class MenuController implements GuiController {

    @FXML
    private Button btnStart;

    /**
     * Handle start game button event.
     */
    @FXML
    public void btnStartonClickHandler() {
        final Scene scene = btnStart.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true); //allow jvm to close the thread when close the window.
        engine.start();
    }
}
