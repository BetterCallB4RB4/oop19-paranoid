package paranoid.model.component.graphics;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Brick;
import paranoid.model.entity.Player;

public interface GraphicsAdapter {

    void drawBall(Ball ball, Image sprite);

    void drawPlayer(Player player, Image sprite);

    void drawBrick(Brick brick, Color color);

}
