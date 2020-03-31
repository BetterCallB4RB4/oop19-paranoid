package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;

public class Ball extends GameObj {

    private boolean isMoving;

    public Ball(final P2d pos, final V2d vel, final int height, final int width) {
        super(pos, vel, height, width);
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
    public boolean getBallMotion() {
        return this.isMoving;
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
     * simulate a orizontal collision.
     */
    public void flipVelOnX() {
        this.setVel(new V2d(-this.getVel().getX(), this.getVel().getY()));
    }

    /**
     * 
     * @param newValue : direction after collision
     */
    public void flipByValue(final V2d newValue) {
        this.setVel(newValue);
    }

}
