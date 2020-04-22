package paranoid.model.score;

import java.io.Serializable;
import java.util.Date;

/**
 * User. It store the information of the current user that play the game.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 7908156953699582799L;

    private String name;
    private Integer score;
    private Integer lives;
    private final Date date;

    /**
     * Constructor. It create an user with default name, score, lives and the date to compare
     * with other user that have the same score.
     */
    public User() {
        this.name = "player";
        this.score = 0;
        this.lives = 4;
        this.date = new Date();
    }

    /**
     * @return name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the score earned by the user.
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * @return the date of the creation of the user.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * 
     * @return the current lives of the user.
     */
    public Integer getLives() {
        return this.lives;
    }

    /**
     * @param name the name to set when you want save the user score in file.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param score the score to set.
     */
    public void setScore(final Integer score) {
        this.score = score;
    }

    /**
     * @param lives the lives to set.
     */
    public void setLives(final Integer lives) {
        this.lives = lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((score == null) ? 0 : score.hashCode());
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
        final User other = (User) obj;
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (score == null) {
            if (other.score != null) {
                return false;
            }
        } else if (!score.equals(other.score)) {
            return false;
        }
        return true;
    }
}
