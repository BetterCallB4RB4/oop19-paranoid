package paranoid.model.score;

import paranoid.main.ParanoidApp;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Score is the class that allow to store the best score earned by user in
 * all kind of level.
 */
public final class Score implements Serializable {

    private static final long serialVersionUID = 6171113929805935910L;

    private final List<User> scoreList;
    private final String nameScore;

    /**
     * Constructor.
     * @param scoreList the list that contain the best scores earned by users
     * in specific level.
     * @param nameScore the name of the score, equal to the name of the level played 
     * or "story" if it is a story level.
     */
    private Score(final List<User> scoreList, final String nameScore) {
        this.scoreList = scoreList;
        this.nameScore = nameScore;
    }

    /**
     * @return the user list of the selected score.
     */
    public List<User> getScoreList() {
        return this.scoreList;
    }

    /**
     * @return the name of selected score.
     */
    public String getNameScore() {
        return this.nameScore;
    }

    /**
     * @return the highest score value earned by the user, or 0 if the list is empty.
     */
    public Integer getHighValue() {
        return this.scoreList.isEmpty() ? 0 : this.scoreList.get(0).getScore();
    }

    /**
     * Static nested class for build the score.
     */
    public static final class Builder {

        private List<User> scoreList;
        private String nameScore;

        /**
         * Used to create a default score (empty).
         * @param nameScore the name that you choose for save the score.
         * @return the score builder.
         */
        public Builder defaultScore(final String nameScore) {
            this.scoreList = new LinkedList<>();
            this.nameScore = nameScore;
            return this;
        }

        /**
         * Used to update an existing score.
         * @param score the score that you want update.
         * @return the score builder.
         */
        public Builder fromExistScore(final Score score) {
            this.scoreList = score.getScoreList();
            this.nameScore = score.getNameScore();
            return this;
        }

        /**
         * Add a new user in a default or existing score.
         * @param user the user to add.
         * @return the score builder.
         */
        public Builder addUserScore(final User user) {
            this.scoreList.add(user);
            return this;
        }

        /**
         * Create the new score with the builder, with a descending order from
         * top value score to low value score. The elements of the list that
         * exceed the score_max_elem static param in ParanoidApp will be deleted.
         * @return the new Score.
         */
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
