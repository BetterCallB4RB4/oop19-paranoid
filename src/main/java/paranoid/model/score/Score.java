package paranoid.model.score;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;



public class Score implements Serializable{

    private static final long serialVersionUID = 6171113929805935910L;

    private final List<User> scoreList;
    private static final int MAX_ELEM = 4;
    private Score(final List<User> scoreList) {
        this.scoreList = scoreList;
    }

    public List<User> getScoreList() {
        return this.scoreList;
    }

    public Integer getHighValue() {
        if (this.scoreList.isEmpty()) {
            return 0;
        }
        return this.scoreList.get(0).getScore();

    }

    public static final class Builder {

        private List<User> scoreList;

        public Builder defaultScore() {
            this.scoreList = new ArrayList<>();
            return this;
        }

        public Builder fromExistScore(final Score score) {
            this.scoreList = score.getScoreList();
            return this;
        }

        public void addUserScore(final String name, final Integer score) {
            this.scoreList.add(new User(name, score));
        }

        public void removeUserScore(final User user) {
            this.scoreList.remove(user);
        }

        public Score build() {
            if (this.scoreList == null) {
                throw new IllegalStateException();
            }

            sortScore();

            while (this.scoreList.size() > MAX_ELEM) {
                this.scoreList.remove(this.scoreList.size() - 1);
            }

            return new Score(this.scoreList);

        }

        private void sortScore() {
            Collections.sort(this.scoreList, (o1, o2) -> o1.getScore().equals(o2.getScore()) 
                    ? o1.getDate().compareTo(o2.getDate()) 
                            : o2.getScore() - o1.getScore());
        }

    }
}
