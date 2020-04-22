package paranoid.model.entity;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import paranoid.common.Pair;
import paranoid.common.PlayerId;
import paranoid.controller.event.Event;
import paranoid.controller.event.EventConsumer;
import paranoid.model.collision.Collision;
import paranoid.model.collision.Direction;
import paranoid.model.component.input.InputController;

public interface World {

    /**
     * 
     * @param balls to set to the world
     */
    void setBalls(Collection<Ball> balls);

    /**
     * 
     * @param ball to add to the world.
     */
    void addBall(Ball ball);

    /**
     * 
     * @param players to add to the world
     */
    void setPlayers(Collection<Player> players);

    /**
     * 
     * @param bricks added in the world
     */
    void setBricks(Collection<Brick> bricks);

    /**
     * 
     * @return all the balls in the game
     */
    Set<Ball> getBalls();

    /**
     * 
     * @return all the bricks in the world
     */
    Set<Brick> getBricks();

    /**
     * 
     * @return the border of the world
     */
    Border getBorder();

    /**
     * 
     * @param ball to remove
     */
    void removeBall(Ball ball);

    /**
     * 
     * @param brick to remove
     */
    void removeBrick(Brick brick);

    /**
     * the world asks the collision manager to check 
     * if there are collisions border and given objects.
     * @param entity the object to be checked
     * @return on what surface the object collides
     */
    Optional<Collision> checkCollisionWithBoundaries(GameObject entity);

    /**
     * the collision manager is asked if collisions 
     * have occurred between the ball and the bricks.
     * @param ball object that can collide
     * @return the result of collision
     */
    Optional<Pair<Brick, Collision>> checkCollisionWithBrick(Ball ball);

    /**
     * the collision manager is asked if collisions 
     * have occurred between the ball and the player.
     * @param ball object that can collide
     * @return if a collision has occurred in the upper part of the player, 
     * the direction the ball will take is also calculated. 
     */
    Pair<Optional<Collision>, Optional<Direction>> checkCollisionWithPlayer(Ball ball);

    /**
     * 
     * @return a list of all the gameObj in the world
     */
    Set<GameObject> getSceneEntities();

    /**
     * 
     * @param dt the time difference delta time
     */
    void updateState(int dt);

    /**
     * Update player input component.
     * @param playerId each player has is own id
     * @param inputController controller that check the key pressed by user input device
     */
    void movePlayer(PlayerId playerId, InputController inputController);

    /**
     * add events to the queue that will be resolved with each iteration of gameLoop.
     * @param ev the event generated
     */
    void notifyEvent(Event ev);

    /**
     * 
     * @return the eventHandler 
     */
    EventConsumer getEventHanlder();

}
