package paranoid.model.score;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;



public class Score implements Serializable{

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
            this.scoreList = new ArrayList<>();
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

        public Builder removeUserScore(final User user) {
            this.scoreList.remove(user);
            return this;
        }

        public Score build() {
            if (this.scoreList == null || this.nameScore == null) {
                throw new IllegalStateException();
            }

            sortScore();

            while (this.scoreList.size() > 10) {
                this.scoreList.remove(this.scoreList.size() - 1);
            }

            return new Score(this.scoreList, this.nameScore);

        }

        private void sortScore() {
            Collections.sort(this.scoreList, (o1, o2) -> o1.getScore().equals(o2.getScore()) 
                    ? o1.getDate().compareTo(o2.getDate()) 
                            : o2.getScore() - o1.getScore());
        }

    }
}
