package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.input.DummyInputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.BallPhysicsComponent;

public class Ball extends GameObj {

    private boolean isMoving;

    public Ball(final P2d pos, final V2d vel, final double agility, final int height, final int width) {
        super(pos, vel, agility, height, width, new BallPhysicsComponent(), new DummyInputComponent());
        this.isMoving = true;
    }

    /**
     * 
     * @param newState allow the update of physic component
     */
    public void setBallMotion(final boolean newState) {
        this.isMoving = newState;
    }

    /**
     * 
     * @return if the ball can move 
     */
    public boolean isBallMoving() {
        return this.isMoving;
    }

    /**
     * 
     * simulate a vertical collision.
     */
    public void flipVelOnY() {
        this.flipByValue(new V2d(this.getVel().getX(), -this.getVel().getY()));
    }

    /**
     * 
     * simulate a orizontal collision.
     */
    public void flipVelOnX() {
        this.flipByValue(new V2d(-this.getVel().getX(), this.getVel().getY()));
    }

    /**
     * 
     * @param newValue : direction after collision
     */
    public void flipByValue(final V2d newValue) {
        this.setVel(newValue);
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
     * allows to update the physics component of the ball.
     */
    @Override
    public void updateInput(final InputController controller) {
        this.getInputComponent().update(this, controller);
    }

}
