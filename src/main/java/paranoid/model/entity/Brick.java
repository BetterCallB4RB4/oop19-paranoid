package paranoid.model.entity;

import java.io.Serializable;

import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.SerializableColor;
import paranoid.common.V2d;
import paranoid.model.component.input.DummyInputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.DummyPhysicsComponent;

/**
 * Brick entity.
 */
public final class Brick extends GameObj implements Serializable {

    private static final long serialVersionUID = -2501638679114243141L;
    private final SerializableColor color;
    private final int pointEarned;
    private int energy;
    private boolean desctructible;
    /**
     * Constructor.
     * @param pos the position of this game object
     * @param vel the velocity of this game object
     * @param agility the agility of this game object
     * @param height the height of this game object
     * @param width the width of this game object
     */
    private Brick(final P2d pos, final int height, final int width, final Color color, final int pointEarned, final int energy, final boolean destructible) {
        super(pos, new V2d(0, 0), 0, height, width, new DummyPhysicsComponent(), new DummyInputComponent());
        this.color = new SerializableColor(color);
        this.pointEarned = pointEarned;
        this.energy = energy;
        this.desctructible = destructible;
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

    public Color getColor() {
        return this.color.getColor();
    }

    public int getPointEarned() {
        return this.pointEarned;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void decEnergy() {
        this.energy = this.energy - 1;
    }

    public boolean isDestructible() {
        return this.desctructible;
    }

    public static final class Builder {

        private P2d pos;
        private int height;
        private int width;
        private Color color;
        private int pointEarned;
        private int energy;
        private boolean destructible;

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

        public Builder pointEarned(final int pointEarned) {
            this.pointEarned = pointEarned;
            return this;
        }

        public Builder energy(final int energy) {
            this.energy = energy;
            return this;
        }

        public Builder destructible(final boolean destructible) {
            this.destructible = destructible;
            return this;
        }

        public Brick build() {
            if (this.pos == null || this.height <= 0 || this.width <= 0
                    || this.color == null || this.energy <= 0) {
                throw new IllegalStateException();
            }
            return new Brick(this.pos, this.height, this.width, this.color, this.pointEarned, this.energy, this.destructible);
        }
    }
}
