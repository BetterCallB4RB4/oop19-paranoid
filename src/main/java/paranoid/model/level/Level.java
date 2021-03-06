package paranoid.model.level;

import java.io.Serializable;
import java.util.Set;
import paranoid.model.entity.Brick;

public class Level implements Serializable {

    private static final long serialVersionUID = -3269378075735300995L;
    private final Set<Brick> bricks;
    private final String levelName;
    private final Music music;
    private final BackGround backGround;

    public Level(final Set<Brick> bricks, final String levelName, final Music music, final BackGround backGround) {
        this.bricks = bricks;
        this.levelName = levelName;
        this.music = music;
        this.backGround = backGround;
    }

    /**
     * 
     * @return the list of brick that are in this level
     */
    public Set<Brick> getBricks() {
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
    public Music getMusic() {
        return music;
    }

    /**
     * @return the backGround
     */
    public BackGround getBackGround() {
        return backGround;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((levelName == null) ? 0 : levelName.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Level other = (Level) obj;
        if (levelName == null) {
            if (other.levelName != null) {
                return false;
            }
        } else if (!levelName.equals(other.levelName)) {
            return false;
        }
        return true;
    }

}
