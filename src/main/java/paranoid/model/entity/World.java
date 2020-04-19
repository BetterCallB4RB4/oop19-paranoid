package paranoid.model.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import paranoid.common.Collision;
import paranoid.common.Pair;
import paranoid.common.PlayerId;
import paranoid.controller.event.Event;
import paranoid.controller.event.EventConsumer;
import paranoid.controller.event.WorldEventListener;
import paranoid.controller.gameLoop.GameState;
import paranoid.model.collision.CollisionManager;
import paranoid.model.collision.Direction;
import paranoid.model.component.input.InputController;

public class World implements WorldEventListener {

    private final Set<Ball> balls;
    private final Set<Brick> bricks;
    private final Set<Player> players;
    private final Border border;
    private final EventConsumer eventHandler;
    private final CollisionManager collisionManager;

    public World(final Border border, final GameState gameState) {
        this.border = border;
        this.balls = new HashSet<>();
        this.bricks = new HashSet<>();
        this.players = new HashSet<>();
        this.collisionManager = new CollisionManager();
        this.eventHandler = new EventConsumer(gameState);
    }

    /**
     * 
     * @param balls added int eh world
     */
    public void setBalls(final Collection<Ball> balls) {
        this.balls.clear();
        this.balls.addAll(balls);
    }

    /**
     * 
     * @param players added int the world
     */
    public void setPlayers(final Collection<Player> players) {
        this.players.clear();
        this.players.addAll(players);
    }

    /**
     * 
     * @param bricks added in the world
     */
    public void setBricks(final Collection<Brick> bricks) {
        this.bricks.clear();
        this.bricks.addAll(bricks);
    }

    /**
     * 
     * @return x
     */
    public Set<Ball> getBalls() {
        return Collections.unmodifiableSet(this.balls);
    }

    /**
     * 
     * @return x
     */
    public Set<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * 
     * @return the border of the world
     */
    public Border getBorder() {
        return this.border;
    }

    /**
     * 
     * @param ball
     */
    public void removeBall(final Ball ball) {
        this.balls.remove(ball);
    }

    /**
     * 
     * @param brick
     */
    public void removeBrick(final Brick brick) {
        this.bricks.remove(brick);
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
     * @param ball
     * @return dio 
     */
    public Optional<Pair<Brick, Collision>> checkCollisionWithBrick(final Ball ball) {
        Optional<Pair<Brick, Collision>> resultCollision = Optional.empty();
        for (var elem : this.bricks) {
            resultCollision = this.collisionManager.checkCollisionWithBricks(elem, ball);
            if (resultCollision.isPresent()) {
                return resultCollision;
            }
        }
        return resultCollision;
    }

    /**
     * 
     * @param ball
     * @return miao
     */
    public Pair<Optional<Collision>, Optional<Direction>> checkCollisionWithPlayer(final Ball ball) {
        Optional<Collision> resultCollision = Optional.empty();
        for (var elem : this.players) {
            resultCollision = this.collisionManager.checkCollisionWithPlayers(elem, ball);
            if (resultCollision.isPresent()) {
                Collision collisionType = resultCollision.get(); 
                if (collisionType.equals(Collision.TOP)) {
                    double centerBall = ball.getPos().getX() + (ball.getWidth() / 2);
                    double playerHitZone = elem.getWidth() / Direction.values().length;
                    for (int i = 0; i < Direction.values().length; i++) {
                        if (centerBall > elem.getPos().getX() + (i * playerHitZone)
                         && centerBall < elem.getPos().getX() + ((i + 1) * playerHitZone)) {
                            return new Pair<>(Optional.of(Collision.TOP), Optional.of(Direction.values()[i]));
                        }
                    }
                } else if (collisionType.equals(Collision.LEFT) || collisionType.equals(Collision.RIGHT)) {
                    return new Pair<>(resultCollision, Optional.empty());
                }
            }
        }
        return new Pair<>(Optional.empty(), Optional.empty());
    }

    /**
     * 
     * @return a list of all the gameObj in the world
     */
    public Set<GameObject> getSceneEntities() {
        Set<GameObject> entities = new HashSet<>();
        entities.addAll(this.balls);
        entities.addAll(this.bricks);
        entities.addAll(this.players);
        return Collections.unmodifiableSet(entities);
    }

    /**
     * 
     * @param dt the time difference delta time
     */
    public void updateState(final int dt) {
        this.getSceneEntities().forEach(i -> i.updatePhysics(dt, this));
    }

    /**
     * Update player input component.
     * @param playerId each player has is own id
     * @param inputController controller that check the key pressed by user input device
     */
    public void movePlayer(final PlayerId playerId, final InputController inputController) {
        this.players.stream()
                    .filter(p -> p.getPlayerId().equals(playerId))
                    .forEach(p -> p.updateInput(inputController));
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

}
