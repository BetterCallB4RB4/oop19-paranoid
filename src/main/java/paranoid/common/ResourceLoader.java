package paranoid.common;

import javafx.scene.image.Image;

/**
 * Enum for static load sprites at the start of application for optimize faster object
 * in game creation.
 */
public enum ResourceLoader {
    /**
     * ball image path.
     */
    BALL_SPRITE("sprite/discoBall.png"),

    /**
     * player one image path.
     */
    PLAYER_ONE_SPRITE("sprite/player1.png"),

    /**
     * player two image path.
     */
    PLAYER_TWO_SPRITE("sprite/player2.png");

    private final transient Image sprite;

    ResourceLoader(final String name) {
        this.sprite = new Image(ClassLoader.getSystemResourceAsStream(name));
    }

    public Image getSprite() {
        return this.sprite;
    }
}
