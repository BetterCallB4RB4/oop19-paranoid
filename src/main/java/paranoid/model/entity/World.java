package paranoid.model.entity;

import java.util.ArrayList;
import java.util.List;

public class World {

    private List<Ball> balls = new ArrayList<>();
    private Border border;

    public World(final List<Ball> balls, final Border border) {
        this.balls.addAll(balls);
        this.border = border;
    }

    /**
     * 
     * @return a list of all the gameObj in the world
     */
    public List<GameObj> getSceneEntities() {
        List<GameObj> entities = new ArrayList<GameObj>();
        entities.addAll(balls);
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
