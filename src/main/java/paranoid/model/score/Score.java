package paranoid.model.score;

import java.util.List;

import paranoid.common.Pair;

import java.util.ArrayList;
import java.util.Collections;


public class Score {


    private final List<Pair<String, Integer>> scoreList;

    private Score(final List<Pair<String, Integer>> scoreList) {
        this.scoreList = scoreList;
    }

    public List<Pair<String, Integer>> getScore() {
        return this.scoreList;
    }

    public int getHightScore() {
        if (this.scoreList.size() > 0) {
            return this.scoreList.get(0).getY();
        }
        return 0;

    }

    public void addScore(final String name, final Integer score) {
        this.scoreList.add(new Pair<>(name, score));
    }

    public static final class Builder {

        private List<Pair<String, Integer>> scoreList = new ArrayList<>();

        public Builder defaultScore() {
            this.scoreList.add(new Pair<>("TIMMY", 50000));
            this.scoreList.add(new Pair<>("BILLY", 40000));
            this.scoreList.add(new Pair<>("WILLY", 30000));
            this.scoreList.add(new Pair<>("TOMMY", 20000));
            this.scoreList.add(new Pair<>("JIMMI", 10000));
            return this;
        }

        public Builder fromExScore(final List<Pair<String, Integer>> scoreList) {
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
