package paranoid.model.score;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 7908156953699582799L;

    private final String name;
    private final Integer score;

    public User(final String name, final Integer score) {
        this.name = name;
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + score;
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
        User other = (User) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (score != other.score) {
            return false;
        }
        return true;
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        return this.score;
    }
}
