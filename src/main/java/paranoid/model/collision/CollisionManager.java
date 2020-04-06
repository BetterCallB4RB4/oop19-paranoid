package paranoid.model.collision;

import java.util.Optional;

import paranoid.common.Collision;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.Brick;
import paranoid.model.entity.GameObject;

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
     * @param entity the object that can collide with the brick
     * @return the collision surface
     */
    public Optional<Collision> checkCollisionWithBricks(final Brick brick, final GameObject entity) {
        final boolean checkLeft = checkLeftForCollision(entity.getPos().getX(), brick.getPos().getX() + brick.getWidth());
        final boolean checkRight = checkRightForCollision(entity.getPos().getX() + entity.getWidth(), brick.getPos().getX());
        final boolean checkTop = checkTopForCollision(entity.getPos().getY(), brick.getPos().getY() + brick.getHeight());
        final boolean checkBottom = checkBottomForCollision(entity.getPos().getY() + entity.getHeight(), brick.getPos().getY());
        final Ball ball = (Ball) entity;


        if (!checkLeft) {
            brick.getLastZonePresence().put(ball, Collision.RIGHT);
        } else if (!checkRight) {
            brick.getLastZonePresence().put(ball, Collision.LEFT);
        } else if (!checkTop) {
            brick.getLastZonePresence().put(ball, Collision.BOTTOM);
        } else if (!checkBottom) {
            brick.getLastZonePresence().put(ball, Collision.TOP);
        } else {
            return Optional.of(brick.getLastZonePresence().get(ball));
        }

        return Optional.empty();
    }

    private boolean checkLeftForCollision(final double posEntity, final double posBrick) {
        return posEntity < posBrick;
    }

    private boolean checkRightForCollision(final double posEntity, final double posBrick) {
        return posEntity > posBrick;
    }

    private boolean checkTopForCollision(final double posEntity, final double posBrick) {
        return posEntity < posBrick;
    }

    private boolean checkBottomForCollision(final double posEntity, final double posBrick) {
        return posEntity > posBrick;
    }
}

