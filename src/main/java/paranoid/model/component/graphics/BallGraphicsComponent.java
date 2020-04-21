package paranoid.model.component.graphics;

import javafx.scene.image.Image;
import paranoid.common.ResourceLoader;
import paranoid.model.entity.Ball;
import paranoid.model.entity.GameObject;

public class BallGraphicsComponent implements GraphicsComponent {

    private final Image ballSprite;

    public BallGraphicsComponent() {
        this.ballSprite = ResourceLoader.BALL_SPRITE.getSprite();
    }

    /**
     * draw the ball by passing the specific graphic information to the graphic adapter. 
     */
    @Override
    public void update(final GameObject obj, final GraphicsAdapter ga) {
        ga.drawBall((Ball) obj, ballSprite);
    }

}
