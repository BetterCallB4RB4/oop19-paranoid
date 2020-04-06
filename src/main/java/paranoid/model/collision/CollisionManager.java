package paranoid.model.collision;

import java.util.Optional;

import paranoid.common.Collision;
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
        final boolean checkLeft = checkLeftForCollision(brick.getPos().getX(), entity.getPos().getX());
        final boolean checkRight = checkRightForCollision(brick.getPos().getX(), entity.getPos().getX());
        final boolean checkTop = checkTopForCollision(brick.getPos().getY(), entity.getPos().getY());
        final boolean checkBottom = checkBottomForCollision(brick.getPos().getY(), entity.getPos().getY());

        if (checkLeft && checkRight && checkTop && checkBottom) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    private boolean checkLeftForCollision(final double posBrick, final double posEntity) {
        return posBrick > posEntity;
    }

    private boolean checkRightForCollision(final double posBrick, final double posEntity) {
        return posBrick < posEntity;
    }

    private boolean checkTopForCollision(final double posBrick, final double posEntity) {
        return posBrick < posEntity;
    }

    private boolean checkBottomForCollision(final double posBrick, final double posEntity) {
        return posBrick > posEntity;
    }
}

