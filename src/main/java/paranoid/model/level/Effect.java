package paranoid.model.level;

import java.net.URL;

public enum Effect {

    /**
     * the collision effect with the edge.
     */
    BOARD_COLLISION("effetti/laser1.wav"),

    /**
     * the collision effect with a brick.
     */
    BRICK_COLLISION("effetti/pepSound1.wav"),

    /**
     * the collision effect with the player.
     */
    PLAYER_COLLISION("effetti/zap1.wav");

    private String location;

    Effect(final String location) {
        this.location = location;
    }

    /**
     * @return the location
     */
    public URL getLocation() {
        return ClassLoader.getSystemResource(location);
    }

}
