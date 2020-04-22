package paranoid.model.score;

import paranoid.main.ParanoidApp;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Score.
 */
public final class Score implements Serializable {

    private static final long serialVersionUID = 6171113929805935910L;

    private final List<User> scoreList;
    private final String nameScore;

    private Score(final List<User> scoreList, final String nameScore) {
        this.scoreList = scoreList;
        this.nameScore = nameScore;
    }

    public List<User> getScoreList() {
        return this.scoreList;
    }

    public String getNameScore() {
        return this.nameScore;
    }

    public Integer getHighValue() {
        return this.scoreList.isEmpty() ? 0 : this.scoreList.get(0).getScore();
    }

    public static final class Builder {

        private List<User> scoreList;
        private String nameScore;

        public Builder defaultScore(final String nameScore) {
            this.scoreList = new LinkedList<>();
            this.nameScore = nameScore;
            return this;
        }

        public Builder fromExistScore(final Score score) {
            this.scoreList = score.getScoreList();
            this.nameScore = score.getNameScore();
            return this;
        }

        public Builder addUserScore(final User user) {
            if (this.scoreList == null) {
                throw new IllegalStateException();
            }
            this.scoreList.add(user);
            return this;
        }

        public Score build() {
            if (this.scoreList == null || this.nameScore == null) {
                throw new IllegalStateException();
            }
            checkScoreFull();
            return new Score(this.scoreList, this.nameScore);

        }

        private void checkScoreFull() {
            if (!this.nameScore.isEmpty()) {
                sortScore();
                while (this.scoreList.size() > ParanoidApp.SCORE_MAX_ELEM) {
                    this.scoreList.remove(this.scoreList.size() - 1);
                }
            }
        }

        private void sortScore() {
            Collections.sort(this.scoreList, (o1, o2) -> o1.getScore().equals(o2.getScore()) 
                    ? o1.getDate().compareTo(o2.getDate()) 
                            : o2.getScore() - o1.getScore());
        }

    }
}
