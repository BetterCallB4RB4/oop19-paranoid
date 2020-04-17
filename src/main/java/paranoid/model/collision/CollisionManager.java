package paranoid.model.collision;

import java.util.Optional;

import paranoid.common.Collision;
import paranoid.common.Pair;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.Brick;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;

public class CollisionManager {

    /**
     * 
     * @param border the limit of the world
     * @param entity the object that can collide with the border
     * @return the collision surface
     */
    public Optional<Collision> checkCollisionWithBoundaries(final Border border, final GameObject entity) {
        if (entity.getPos().getY() + entity.getHeight() > border.getBottomRightCorner().getY()) {
            return Optional.of(Collision.BOTTOM);
        } else if (entity.getPos().getY() < border.getUpperleftCorner().getY()) {
            return Optional.of(Collision.TOP);
        } else if (entity.getPos().getX() < border.getUpperleftCorner().getX()) {
            return Optional.of(Collision.LEFT);
        } else if (entity.getPos().getX() + entity.getWidth() > border.getBottomRightCorner().getX()) {
            return Optional.of(Collision.RIGHT);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param brick the brick to check the collision
     * @param ball the object that can collide with the brick
     * @return the collision surface
     */
    public Optional<Pair<Brick, Collision>> checkCollisionWithBricks(final Brick brick, final Ball ball) {
        final boolean checkLeft = checkLeftForCollision(ball.getPos().getX(), brick.getPos().getX() + brick.getWidth());
        final boolean checkRight = checkRightForCollision(ball.getPos().getX() + ball.getWidth(), brick.getPos().getX());
        final boolean checkTop = checkTopForCollision(ball.getPos().getY(), brick.getPos().getY() + brick.getHeight());
        final boolean checkBottom = checkBottomForCollision(ball.getPos().getY() + ball.getHeight(), brick.getPos().getY());
        if (!checkLeft) {
            brick.getLastZonePresence().put(ball, Collision.RIGHT);
        } else if (!checkRight) {
            brick.getLastZonePresence().put(ball, Collision.LEFT);
        } else if (!checkTop) {
            brick.getLastZonePresence().put(ball, Collision.BOTTOM);
        } else if (!checkBottom) {
            brick.getLastZonePresence().put(ball, Collision.TOP);
        } else {
            return Optional.of(new Pair<>(brick, brick.getLastZonePresence().get(ball)));
        }

        return Optional.empty();

    }

    /**
     * 
     * @param player
     * @param ball
     * @return ememe
     */
    public Optional<Pair<GameObject, Direction>> checkCollisionWithPlayers(final Player player, final Ball ball) {
        final boolean checkTop = checkTopForCollision(ball.getPos().getY(), player.getPos().getY() + player.getHeight());
        final boolean checkBottom = checkBottomForCollision(ball.getPos().getY() + ball.getHeight(), player.getPos().getY());
        if (checkBottom && checkTop) {
            double centerBall = ball.getPos().getX() + (ball.getWidth() / 2);
            double playerHitZone = player.getWidth() / Direction.values().length;
            for (int i = 0; i < Direction.values().length; i++) {
                if (centerBall > player.getPos().getX() + (i * playerHitZone)
                && centerBall < player.getPos().getX() + ((i + 1) * playerHitZone)) {
                    return Optional.of(new Pair<>(player, Direction.values()[i]));
                }
            }
        }
        return Optional.empty();
    }

    private boolean checkLeftForCollision(final double posBall, final double posEntity) {
        return posBall < posEntity;
    }

    private boolean checkRightForCollision(final double posBall, final double posEntity) {
        return posBall > posEntity;
    }

    private boolean checkTopForCollision(final double posBall, final double posEntity) {
        return posBall < posEntity;
    }

    private boolean checkBottomForCollision(final double posBall, final double posEntity) {
        return posBall > posEntity;
    }
}

