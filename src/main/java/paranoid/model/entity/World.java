package paranoid.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import paranoid.common.Collision;
import paranoid.controller.event.Event;
import paranoid.controller.event.EventConsumer;
import paranoid.controller.event.WorldEventListener;
import paranoid.model.collision.CollisionManager;
import paranoid.model.component.input.InputController;

public class World implements WorldEventListener {

    private List<Ball> balls;
    private List<Brick> bricks;
    private List<Player> players;
    private Border border;
    private CollisionManager collisionManager;
    private EventConsumer eventHandler;

    public World(final List<Ball> balls, final List<Brick> bricks, final List<Player> players, final Border border) {
        this.balls = balls;
        this.bricks = bricks;
        this.players = players;
        this.border = border;
        this.collisionManager = new CollisionManager();
        this.eventHandler = new EventConsumer();
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
     * @param inputController controller that check the key pressed by user input device
     */
    public void movePlayer(final InputController inputController) {
        this.players.forEach(p -> p.updateInput(inputController));
    }
}
