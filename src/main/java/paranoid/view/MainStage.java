package paranoid.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.fxmlcontroller.ChooseLevelController;
import paranoid.controller.fxmlcontroller.MenuController;
import paranoid.controller.fxmlcontroller.ScoreController;
import paranoid.view.parameters.LayoutManager;

/**
 * Main stage of this application.
 */
public class MainStage extends Stage {

    public MainStage() {
        super();
        this.setTitle("PARANOID");
        this.setScene(new MainScene());
        this.sizeToScene();
        this.setResizable(false);
        this.show();
    }

    private final class MainScene extends Scene {

        /**
         * Add layout menu.fxml and size to the scene.
         * connection of observers to subjects following the observer pattern.
         */
        private MainScene() {
            super(LayoutManager.MENU.getLayout(), ScreenConstant.SCREEN_WIDTH, ScreenConstant.SCREEN_HEIGHT);
            final MenuController menuController = (MenuController) LayoutManager.MENU.getGuiController();
            final ScoreController scoreController = (ScoreController) LayoutManager.SCORE.getGuiController();
            final ChooseLevelController chooseLevel = (ChooseLevelController) LayoutManager.CHOOSE_LVL.getGuiController();
            scoreController.initialize(menuController);
            chooseLevel.initialize(menuController);
        }
    }
}
