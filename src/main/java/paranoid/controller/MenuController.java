package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Controller relativo alla gui menu.fxml.
 */
public final class MenuController implements GuiController {

    @FXML
    private Button btnStart;

    /**
     * Gestore evento click del mouse sul bottone inizia partita.
     */
    @FXML
    public void btnStartonClickHandler() {
        final Scene scene = btnStart.getScene();
        final Thread engine = new Thread(new GameEngine(this.scene));
        engine.setDaemon(true); //permette alla VM di chiudere il thread quando si esce dall' app.
        engine.start();
    }
}
