package paranoid.model.entity;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.component.graphics.BallGraphicsComponent;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.input.DummyInputComponent;
import paranoid.model.component.input.InputController;
import paranoid.model.component.physics.BallPhysicsComponent;


public class Ball extends GameObj {

    private static final long serialVersionUID = 9139431443544241120L;

    public Ball(final P2d pos, final V2d vel, final double agility, final int height, final int width) {
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
     * simulate a orizontal collision.
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

}
