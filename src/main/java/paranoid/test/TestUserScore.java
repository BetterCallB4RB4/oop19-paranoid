//package paranoid.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.util.Collections;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import paranoid.controller.gameloop.GameState;
//import paranoid.controller.gameloop.GameStateImpl;
//import paranoid.main.ParanoidApp;
//import paranoid.model.score.Score;
//import paranoid.model.score.User;
//import paranoid.model.score.UserManager;
//
///**
// * TestUserScore. Test the creation of the User and check if user is stored correctly
// * in the score list.
// */
//public class TestUserScore {
//    private User user;
//    private GameState gameState;
//
//    /**
//     * Initialize fields before the test start.
//     */
//    @BeforeEach
//    public void creationScore() {
//        UserManager.saveUser(new User());
//        this.gameState = new GameStateImpl();
//        this.user = gameState.getUser();
//    }
//
//    /**
//     * Test if default user set correctly attributes and test if point earned are
//     * stored correctly in the user.
//     */
//    @Test
//    public void testUserCreation() {
//        assertEquals(4, this.user.getLives());
//        assertEquals(0, this.user.getScore());
//        this.gameState.addPoint(2000);
//        assertEquals(2000, this.user.getScore());
//
//    }
//
//    /**
//     * Test if the builder create correctly the score and check if the score is
//     * sorted correctly from the top score value to the lowest.
//     * Test if score size is at most static parameter SCORE_MAX_ELEM in ParanoidApp
//     */
//    @Test
//    public void testScoreList() {
//        assertThrows(IllegalStateException.class, () -> new Score.Builder()
//                                                        .build());
//        assertThrows(IllegalStateException.class, () -> new Score.Builder()
//                                                        .addUserScore(new User())
//                                                        .build());
//        assertEquals(Collections.EMPTY_LIST, new Score.Builder()
//                                                      .defaultScore("story")
//                                                      .build()
//                                                      .getScoreList());
//
//        final Score.Builder scoreBuilder = new Score.Builder().defaultScore("story");
//        for (int x = 1; x <= 10; x++) {
//            final User newUser = new User();
//            newUser.setScore(1000 * x);
//            scoreBuilder.addUserScore(newUser);
//        }
//        final Score score = scoreBuilder.build();
//        assertEquals(1000, score.getScoreList().get(score.getScoreList().size() - 1).getScore());
//        assertEquals(10000, score.getHighValue());
//        assertEquals(score.getHighValue(), score.getScoreList().get(0).getScore());
//
//        for (int x = 1; x <= 100; x++) {
//            scoreBuilder.addUserScore(new User());
//        }
//        final Score maxScore = scoreBuilder.build();
//        assertEquals(ParanoidApp.SCORE_MAX_ELEM, maxScore.getScoreList().size());
//    }
//}
