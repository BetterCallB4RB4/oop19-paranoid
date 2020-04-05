package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.input.InputController;
import paranoid.model.component.input.PlayerInputComponent;
import paranoid.model.component.physics.PlayerPhysicsComponent;

/**
 * Player entity.
 */
public final class Player extends GameObj {

    private Player(final P2d pos, final int height, final int width) {
        super(pos, new V2d(0, 0), 300, height, width, new PlayerPhysicsComponent(), new PlayerInputComponent());
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

    public static final class Builder {

        private P2d pos;
        private int height;
        private int width;

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

        public Player build() {
            if (this.pos == null || this.height <= 0 || this.width <= 0) {
                throw new IllegalStateException();
            }
            return new Player(this.pos, this.height, this.width);
        }
    }

}
