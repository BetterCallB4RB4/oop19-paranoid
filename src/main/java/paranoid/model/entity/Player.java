package paranoid.model.entity;

import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.PlayerId;
import paranoid.common.V2d;
import paranoid.model.component.graphics.DummyGraphicsComponent;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.input.InputController;
import paranoid.model.component.input.PlayerInputComponent;
import paranoid.model.component.physics.PlayerPhysicsComponent;

/**
 * Player entity.
 */
public final class Player extends GameObj {

    private final Color color;
    private final PlayerId playerId;
    private Player(final P2d pos, final int height, final int width, final Color color, final PlayerId playerId) {
        super(pos, new V2d(0, 0), 300, height, width, new PlayerPhysicsComponent(), new PlayerInputComponent(), new DummyGraphicsComponent());
        this.color = color;
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

    public Color getColor() {
        return this.color;
    }
    public PlayerId getPlayerId() {
        return this.playerId;
    }

    public static final class Builder {

        private P2d pos;
        private int height;
        private int width;
        private Color color;
        private PlayerId playerId;

        public Builder position(final P2d pos) {
            this.pos = pos;
            return this;
        }

        public Builder height(final int height) {
            this.height = height;
            return this;
        }

        public Builder width(final int width) {
            this.width = width;
            return this;
        }

        public Builder color(final Color color) {
            this.color = color;
            return this;
        }

        public Builder playerId(final PlayerId playerId) {
            this.playerId = playerId;
            return this;
        }

        public Player build() {
            if (this.pos == null || this.height <= 0 || this.width <= 0 || this.color == null
                    || this.playerId == null) {

                throw new IllegalStateException();
            }
            return new Player(this.pos, this.height, this.width, this.color, this.playerId);
        }
    }

}
