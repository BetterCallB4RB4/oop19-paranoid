package paranoid.model.level;

import java.util.ArrayList;

import java.util.List;

import paranoid.model.entity.Brick;

public class Level {

    //TODO add music URI
    //TODO add backGround URI
    private List<Brick> bricks = new ArrayList<>();
    private String levelName;

    public Level(final List<Brick> bricks, final String levelName) {
        this.bricks = bricks;
        this.levelName = levelName;
    }

    /**
     * 
     * @return the list of brick that are in this level
     */
    public List<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * 
     * @return the name of the level
     */
    public String getLevelName() {
        return this.levelName;
    }

}
