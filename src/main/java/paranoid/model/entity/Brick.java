package paranoid.model.entity;

import java.io.Serializable;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.SerializableColor;
import paranoid.common.V2d;
import paranoid.model.collision.Collision;
import paranoid.model.component.graphics.BrickGraphicsComponent;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.input.DummyInputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.DummyPhysicsComponent;

/**
 * Brick entity.
 */
public final class Brick extends GameObj implements Serializable {

    private static final long serialVersionUID = -2501638679114243141L;
    private final Map<Ball, Collision> lastZonePresence = new HashMap<>();
    private final boolean indesctructible;
    private final int pointEarned;
    private int energy;

    /**
     * Constructor.
     * @param pos the position of this game object
     * @param vel the velocity of this game object
     * @param agility the agility of this game object
     * @param height the height of this game object
     * @param width the width of this game object
     */
    private Brick(final P2d pos, final int height, final int width, final Color color, final int pointEarned, final int energy, final boolean indestructible) {
        super(pos, new V2d(0, 0), 0, height, width, new DummyPhysicsComponent(), new DummyInputComponent(), new BrickGraphicsComponent(new SerializableColor(color)));
        this.pointEarned = pointEarned;
        this.energy = energy;
        this.indesctructible = indestructible;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhysics(final int dt, final WorldImpl w) {
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
     * allows to update the graphics component of the brick.
     */
    @Override
    public void updateGraphics(final GraphicsAdapter graphicsAdapter) {
        this.getGraphicsComponent().update(this, graphicsAdapter);
    }

    /**
     * @return the point that user earn when hit a brick.
     */
    public int getPointEarned() {
        return this.pointEarned;
    }

    /**
     * @return the energy of the brick. When energy is 0 the brick will be removed.
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Decrease the energy of brick.
     */
    public void decEnergy() {
        this.energy = this.energy - 1;
    }

    /**
     * @return true if the brick is indestructible or false if it can be destroyed
     */
    public boolean isIndestructible() {
        return this.indesctructible;
    }

    /**
     * @return a map that contains the information of all ball in the game and the
     * zone of presence for allow the ball to bounce fine when hit the brick.
     */
    public Map<Ball, Collision> getLastZonePresence() {
        return this.lastZonePresence;
    }

    /**
     * Static nested class for build the brick.
     */
    public static final class Builder {

        private P2d pos;
        private int height;
        private int width;
        private Color color;
        private int pointEarned;
        private int energy;
        private boolean indestructible;

        /**
         * @param pos the position of the brick to set.
         * @return the brick builder.
         */
        public Builder position(final P2d pos) {
            this.pos = pos;
            return this;
        }

        /**
         * @param height the height of the brick to set.
         * @return the brick builder.
         */
        public Builder height(final int height) {
            this.height = height;
            return this;
        }

        /**
         * @param width the width of the brick to set.
         * @return the brick builder.
         */
        public Builder width(final int width) {
            this.width = width;
            return this;
        }

        /**
         * @param color the color of the brick to set.
         * @return the brick builder.
         */
        public Builder color(final Color color) {
            this.color = color;
            return this;
        }

        /**
         * @param pointEarned the points of the brick to set.
         * @return the brick builder.
         */
        public Builder pointEarned(final int pointEarned) {
            this.pointEarned = pointEarned;
            return this;
        }

        /**
         * @param energy the energy of the brick to set.
         * @return the brick builder.
         */
        public Builder energy(final int energy) {
            this.energy = energy;
            return this;
        }

        /**
         * @param indestructible set if the brick is indestructible or not.
         * @return the brick builder.
         */
        public Builder indestructible(final boolean indestructible) {
            this.indestructible = indestructible;
            return this;
        }

        /**
         * Build the brick and check if fields are set correctly.
         * @return the new Brick with the selected properties.
         */
        public Brick build() {
            if (this.pos == null || this.height <= 0 || this.width <= 0
                    || this.color == null || this.energy <= 0) {
                throw new IllegalStateException();
            }
            return new Brick(this.pos, this.height, this.width, this.color, this.pointEarned, this.energy, this.indestructible);
        }
    }
}
