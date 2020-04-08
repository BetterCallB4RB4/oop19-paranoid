package paranoid.model.score;

import java.util.List;

import paranoid.common.Pair;

import java.util.ArrayList;
import java.util.Collections;


public class Score {

    /**
     * Number of element that you want show in Score GUI.
     */
    public static final int MAX_SCORE = 5;

    private final List<Pair<String, Integer>> scoreList;

    private Score(final List<Pair<String, Integer>> scoreList) {
        this.scoreList = scoreList;
    }

    public List<Pair<String, Integer>> getScore() {
        return this.scoreList;
    }

    public int getHighest() {
        return this.scoreList.get(0).getY();
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

        public void addScore(final String name, final Integer score) {
            this.scoreList.add(new Pair<>(name, score));
        }

        public Score build() {
            if (this.scoreList == null) {
                throw new IllegalStateException();
            }

            Collections.sort(scoreList, java.util.Comparator.comparing(Pair::getY));
            Collections.reverse(scoreList);
            while (scoreList.size() > MAX_SCORE) {
                scoreList.remove(scoreList.size() - 1);
            }
            return new Score(this.scoreList);

        }

    }
}
