package paranoid.model.score;

import java.util.List;

import paranoid.common.Pair;

import java.util.ArrayList;
import java.util.Collections;


public class Score {


    private final List<User> scoreList;

    private Score(final List<User> scoreList) {
        this.scoreList = scoreList;
    }

    public List<User> getScore() {
        return this.scoreList;
    }

    public Integer getHightScore() {
        if (this.scoreList.size() > 0) {
            return this.scoreList.get(0).getScore();
        }
        return 0;

    }

    public void addScore(final String name, final Integer score) {
        this.scoreList.add(new User(name, score));
    }

    public static final class Builder {

        private List<User> scoreList = new ArrayList<>();

        public Builder defaultScore() {
            scoreList.add(new User("mario", 10000));
            return this;
        }

        public Builder fromExScore(final List<User> scoreList) {
            this.scoreList = scoreList;
            return this;
        }

        public Score build() {
            if (this.scoreList == null) {
                throw new IllegalStateException();
            }

            return new Score(this.scoreList);

        }

    }
}
