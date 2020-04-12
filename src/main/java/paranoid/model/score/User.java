package paranoid.model.score;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

    private static final long serialVersionUID = 7908156953699582799L;

    private final String name;
    private final Integer score;
    private final Date date;

    public User(final String name, final Integer score) {
        this.name = name;
        this.score = score;
        this.date = new Date();
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        return this.score;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((score == null) ? 0 : score.hashCode());
        return result;
    }

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
        User other = (User) obj;
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
