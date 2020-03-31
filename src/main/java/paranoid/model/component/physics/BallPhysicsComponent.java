package paranoid.model.component.physics;

import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.model.entity.Ball;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.World;

public class BallPhysicsComponent implements PhysicsComponent {

    private static double scaler = 0.001;
    /**
     * ball asks the world if collisions have occurred.
     * report events
     * changes the physical state of the ball
     */
    @Override
    public void update(final int dt, final GameObject gameObj, final World w) {
        Ball ball = (Ball) gameObj;
        P2d pos = ball.getPos();
        V2d vel = ball.getVel();
        ball.setPos(pos.sum(vel.mul(scaler * dt)));
    }

}
