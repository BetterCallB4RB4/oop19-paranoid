package paranoid.model.component.graphics;

import javafx.scene.image.Image;
import paranoid.model.entity.Ball;

public interface GraphicsAdapter {

    void drawBall(Ball ball, Image sprite);

}
