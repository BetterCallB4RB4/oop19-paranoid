package paranoid.model.component.graphics;

import javafx.scene.image.Image;
import paranoid.model.entity.Ball;
import paranoid.model.entity.GameObject;

public class BallGraphicsComponent implements GraphicsComponent {

    private Image ballSprite;

    public BallGraphicsComponent() {
        this.ballSprite = new Image(ClassLoader.getSystemResourceAsStream("sprite/ball3.png"));
    }

    /**
     * 
     */
    @Override
    public void update(final GameObject obj, final GraphicsAdapter ga) {
        ga.drawBall((Ball) obj, ballSprite);
    }

}
