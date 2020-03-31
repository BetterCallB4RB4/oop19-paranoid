package paranoid.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Stage principale dell'applicazione.
 */
public class MainStage extends Stage {

    public MainStage() {
        super();
        this.setTitle("PARANOID");
        this.setScene(new MainScene(null));
        this.sizeToScene();
        this.show();
    }

    public class MainScene extends Scene {

        public MainScene(Parent root) {
            super(root);
            // TODO Auto-generated constructor stub
        }

    }

}
