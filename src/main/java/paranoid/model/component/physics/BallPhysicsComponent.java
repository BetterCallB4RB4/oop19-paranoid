package paranoid.model.component.physics;

import java.util.Optional;

import paranoid.common.Collision;
import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.controller.event.HitBorderEvent;
import paranoid.model.entity.Ball;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.World;

public class BallPhysicsComponent implements PhysicsComponent {

    /**
     * ball asks the world if collisions have occurred.
     * report events
     * changes the physical state of the ball
     */
    @Override
    public void update(final int dt, final GameObject gameObj, final World w) {
        Ball ball = (Ball) gameObj;
        P2d old = gameObj.getPos(); //primitive method
        P2d pos = ball.getPos();
        V2d vel = ball.getVel();
        ball.setPos(pos.sum(vel.mul(SCALER * dt * ball.getAgility())));

        Optional<Collision> borderCollisionInfo = w.checkCollisionWithBoundaries(ball);
        if (borderCollisionInfo.isPresent()) {
            gameObj.setPos(old);
            w.notifyEvent(new HitBorderEvent());
            if (borderCollisionInfo.get().equals(Collision.TOP)
            ||  borderCollisionInfo.get().equals(Collision.BOTTOM)) {
                ball.flipVelOnY();
            } else {
                ball.flipVelOnX();
            }
        }
    }


}
