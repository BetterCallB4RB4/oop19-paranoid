package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import paranoid.common.dimension.ScreenConstant;
import paranoid.view.parameters.LayoutManager;

/**
 * Controller of tutorial.fxml.
 */
public class TutorialController implements GuiController {

    @FXML
    private SplitPane mainPanel;

    @FXML
    private Button btnMenu;

    /**
     * Initialize the window with default settings adapted to the monitor resolution.
     */
    @FXML
    public void initialize() {
        this.mainPanel.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenConstant.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenConstant.SCREEN_HEIGHT);
    }

    /**
     * Go back to menu when click with mouse the button menu.
     */
    @FXML
    public void btnMenuOnClickHandler() {
        final Scene scene = this.btnMenu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }
}
