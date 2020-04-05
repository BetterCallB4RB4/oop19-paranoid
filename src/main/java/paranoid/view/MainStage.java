package paranoid.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import paranoid.common.dimension.ScreenConstant;
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
         */
        private MainScene() {
            super(LayoutManager.MENU.getLayout(), ScreenConstant.SCREEN_WIDTH, ScreenConstant.SCREEN_HEIGHT);
        }

    }

}
