package paranoid.model.component.graphics;

import javafx.scene.image.Image;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Player;

public interface GraphicsAdapter {

    void drawBall(Ball ball, Image sprite);

    void drawPlayer(Player player, Image sprite);

}
