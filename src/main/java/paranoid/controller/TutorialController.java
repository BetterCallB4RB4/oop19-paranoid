package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import paranoid.view.parameters.LayoutManager;

public class TutorialController implements GuiController {

    @FXML
    private Button btnMenu;

    /**
     * 
     */
    @FXML
    public void btnMenuOnClickHandler() {
        final Scene scene = this.btnMenu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }
}
