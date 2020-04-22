package paranoid.model.entity;

import java.util.HashMap;
import java.util.Map;

import paranoid.common.Collision;
import paranoid.common.P2d;
import paranoid.common.PlayerId;
import paranoid.common.V2d;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.graphics.PlayerGraphicsComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.input.PlayerInputComponent;
import paranoid.model.component.physics.PlayerPhysicsComponent;

/**
 * Player entity.
 */
public final class Player extends GameObj {

    private static final long serialVersionUID = -854724973379080675L;
    private static final int PLAYER_SPEED = 450;
    private final Map<Ball, Collision> lastZonePresence = new HashMap<>();
    private final PlayerId playerId;

    private Player(final P2d pos, final int height, final int width, final PlayerId playerId) {
        super(pos, new V2d(0, 0), PLAYER_SPEED, height, width, new PlayerPhysicsComponent(), new PlayerInputComponent(), new PlayerGraphicsComponent());
        this.playerId = playerId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhysics(final int dt, final World w) {
        super.getPhysicsComponent().update(dt, this, w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final InputController controller) {
        super.getInputComponent().update(this, controller);

    }

    /**
     * allows to update the graphics component of the player.
     */
    @Override
    public void updateGraphics(final GraphicsAdapter graphicsAdapter) {
        this.getGraphicsComponent().update(this, graphicsAdapter);
    }

    /**
     * @return the id of the player, different id retrieve different player attributes.
     */
    public PlayerId getPlayerId() {
        return this.playerId;
    }

    /**
     * @return a map that contains the information of all ball in the game and the
     * zone of presence for allow the ball to bounce fine when hit the player.
     */
    public Map<Ball, Collision> getLastZonePresence() {
        return this.lastZonePresence;
    }

    /**
     * Static nested class for build the player.
     */
    public static final class Builder {

        private P2d pos;
        private int height;
        private int width;
        private PlayerId playerId;

        /**
         * @param pos the position of the player to set.
         * @return the player builder.
         */
        public Builder position(final P2d pos) {
            this.pos = pos;
            return this;
        }

        /**
         * @param height the height of the player to set.
         * @return the player builder.
         */
        public Builder height(final int height) {
            this.height = height;
            return this;
        }

        /**
         * @param width the width of the player to set.
         * @return the player builder.
         */
        public Builder width(final int width) {
            this.width = width;
            return this;
        }

        /**
         * @param playerId the id of the player to set.
         * @return the player builder.
         */
        public Builder playerId(final PlayerId playerId) {
            this.playerId = playerId;
            return this;
        }

        /**
         * Build the player and check if fields are set correctly.
         * @return the new Player with the selected properties.
         */
        public Player build() {
            if (this.pos == null || this.height <= 0 || this.width <= 0
                    || this.playerId == null) {

                throw new IllegalStateException();
            }
            return new Player(this.pos, this.height, this.width, this.playerId);
        }
    }

}
