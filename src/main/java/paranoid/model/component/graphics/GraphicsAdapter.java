package paranoid.model.component.graphics;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Brick;
import paranoid.model.entity.Player;

public interface GraphicsAdapter {

    /**
     * allows the graphics to draw the ball
     * with the characteristics of the graphic component.
     * @param ball
     * @param sprite
     */
    void drawBall(Ball ball, Image sprite);

    /**
     * allows the graphics to draw the player 
     * with the characteristics of the graphic component.
     * @param player
     * @param sprite
     */
    void drawPlayer(Player player, Image sprite);

    /**
     * allows the graphics to draw the brick
     * with the characteristics of the graphic component.
     * @param brick
     * @param color
     */
    void drawBrick(Brick brick, Color color);

}
