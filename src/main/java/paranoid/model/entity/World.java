package paranoid.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import paranoid.common.Collision;
import paranoid.model.collision.CollisionManager;

public class World {

    private List<Ball> balls;
    private Border border;
    private CollisionManager collisionManager;

    public World(final List<Ball> balls, final Border border) {
        this.balls = balls;
        this.border = border;
        this.collisionManager = new CollisionManager();
    }

    /**
     * the world asks the collision manager to check 
     * if there are collisions border and given objects.
     * @param entity the object to be checked
     * @return on what surface the object collides
     */
    public Optional<Collision> checkCollisionWithBoundaries(final GameObj entity) {
        return this.collisionManager.checkCollisionWithBoundaries(border, entity);
    }

    /**
     * 
     * @return a list of all the gameObj in the world
     */
    public List<GameObj> getSceneEntities() {
        List<GameObj> entities = new ArrayList<>();
        entities.addAll(this.balls);
        return entities;
    }

    /**
     * 
     * @return the border of the world
     */
    public Border getBorder() {
        return this.border;
    }
}
