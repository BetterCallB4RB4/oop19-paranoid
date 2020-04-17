package paranoid.model.component.graphics;

import javafx.scene.image.Image;
import paranoid.common.PlayerId;
import paranoid.common.ResourceLoader;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;

public class PlayerGraphicsComponent implements GraphicsComponent{

    private final Image playerOneSprite;
    private final Image playerTwoSprite;

    public PlayerGraphicsComponent() {
        this.playerOneSprite = ResourceLoader.PLAYER_ONE_SPRITE.getSprite();
        this.playerTwoSprite = ResourceLoader.PLAYER_TWO_SPRITE.getSprite();
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
