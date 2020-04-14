package paranoid.controller.gameLoop;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
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
import paranoid.model.level.Music;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.settings.Difficulty;
import paranoid.model.settings.Settings;
import paranoid.model.settings.SettingsManager;
import paranoid.view.parameters.LayoutManager;

public class GameState {

    private int highScoreValue;
    private int playerScore;
    private int multiplier;
    private int lives;
    private World world;
    private GamePhase phase = GamePhase.INIT;
    private Settings set = SettingsManager.loadOption();
    private Score topScores = ScoreManager.loadScore("story");
    private GameController gameController;

    public GameState() {
        this.highScoreValue = this.topScores.getHighValue();
        this.playerScore = 0;
        this.multiplier = 1;
        this.lives = 4;

        this.world = new World(new Border(ScreenConstant.WORLD_WIDTH, ScreenConstant.WORLD_HEIGHT), this);

        Level lvl = set.getSelectedLevel();
        this.gameController = (GameController) LayoutManager.GAME.getGuiController();
        this.gameController.setBackGroundImage(BackGround.getBackGroundByName(lvl.getBackGround()));
        this.gameController.getMusicPlayer().setMusicEnable(set.isPlayMusic());
        this.gameController.getMusicPlayer().setEffectEnable(set.isPlayEffects());
        this.world.getEventHanlder().addMusicPlayer(this.gameController.getMusicPlayer());
        this.gameController.getMusicPlayer().playMusic(Music.getMusicByName(lvl.getMusic()));
        //add brick to the world
        world.setBricks(lvl.getBricks());
    }

    /**
     * set initial game state.
     */
    public void init() {
      //add players to the world
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player.Builder().position(StartPhase.PLAYER.getSpawnPoint())
                                           .width(StartPhase.PLAYER.getInitWidth())
                                           .height(StartPhase.PLAYER.getInitHeight())
                                           .color(Color.DARKGREEN)
                                           .playerId(PlayerId.ONE)
                                           .build());
        if (set.getPlayerNumber() == 2) {
            playerList.add(new Player.Builder().position(StartPhase.PLAYER.getSpawnPoint())
                                               .width(StartPhase.PLAYER.getInitWidth())
                                               .height(StartPhase.PLAYER.getInitHeight())
                                               .color(Color.RED)
                                               .playerId(PlayerId.TWO)
                                               .build());
        }
        world.setPlayers(playerList);

        //add balls to the world
        List<Ball> ballContainer = new ArrayList<>();
        switch (set.getDifficulty()) {
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
        return playerScore;
    }

    /**
     * @return the highscore.
     */
    public int getHighScoreValue() {
        return highScoreValue;
    }

    /**
     * @param playerScore the player score to set
     */
    public void setPlayerScore(final int playerScore) {
        this.playerScore = playerScore;

        if (this.playerScore > this.highScoreValue) {
            this.highScoreValue = this.playerScore;
        }
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
        return lives;
    }

    /**
     * @param lives the lives to set
     */
    public void setLives(final int lives) {
        this.lives = lives;
    }

    /**
     * 
     */
    public void decLives() {
        this.lives--;
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
}
