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
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.settings.Settings;
import paranoid.model.settings.SettingsManager;
import paranoid.view.parameters.LayoutManager;

public class GameState {

    private Score bestScores;
    private int highScore;
    private int score;
    private int multiplier;
    private int lives;
    private World world;
    private GameController gameController;

    public GameState() {
        try {
            this.bestScores = ScoreManager.loadScore();
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IOException e) {
            e.printStackTrace();
        }
        this.highScore = this.bestScores.getHightScore();
        this.score = 0;
        this.multiplier = 1;
        this.lives = 1;

        this.world = new World(new Border(ScreenConstant.WORLD_WIDTH, ScreenConstant.WORLD_HEIGHT), this);
        Settings set = SettingsManager.loadOption();

        Level lvl = LevelManager.loadLevel(set.getSelectedLevel());
        this.gameController = (GameController) LayoutManager.GAME.getGuiController();
        this.gameController.setBackGroundImage(BackGround.getBackGroundByName(lvl.getBackGround()));

        //add brick to the world
        world.setBricks(lvl.getBricks());

        //add players to the world
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player.Builder().position(new P2d(350,500))
                                           .width(80)
                                           .height(10)
                                           .color(Color.DARKGREEN)
                                           .playerId(PlayerId.ONE)
                                           .build());
        if (set.getPlayerNumber() == 2) {
            playerList.add(new Player.Builder().position(new P2d(290,500))
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
                ballContainer.add(new Ball(new P2d(330, 500), new V2d(100, -200), 1, 10, 10));
            break;
            case NORMAL:
                ballContainer.add(new Ball(new P2d(330, 500), new V2d(100, -200), 2, 10, 10));
            break;
            case HARD:
                ballContainer.add(new Ball(new P2d(330, 500), new V2d(100, -200), 2.5, 10, 10));
            break;
            default:
            break;
        }
        world.setBalls(ballContainer);
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the highscore.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * @param score the score to set
     */
    public void setScore(final int score) {
        this.score = score;

        if (this.score > this.highScore) {
            this.highScore = this.score;
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
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Save the final score in file.
     */
    public void saveScore() {
        this.bestScores.addScore("SUPERMK", this.getScore());

        try {
            ScoreManager.saveScore(this.bestScores);
        } catch (InvalidKeyException | IOException e) {
            e.printStackTrace();
        }
    }
}
