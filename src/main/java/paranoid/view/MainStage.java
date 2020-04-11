package paranoid.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.ChooseLevelController;
import paranoid.controller.GameBuilderController;
import paranoid.controller.GameController;
import paranoid.controller.SettingsController;
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
         * Constructor.
         * Add layout menu.fxml and size to the scene.
         *  e collego gli osservatori al soggetto
         */
        private MainScene() {
            super(LayoutManager.MENU.getLayout(), ScreenConstant.SCREEN_WIDTH, ScreenConstant.SCREEN_HEIGHT);
            GameBuilderController builder = (GameBuilderController) LayoutManager.LEVEL_BUILDER.getGuiController();
            ChooseLevelController chooseLevel = (ChooseLevelController) LayoutManager.CHOOSE_LVL.getGuiController();
            chooseLevel.initialize(builder);
        }

    }

}
