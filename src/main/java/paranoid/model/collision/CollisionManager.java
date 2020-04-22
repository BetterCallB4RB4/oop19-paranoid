package paranoid.model.collision;

import java.util.Optional;

import paranoid.common.Collision;
import paranoid.common.Pair;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.Brick;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;

/**
 * collision implementation via the AABB (Axis-Aligned Bounding Box) algorithm.
 *
 */
public class CollisionManager {

    /**
     * check for collisions between the ball and the edge.
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
     * check for collisions between the ball and the bricks.
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
     * check for collisions with the player.
     * @param player
     * @param ball
     * @return the wall where the ball touches the player 
     * and if it is the upper one the direction also returns.
     */
    public Optional<Collision> checkCollisionWithPlayers(final Player player, final Ball ball) {
        final boolean checkTop = checkTopForCollision(ball.getPos().getY(), player.getPos().getY() + player.getHeight());
        final boolean checkBottom = checkBottomForCollision(ball.getPos().getY() + ball.getHeight(), player.getPos().getY());
        final boolean checkLeft = checkLeftForCollision(ball.getPos().getX(), player.getPos().getX() + player.getWidth());
        final boolean checkRight = checkRightForCollision(ball.getPos().getX() + ball.getWidth(), player.getPos().getX());
        if (!checkLeft) {
            player.getLastZonePresence().put(ball, Collision.RIGHT);
        } else if (!checkRight) {
            player.getLastZonePresence().put(ball, Collision.LEFT);
        } else if (!checkTop) {
            player.getLastZonePresence().put(ball, Collision.BOTTOM);
        } else if (!checkBottom) {
            player.getLastZonePresence().put(ball, Collision.TOP);
        } else {
            return Optional.of(player.getLastZonePresence().get(ball));
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

