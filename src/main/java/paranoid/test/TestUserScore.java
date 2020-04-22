package paranoid.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paranoid.controller.gameloop.GameState;
import paranoid.model.score.Score;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;

public class TestUserScore {
    private User user;
    private GameState gameState;

    @BeforeEach
    public void creationScore() {
        UserManager.saveUser(new User());
        this.gameState = new GameState();
        this.user = gameState.getUser();
    }

    @Test
    public void testCreation() {
        assertEquals(4, this.user.getLives());
        assertEquals(0, this.user.getScore());
        assertThrows(IllegalStateException.class, () -> new Score.Builder()
                                                            .build());
        assertThrows(IllegalStateException.class, () -> new Score.Builder()
                                                            .addUserScore(new User())
                                                            .build());
        assertEquals(Collections.EMPTY_LIST, new Score.Builder()
                                                .defaultScore("story")
                                                  .build()
                                                  .getScoreList());
        this.gameState.addPoint(2000);
        assertEquals(2000, this.user.getScore());

        final Score.Builder scoreBuilder = new Score.Builder().defaultScore("story");
        for (int x = 1; x <= 10; x++) {
            final User newUser = new User();
            newUser.setScore(1000 * x);
            scoreBuilder.addUserScore(newUser);
        }
        final Score score = scoreBuilder.build();
        assertEquals(1000, score.getScoreList().get(score.getScoreList().size() - 1).getScore());
        assertEquals(10000, score.getHighValue());
        assertEquals(score.getHighValue(), score.getScoreList().get(0).getScore());

        for (int x = 1; x <= 100; x++) {
            scoreBuilder.addUserScore(new User());
        }
        final Score maxScore = scoreBuilder.build();
        assertEquals(10, maxScore.getScoreList().size());
    }
}
