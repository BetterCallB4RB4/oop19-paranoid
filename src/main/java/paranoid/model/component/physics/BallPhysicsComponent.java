package paranoid.model.component.physics;

import java.util.Optional;

import paranoid.common.Collision;
import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.controller.event.HitBorderEvent;
import paranoid.controller.event.HitBrickEvent;
import paranoid.model.entity.Ball;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;
import paranoid.model.entity.World;

public class BallPhysicsComponent implements PhysicsComponent {
    private long timeBrickCol = System.currentTimeMillis();
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

        final Optional<Collision> borderCollisionInfo = w.checkCollisionWithBoundaries(ball);

        if (borderCollisionInfo.isPresent()) {
            ball.setPos(old);
            w.notifyEvent(new HitBorderEvent());
            if (borderCollisionInfo.get().equals(Collision.TOP)
            ||  borderCollisionInfo.get().equals(Collision.BOTTOM)) {
                ball.flipVelOnY();
            } else {
                ball.flipVelOnX();
            }
        }

        final long minTimeCol = dt + 5;

        if ((System.currentTimeMillis() - timeBrickCol) > minTimeCol) {
            final Optional<Collision> brickCollisionInfo = w.checkCollisionWithBricks(ball);

            if (brickCollisionInfo.isPresent()) {
                this.timeBrickCol = System.currentTimeMillis();
                ball.setPos(old);
                w.notifyEvent(new HitBrickEvent());

            if (brickCollisionInfo.get().equals(Collision.TOP)
                    || brickCollisionInfo.get().equals(Collision.BOTTOM)) {
                ball.flipVelOnY();
            } else {
                ball.flipVelOnX();
            }
        }

        }

        final Optional<Pair<Player, Collision>> playerCollisionInfo = w.checkCollisionWithPlayer(ball);

        if (playerCollisionInfo.isPresent()) {
            final Player player = playerCollisionInfo.get().getX();
            final Collision collision = playerCollisionInfo.get().getY();

            ball.setPos(new P2d(ball.getPos().getX(), player.getPos().getY() - ball.getHeight()));
            if (collision.equals(Collision.LEFT)) {
                ball.setVel(new V2d(-100, -200));
            } else if (collision.equals(Collision.RIGHT)) {
                ball.setVel(new V2d(100, -200));
            }
        }
    }


}
