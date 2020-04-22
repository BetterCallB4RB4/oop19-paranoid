package paranoid.controller.gameloop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import paranoid.common.PlayerId;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.collision.Direction;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.Player;
import paranoid.model.entity.StartPhase;
import paranoid.model.entity.World;
import paranoid.model.level.Level;
import paranoid.model.level.LevelSelection;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.Difficulty;
import paranoid.model.settings.Settings;
import paranoid.model.settings.SettingsManager;

public class GameState {

    private GamePhase phase;
    private final int highScoreValue;
    private int multiplier;
    private final Settings settings;
    private final Score scores;
    private final World world;
    private final Level level;
    private final User user;

    public GameState() {
        this.phase = GamePhase.INIT;
        this.user = UserManager.loadUser();
        this.settings = SettingsManager.loadOption();
        this.level = settings.getSelectedLevel();
        this.world = new World(new Border(ScreenConstant.WORLD_WIDTH, ScreenConstant.WORLD_HEIGHT), this);
        if (LevelSelection.isStoryLevel(level.getLevelName())) {
            this.scores = ScoreManager.loadStory();
        } else {
            this.scores = ScoreManager.loadCustom(level.getLevelName());
        }
        this.highScoreValue = this.scores.getHighValue();
        this.world.setBricks(level.getBricks());
        this.multiplier = 1;
    }

    /**
     * set initial game state.
     */
    public void init() {
        this.flatMultiplier();
        final Player.Builder playerBuilder = new Player.Builder();
        final List<Player> playerList = new ArrayList<>();
        playerList.add(playerBuilder.position(StartPhase.PLAYER_ONE.getSpawnPoint())
                .width(StartPhase.PLAYER_ONE.getInitWidth())
                .height(StartPhase.PLAYER_ONE.getInitHeight())
                .playerId(PlayerId.ONE)
                .build());

        if (settings.getPlayerNumber() == 2) {
            playerList.add(playerBuilder.position(StartPhase.PLAYER_TWO.getSpawnPoint())
                    .width(StartPhase.PLAYER_TWO.getInitWidth())
                    .height(StartPhase.PLAYER_TWO.getInitHeight())
                    .playerId(PlayerId.TWO)
                    .build());
        }
        this.world.setPlayers(playerList);
        this.world.setBalls(Arrays.asList(new Ball.Builder()
                                             .position(StartPhase.BALL.getSpawnPoint())
                                             .direction(Direction.EDGE_LEFT.getVector().mul(-1))
                                             .height(StartPhase.BALL.getInitHeight())
                                             .width(StartPhase.BALL.getInitWidth())
                                             .speed(settings.getDifficulty().getSpeed())
                                             .build()));
        this.phase = GamePhase.PAUSE;
    }

    /**
     * @return score values
     */
    public int getPlayerScore() {
        return user.getScore();
    }

    /**
     * @return the highscore.
     */
    public int getHighScoreValue() {
        return user.getScore() > this.highScoreValue ? user.getScore() : this.highScoreValue;
    }

    /**
     * 
     * @param point to add to the total.
     */
    public void addPoint(final int point) {
        user.setScore(user.getScore() + (point * multiplier));
    }

    /**
     * add the end game bonus to the total score.
     */
    public void addBonus() {
        user.setScore((user.getScore() + settings.getDifficulty().getGameOverBonus()) * this.getLives());
    }

    /**
     * returns the multiplier to its initial state.
     */
    public void flatMultiplier() {
        this.multiplier = this.settings.getDifficulty().getFlatMultiplier();
    }

    /**
     * increase the multiplier.
     */
    public void incMultiplier() {
        this.multiplier++;
    }

    /**
     * decreases the player's lives.
     */
    public void decLives() {
        user.setLives(user.getLives() - 1);
    }

    /**
     * increases the player's lives.
     */
    public void incLives() {
        user.setLives(user.getLives() + 1);
    }

    /**
     * 
     * @return current number of lives
     */
    public int getLives() {
        return user.getLives();
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * 
     * @return the current game phase
     */
    public GamePhase getPhase() {
        return this.phase;
    }

    /**
     * 
     * @param phase set the game phase
     */
    public void setPhase(final GamePhase phase) {
        this.phase = phase;
    }

    /**
     * 
     * @return the current user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * 
     * @return the topScore of the user who is playing
     */
    public Score getTopScores() {
        return this.scores;
    }

    /**
     * 
     * @return the level where the game is taking place
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * 
     * @return the difficulty of the level returns.
     */
    public Difficulty getDifficulty() {
        return this.settings.getDifficulty();
    }

    /**
     * 
     * @return if the music is active
     */
    public boolean isMusicActive() {
        return this.settings.isPlayMusic();
    }

    /**
     * 
     * @return if the effects is active
     */
    public boolean isEffectActive() {
        return this.settings.isPlayEffects();
    }
}
