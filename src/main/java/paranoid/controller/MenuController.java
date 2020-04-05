package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import paranoid.controller.gameLoop.GameLoop;
import paranoid.view.parameters.LayoutManager;

/**
 * Controller relativo alla gui menu.fxml.
 */
public final class MenuController implements GuiController {

    @FXML
    private Button btnStart;

    @FXML
    private Button btnBuilder;

    /**
     * Gestore evento click del mouse sul bottone inizia partita.
     */
    @FXML
    public void btnStartonClickHandler() {
        final Scene scene = btnStart.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true); //permette alla VM di chiudere il thread quando si esce dall' app.
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
