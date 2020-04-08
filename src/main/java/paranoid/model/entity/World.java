package paranoid.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import paranoid.common.Collision;
import paranoid.common.Pair;
import paranoid.common.PlayerId;
import paranoid.controller.event.Event;
import paranoid.controller.event.EventConsumer;
import paranoid.controller.event.WorldEventListener;
import paranoid.controller.gameLoop.GameState;
import paranoid.model.collision.CollisionManager;
import paranoid.model.component.input.InputController;

public class World implements WorldEventListener {

    private List<Ball> balls;
    private List<Brick> bricks;
    private List<Player> players;
    private Border border;
    private CollisionManager collisionManager;
    private EventConsumer eventHandler;

    public World(final Border border, final GameState gameState) {
        this.border = border;
        this.balls = new ArrayList<>();
        this.bricks = new ArrayList<>();
        this.players = new ArrayList<>();
        this.collisionManager = new CollisionManager();
        this.eventHandler = new EventConsumer(gameState);
    }

    /**
     * 
     * @param balls added int eh world
     */
    public void setBalls(final List<Ball> balls) {
        this.balls = balls;
    }

    /**
     * 
     * @param players added int the world
     */
    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    /**
     * 
     * @param bricks added in the world
     */
    public void setBricks(final List<Brick> bricks) {
        this.bricks = bricks;
    }

    /**
     * the world asks the collision manager to check 
     * if there are collisions border and given objects.
     * @param entity the object to be checked
     * @return on what surface the object collides
     */
    public Optional<Collision> checkCollisionWithBoundaries(final GameObject entity) {
        return this.collisionManager.checkCollisionWithBoundaries(border, entity);
    }

    /**
     * the world asks the collision manager to check 
     * if there are collisions with bricks and given objects.
     * @param entity the object to be checked
     * @return on what surface the object collides
     */

    public Optional<Pair<GameObject, Collision>> checkCollisionWithEntity(final GameObject entity) {
        Optional<Pair<GameObject, Collision>> collisionResult = Optional.empty();
        if (entity instanceof Ball) {
            final Ball ball = (Ball) entity;
            for (GameObject gameObj : getSceneEntities()) {
                if (gameObj instanceof Player) {
                    collisionResult = this.collisionManager.checkCollisionWithPlayers((Player) gameObj, ball);
                } else if (gameObj instanceof Brick) {
                    collisionResult = this.collisionManager.checkCollisionWithBricks((Brick) gameObj, ball);
                }

                if (collisionResult.isPresent()) {
                    return collisionResult;
                }

            }
        }
        return Optional.empty();

    }

    /**
     * 
     * @return a list of all the gameObj in the world
     */
    public List<GameObject> getSceneEntities() {
        List<GameObject> entities = new ArrayList<>();
        entities.addAll(this.balls);
        entities.addAll(this.bricks);
        entities.addAll(this.players);
        return Collections.unmodifiableList(entities);
    }

    /**
     * 
     * @param dt the time difference delta time
     */
    public void updateState(final int dt) {
        this.getSceneEntities().forEach(i -> i.updatePhysics(dt, this));
    }

    /**
     * 
     * @return the border of the world
     */
    public Border getBorder() {
        return this.border;
    }

    /**
     * add events to the queue that will be resolved with each iteration of gameLoop.
     */
    @Override
    public void notifyEvent(final Event ev) {
        this.eventHandler.addEvent(ev);
    }

    /**
     * 
     * @return the eventHandler
     */
    public EventConsumer getEventHanlder() {
        return this.eventHandler;
    }

    /**
     * Update player input component.
     * @param playerId each player has is own id
     * @param inputController controller that check the key pressed by user input device
     */
    public void movePlayer(final PlayerId playerId, final InputController inputController) {
        this.players.stream().filter(p -> p.getPlayerId().equals(playerId)).forEach(p -> p.updateInput(inputController));
    }
}
