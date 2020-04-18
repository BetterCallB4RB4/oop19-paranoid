package paranoid.controller.gameLoop;

import java.util.ArrayList;
import java.util.List;

import paranoid.common.PlayerId;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.GameController;
import paranoid.model.collision.Direction;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.Player;
import paranoid.model.entity.StartPhase;
import paranoid.model.entity.World;
import paranoid.model.level.BackGround;
import paranoid.model.level.Level;
import paranoid.model.level.LevelSelection;
import paranoid.model.level.Music;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.Difficulty;
import paranoid.model.settings.Settings;
import paranoid.model.settings.SettingsManager;
import paranoid.view.parameters.LayoutManager;

public class GameState {

    private int highScoreValue;
    private int multiplier;
    private GamePhase phase;
    private final GameController gameController;
    private final Settings settings;
    private final Score scores;
    private final World world;
    private final Level level;
    private final User user;

    public GameState() {
        this.multiplier = 1;
        this.highScoreValue = 0;
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
        world.setBricks(level.getBricks());


        this.gameController = (GameController) LayoutManager.GAME.getGuiController();
        this.gameController.setBackGroundImage(BackGround.getBackGroundByName(level.getBackGround()));
        this.gameController.getMusicPlayer().setMusicEnable(settings.isPlayMusic());
        this.gameController.getMusicPlayer().setEffectEnable(settings.isPlayEffects());
        this.world.getEventHanlder().addMusicPlayer(this.gameController.getMusicPlayer());
        this.gameController.getMusicPlayer().playMusic(Music.getMusicByName(level.getMusic()));
    }

    /**
     * set initial game state.
     */
    public void init() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player.Builder().position(StartPhase.PLAYER_ONE.getSpawnPoint())
                                           .width(StartPhase.PLAYER_ONE.getInitWidth())
                                           .height(StartPhase.PLAYER_ONE.getInitHeight())
                                           .playerId(PlayerId.ONE)
                                           .build());
        if (settings.getPlayerNumber() == 2) {
            playerList.add(new Player.Builder().position(StartPhase.PLAYER_TWO.getSpawnPoint())
                                               .width(StartPhase.PLAYER_TWO.getInitWidth())
                                               .height(StartPhase.PLAYER_TWO.getInitHeight())
                                               .playerId(PlayerId.TWO)
                                               .build());
        }
        world.setPlayers(playerList);

        //add balls to the world
        List<Ball> ballContainer = new ArrayList<>();
        switch (settings.getDifficulty()) {
            case EASY:
                ballContainer.add(new Ball(StartPhase.BALL.getSpawnPoint(), Direction.EDGE_LEFT.getVector().mul(-1), Difficulty.EASY.getSpeed(), StartPhase.BALL.getInitWidth(), StartPhase.BALL.getInitHeight()));
            break;
            case NORMAL:
                ballContainer.add(new Ball(StartPhase.BALL.getSpawnPoint(), Direction.EDGE_LEFT.getVector().mul(-1), Difficulty.NORMAL.getSpeed(), StartPhase.BALL.getInitWidth(), StartPhase.BALL.getInitHeight()));
            break;
            case HARD:
                ballContainer.add(new Ball(StartPhase.BALL.getSpawnPoint(), Direction.EDGE_LEFT.getVector().mul(-1), Difficulty.HARD.getSpeed(), StartPhase.BALL.getInitWidth(), StartPhase.BALL.getInitHeight()));
            break;
            default:
            break;
        }
        world.setBalls(ballContainer);
        phase = GamePhase.PAUSE;
    }
    /**
     * @return the score
     */
    public int getPlayerScore() {
        return user.getScore();
    }

    /**
     * @return the highscore.
     */
    public int getHighScoreValue() {
        return user.getScore() > this.highScoreValue
                ? user.getScore() : this.highScoreValue;
    }

    /**
     * @param playerScore the player score to set
     */
    public void setPlayerScore(final int playerScore) {
        user.setScore(playerScore);
    }

    /**
     * @return the multiplier
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * @param multiplier the multiplier to set
     */
    public void setMultiplier(final int multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * @return the lives
     */
    public int getLives() {
        return user.getLives();
    }

    /**
     * @param lives the lives to set
     */
    public void setLives(final int lives) {
        user.setLives(lives);
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * 
     * @return the game phase
     */
    public GamePhase getPhase() {
        return this.phase;
    }

    /**
     * 
     * @param phase
     */
    public void setPhase(final GamePhase phase) {
        this.phase = phase;
    }

    /**
     * 
     * @return miao
     */
    public User getUser() {
        return this.user;
    }

    /**
     * 
     * @return gianni
     */
    public Score getTopScores() {
        return this.scores;
    }

    /**
     * 
     * @return gneaa
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * 
     * @return miasd
     */
    public Difficulty getDifficulty() {
        return this.settings.getDifficulty();
    }
}
