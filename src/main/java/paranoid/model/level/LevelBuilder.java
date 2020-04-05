package paranoid.model.level;

import java.util.ArrayList;
import java.util.List;

import paranoid.model.entity.Brick;

public class LevelBuilder {

    private String levelName;
    private List<Brick> bricks; 

    public LevelBuilder() {
        this.bricks = new ArrayList<>();
        this.levelName = "noNameLevel";
    }

    /**
     * 
     * @return the built level
     */
    public Level build() {
        return new Level(bricks, levelName);
    }

    /**
     * 
     * @param brick add ONE brick to the level
     * @return use pattern builder
     */
    public LevelBuilder addBrick(final Brick brick) {
        this.bricks.add(brick);
        return this;
    }

    /**
     * 
     * @param bricks add ONE brick to the level
     * @return use pattern builder
     */
    public LevelBuilder addBricks(final List<Brick> bricks) {
        this.bricks.addAll(bricks);
        return this;
    }

    /**
     * 
     * @param brick to remove
     * @return use pattern builder
     */
    public LevelBuilder removeBrick(final Brick brick) {
        this.bricks.remove(brick);
        return this;
    }

    /**
     * 
     * @param bricks to remove
     * @return use pattern builder
     */
    public LevelBuilder removeBricks(final List<Brick> bricks) {
        this.bricks.removeAll(bricks);
        return this;
    }

    /**
     * 
     * @param levelName 
     * @return use pattern builder
     */
    public LevelBuilder setLevelName(final String levelName) {
        this.levelName = levelName;
        return this;
    }
}
