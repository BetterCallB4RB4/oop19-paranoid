package paranoid.model.component.physics;

import java.util.Optional;

import paranoid.common.Collision;
import paranoid.common.P2d;
import paranoid.common.Pair;
import paranoid.common.V2d;
import paranoid.controller.event.HitBorderEvent;
import paranoid.controller.event.HitBrickEvent;
import paranoid.model.collision.Direction;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Brick;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.World;

public class BallPhysicsComponent implements PhysicsComponent {

    /**
     * 
     */
    @Override
    public void update(final int dt, final GameObject gameObj, final World w) {
        Ball ball = (Ball) gameObj;
        P2d old = gameObj.getPos();
        P2d pos = ball.getPos();
        V2d vel = ball.getVel();
        ball.setPos(pos.sum(vel.mul(ball.getSpeed())));

        final Optional<Collision> borderCollisionInfo = w.checkCollisionWithBoundaries(ball);
        if (borderCollisionInfo.isPresent()) {
            ball.setPos(old);
            w.notifyEvent(new HitBorderEvent(ball, borderCollisionInfo.get()));
            if (borderCollisionInfo.get().equals(Collision.TOP)
            ||  borderCollisionInfo.get().equals(Collision.BOTTOM)) {
                ball.flipVelOnY();
            } else {
                ball.flipVelOnX();
            }
        }

        final Optional<Pair<Brick, Collision>> brickCollisionInfo = w.checkCollisionWithBrick(ball);
        if (brickCollisionInfo.isPresent()) {
            ball.setPos(old);
            w.notifyEvent(new HitBrickEvent(brickCollisionInfo.get().getX()));
            if (brickCollisionInfo.get().getY().equals(Collision.TOP)
            ||  brickCollisionInfo.get().getY().equals(Collision.BOTTOM)) {
                ball.flipVelOnY();
            } else {
                ball.flipVelOnX();
            }
        }

        final Optional<Pair<GameObject, Direction>> playerCollisionInfo = w.checkCollisionWithPlayer(ball);
        if (playerCollisionInfo.isPresent()) {
            ball.setPos(old);
            ball.setVel(playerCollisionInfo.get().getY().getVector());
            ball.flipVelOnY();
        }
    }
}
