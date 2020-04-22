package paranoid.controller.gameloop;

import paranoid.model.entity.World;
import paranoid.model.level.Level;
import paranoid.model.score.Score;
import paranoid.model.score.User;
import paranoid.model.settings.Difficulty;

public interface GameState {

    /**
     * set initial game state.
     */
    void init();

    /**
     * @return score values
     */
    int getPlayerScore();

    /**
     * @return the highscore.
     */
    int getHighScoreValue();

    /**
     * 
     * @param point to add to the total.
     */
    void addPoint(int point);

    /**
     * add the end game bonus to the total score.
     */
    void addBonus();

    /**
     * returns the multiplier to its initial state.
     */
    void flatMultiplier();

    /**
     * increase the multiplier.
     */
    void incMultiplier();

    /**
     * decreases the player's lives.
     */
    void decLives();

    /**
     * increases the player's lives.
     */
    void incLives();

    /**
     * 
     * @return current number of lives
     */
    int getLives();

    /**
     * @return the world
     */
    World getWorld();

    /**
     * 
     * @return the current game phase
     */
    GamePhase getPhase();

    /**
     * 
     * @param phase set the game phase
     */
    void setPhase(GamePhase phase);

    /**
     * 
     * @return the current user
     */
    User getUser();

    /**
     * 
     * @return the topScore of the user who is playing
     */
    Score getTopScores();

    /**
     * 
     * @return the level where the game is taking place
     */
    Level getLevel();

    /**
     * 
     * @return the difficulty of the level returns.
     */
    Difficulty getDifficulty();

    /**
     * 
     * @return if the music is active
     */
    boolean isMusicActive();

    /**
     * 
     * @return if the effects is active
     */
    boolean isEffectActive();

}
