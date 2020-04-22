package paranoid.model.collision;

import java.util.Optional;

import paranoid.common.Pair;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.Brick;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;

public interface CollisionManager {

    /**
     * check for collisions between the ball and the edge.
     * @param border the limit of the world
     * @param entity the object that can collide with the border
     * @return the collision surface
     */
    Optional<Collision> checkCollisionWithBoundaries(Border border, GameObject entity);

    /**
     * check for collisions between the ball and the bricks.
     * @param brick the brick to check the collision
     * @param ball the object that can collide with the brick
     * @return the collision surface
     */
    Optional<Pair<Brick, Collision>> checkCollisionWithBricks(Brick brick, Ball ball);

    /**
     * check for collisions with the player.
     * @param player
     * @param ball
     * @return the wall where the ball touches the player 
     * and if it is the upper one the direction also returns.
     */
    Optional<Collision> checkCollisionWithPlayers(Player player, Ball ball);

}
