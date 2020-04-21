package paranoid.model.score;

import paranoid.main.ParanoidApp;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;


/**
 * Score.
 */
public final class Score implements Serializable {

    private static final long serialVersionUID = 6171113929805935910L;

    private final LinkedList<User> scoreList;
    private final String nameScore;

    private Score(final LinkedList<User> scoreList, final String nameScore) {
        this.scoreList = scoreList;
        this.nameScore = nameScore;
    }

    public LinkedList<User> getScoreList() {
        return this.scoreList;
    }

    public String getNameScore() {
        return this.nameScore;
    }

    public Integer getHighValue() {
        return this.scoreList.isEmpty() ? 0 : this.scoreList.peekFirst().getScore();
    }

    public static final class Builder {

        private LinkedList<User> scoreList;
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
                    this.scoreList.pollLast();
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
