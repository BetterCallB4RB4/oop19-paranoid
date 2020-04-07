package paranoid.model.level;

import java.io.Serializable;
import java.util.List;
import paranoid.model.entity.Brick;

public class Level implements Serializable {

    private static final long serialVersionUID = -3269378075735300995L;
    private List<Brick> bricks;
    private String levelName;
    private String music;
    private String backGround;

    public Level(final List<Brick> bricks, final String levelName, final String music, final String backGround) {
        this.bricks = bricks;
        this.levelName = levelName;
        this.music = music;
        this.backGround = backGround;
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

    /**
     * @return the music
     */
    public String getMusic() {
        return music;
    }

    /**
     * @return the backGround
     */
    public String getBackGround() {
        return backGround;
    }

}
