package paranoid.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import paranoid.view.parameters.LayoutManager;

/**
 * Stage principale dell'applicazione.
 */
public class MainStage extends Stage {

    public MainStage() {
        super();
        this.setTitle("PARANOID");
        this.setScene(new MainScene());
        this.sizeToScene();
        this.show();
    }

    private final class MainScene extends Scene {
        /**
         * costruttore che aggiunge il layout menu.fxml e le dimensioni alla scena.
         */
        private MainScene() {
            super(LayoutManager.MENU.getLayout(), 850, 700);
        }

    }

}
