package paranoid.model.component.graphics;

import javafx.scene.image.Image;
import paranoid.common.PlayerId;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;

public class PlayerGraphicsComponent implements GraphicsComponent{

    private final Image playerOneSprite;
    private final Image playerTwoSprite;

    public PlayerGraphicsComponent() {
        this.playerOneSprite = new Image(ClassLoader.getSystemResourceAsStream("sprite/ball.png"));
        this.playerTwoSprite = new Image(ClassLoader.getSystemResourceAsStream("sprite/ball2.png"));
    }

    @Override
    public void update(final GameObject obj, final GraphicsAdapter ga) {
        final Player player = (Player) obj;
        if (player.getPlayerId().equals(PlayerId.ONE)) {
            ga.drawPlayer((Player) obj, this.playerOneSprite);
        } else if (player.getPlayerId().equals(PlayerId.TWO)) {
            ga.drawPlayer((Player) obj, this.playerTwoSprite);
        }

    }

}
