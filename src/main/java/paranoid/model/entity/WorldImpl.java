package paranoid.model.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import paranoid.common.Pair;
import paranoid.common.PlayerId;
import paranoid.controller.event.Event;
import paranoid.controller.event.EventConsumer;
import paranoid.controller.gameloop.GameState;
import paranoid.model.collision.Collision;
import paranoid.model.collision.CollisionManager;
import paranoid.model.collision.CollisionManagerImpl;
import paranoid.model.collision.Direction;
import paranoid.model.component.input.InputController;

public class WorldImpl implements World {

    private final Set<Ball> balls;
    private final Set<Brick> bricks;
    private final Set<Player> players;
    private final Border border;
    private final EventConsumer eventHandler;
    private final CollisionManager collisionManager;

    public WorldImpl(final Border border, final GameState gameState) {
        this.border = border;
        this.balls = new HashSet<>();
        this.bricks = new HashSet<>();
        this.players = new HashSet<>();
        this.collisionManager = new CollisionManagerImpl();
        this.eventHandler = new EventConsumer(gameState);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setBalls(final Collection<Ball> balls) {
        this.balls.clear();
        this.balls.addAll(balls);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void addBall(final Ball ball) {
        this.balls.add(ball);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setPlayers(final Collection<Player> players) {
        this.players.clear();
        this.players.addAll(players);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setBricks(final Collection<Brick> bricks) {
        this.bricks.clear();
        this.bricks.addAll(bricks);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Set<Ball> getBalls() {
        return Collections.unmodifiableSet(this.balls);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Set<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Border getBorder() {
        return this.border;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void removeBall(final Ball ball) {
        this.balls.remove(ball);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void removeBrick(final Brick brick) {
        this.bricks.remove(brick);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Collision> checkCollisionWithBoundaries(final GameObject entity) {
        return this.collisionManager.checkCollisionWithBoundaries(border, entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<Brick, Collision>> checkCollisionWithBrick(final Ball ball) {
        Optional<Pair<Brick, Collision>> resultCollision = Optional.empty();
        for (final var elem : this.bricks) {
            resultCollision = this.collisionManager.checkCollisionWithBricks(elem, ball);
            if (resultCollision.isPresent()) {
                return resultCollision;
            }
        }
        return resultCollision;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public Pair<Optional<Collision>, Optional<Direction>> checkCollisionWithPlayer(final Ball ball) {
        Optional<Collision> resultCollision = Optional.empty();
        for (final var elem : this.players) {
            resultCollision = this.collisionManager.checkCollisionWithPlayers(elem, ball);
            if (resultCollision.isPresent()) {
                final Collision collisionType = resultCollision.get(); 
                if (collisionType.equals(Collision.TOP)) {
                    final double centerBall = ball.getPos().getX() + (ball.getWidth() / 2);
                    final double playerHitZone = elem.getWidth() / Direction.values().length;
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
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getSceneEntities() {
        final Set<GameObject> entities = new HashSet<>();
        entities.addAll(this.balls);
        entities.addAll(this.bricks);
        entities.addAll(this.players);
        return Collections.unmodifiableSet(entities);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void updateState(final int dt) {
        this.getSceneEntities().forEach(i -> i.updatePhysics(dt, this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final PlayerId playerId, final InputController inputController) {
        this.players.stream()
                    .filter(p -> p.getPlayerId().equals(playerId))
                    .forEach(p -> p.updateInput(inputController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final Event ev) {
        this.eventHandler.addEvent(ev);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public EventConsumer getEventHanlder() {
        return this.eventHandler;
    }

}
