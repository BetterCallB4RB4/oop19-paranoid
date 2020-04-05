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
    private Button btnBuilder;

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

    /**
     * Gestore evento click del mouse sul bottone inizia partita.
     */
    @FXML
    public void goToLevelBuilder() {
        final Scene scene = btnBuilder.getScene();
        scene.setRoot(LayoutManager.LEVEL_BUILDER.getLayout());
    }

}
