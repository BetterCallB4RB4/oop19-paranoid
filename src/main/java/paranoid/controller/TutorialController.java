package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import paranoid.common.dimension.ScreenConstant;
import paranoid.view.parameters.LayoutManager;

public class TutorialController implements GuiController {

    @FXML
    private SplitPane mainPanel;

    @FXML
    private Button btnMenu;

    /**
     * 
     */
    @FXML
    public void initialize() {
        this.mainPanel.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenConstant.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenConstant.SCREEN_HEIGHT);
    }

    /**
     * 
     */
    @FXML
    public void btnMenuOnClickHandler() {
        final Scene scene = this.btnMenu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }
}
