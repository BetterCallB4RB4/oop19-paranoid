package paranoid.model.level;

import java.io.Serializable;
import java.util.List;
import paranoid.model.entity.Brick;

public class Level implements Serializable {

    //TODO add music URI
    //TODO add backGround URI
    private static final long serialVersionUID = -3269378075735300995L;
    private List<Brick> bricks;
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
