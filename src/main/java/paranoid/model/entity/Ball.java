package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.graphics.BallGraphicsComponent;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.input.DummyInputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.BallPhysicsComponent;


public final class Ball extends GameObj {

    private static final long serialVersionUID = 9139431443544241120L;

    private Ball(final P2d pos, final V2d vel, final double agility, final int height, final int width) {
        super(pos, vel, agility, height, width, new BallPhysicsComponent(), new DummyInputComponent(), new BallGraphicsComponent());
    }

    /**
     * 
     * simulate a vertical collision.
     */
    public void flipVelOnY() {
        this.setVel(new V2d(this.getVel().getX(), -this.getVel().getY()));
    }

    /**
     * 
     * simulate a horizontal collision.
     */
    public void flipVelOnX() {
        this.setVel(new V2d(-this.getVel().getX(), this.getVel().getY()));
    }

    /**
     * 
     * allows to update the physics component of the ball.
     */
    @Override
    public void updatePhysics(final int dt, final World w) {
        this.getPhysicsComponent().update(dt, this, w);
    }

    /**
     * 
     * allows to update the input component of the ball.
     */
    @Override
    public void updateInput(final InputController controller) {
        this.getInputComponent().update(this, controller);
    }

    /**
     * allows to update the graphics component of the ball.
     */
    @Override
    public void updateGraphics(final GraphicsAdapter graphicsAdapter) {
        this.getGraphicsComponent().update(this, graphicsAdapter);
    }

    public static class Builder {

        private P2d pos;
        private V2d dir;
        private double speed;
        private int height;
        private int width;

        /**
         * @param position position to set
         * @return returns himself following the pattern builder
         */
        public Builder position(final P2d position) {
            this.pos = position;
            return this;
        }

        /**
         * set the new ball direction.
         * @param direction direction to set
         * @return returns himself following the pattern builder
         */
        public Builder direction(final V2d direction) {
            this.dir = direction;
            return this;
        }

        /**
         * set the new ball speed.
         * @param speed speed to set
         * @return returns himself following the pattern builder
         */
        public Builder speed(final double speed) {
            this.speed = speed;
            return this;
        }

        /**
         * set the new height. 
         * @param height height to se
         * @return returns himself following the pattern builder
         */
        public Builder height(final int height) {
            this.height = height;
            return this;
        }

        /**
         * set the new width.
         * @param width width to set
         * @return returns himself following the pattern builder
         */
        public Builder width(final int width) {
            this.width = width;
            return this;
        }

        /**
         * builds the ball by checking if the past information is all valid.
         * @return the constructed ball
         */
        public Ball build() {
            if (this.pos == null || this.height <= 0 || this.width <= 0 
             || this.dir == null || this.speed < 0) {

                throw new IllegalStateException();
            }
            return new Ball(pos, dir, speed, height, width);
        }

    }

}
