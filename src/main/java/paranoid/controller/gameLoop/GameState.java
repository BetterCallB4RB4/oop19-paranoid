package paranoid.controller.gameLoop;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.PlayerId;
import paranoid.common.V2d;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.GameController;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.Player;
import paranoid.model.entity.World;
import paranoid.model.level.BackGround;
import paranoid.model.level.Level;
import paranoid.model.level.LevelManager;
import paranoid.model.level.Music;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
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
    private Score topScores = ScoreManager.loadScore();
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

    public void init() {
      //add players to the world
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player.Builder().position(new P2d(290,580))
                                           .width(80)
                                           .height(10)
                                           .color(Color.DARKGREEN)
                                           .playerId(PlayerId.ONE)
                                           .build());
        if (set.getPlayerNumber() == 2) {
            playerList.add(new Player.Builder().position(new P2d(290,580))
                                               .width(80)
                                               .height(10)
                                               .color(Color.RED)
                                               .playerId(PlayerId.TWO)
                                               .build());
        }
        world.setPlayers(playerList);

        //add balls to the world
        List<Ball> ballContainer = new ArrayList<>();
        switch (set.getDifficulty()) {
            case EASY:
                ballContainer.add(new Ball(new P2d(330, 570), new V2d(100, -200), 1, 10, 10));
            break;
            case NORMAL:
                ballContainer.add(new Ball(new P2d(330, 570), new V2d(100, -200), 2, 10, 10));
            break;
            case HARD:
                ballContainer.add(new Ball(new P2d(330, 570), new V2d(100, -200), 2.5, 10, 10));
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

    public void decLives() {
        this.lives--;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    public GamePhase getPhase() {
        return this.phase;
    }

    public void setPhase(final GamePhase phase) {
        this.phase = phase;
    }
}
