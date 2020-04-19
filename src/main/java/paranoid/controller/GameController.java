package paranoid.controller;

import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.component.graphics.GraphicsAdapter;
import paranoid.model.component.graphics.GraphicsAdapterImpl;
import paranoid.model.entity.GameObject;
import paranoid.model.level.BackGround;

/**
 * Gui controller of game.fxml .
 * It take from game loop every entities of the game and render it adapted to the monitor resolution
 */
public final class GameController implements GuiController {

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblHighScore;

    @FXML
    private Label lblLives;

    @FXML
    private Label lblPause;

    @FXML
    private Pane panel;

    @FXML
    private VBox dashBoard;


    /**
     * At game.fxml load it initialize the width and height of the canvas and set his 
     * graphic context for draw entity.
     */
    @FXML
    public void initialize() {
        this.canvas.setWidth(ScreenConstant.CANVAS_WIDTH);
        this.canvas.setHeight(ScreenConstant.CANVAS_HEIGHT);
        this.panel.setMinWidth(ScreenConstant.CANVAS_WIDTH);
        this.panel.setMaxWidth(ScreenConstant.CANVAS_WIDTH);
        this.panel.setMinHeight(ScreenConstant.CANVAS_HEIGHT);
        this.panel.setMaxHeight(ScreenConstant.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        final BackgroundImage myBI2 = new BackgroundImage(new Image("backgrounds/dashboard7.jpg", 
                                                              ScreenConstant.SCREEN_WIDTH - ScreenConstant.CANVAS_WIDTH,
                                                              ScreenConstant.SCREEN_HEIGHT,
                                                              false,
                                                              true),
                                                    BackgroundRepeat.NO_REPEAT, 
                                                    BackgroundRepeat.NO_REPEAT, 
                                                    BackgroundPosition.CENTER,
                                                    BackgroundSize.DEFAULT);
        this.dashBoard.setBackground(new Background(myBI2));
    }

    public void setPause(final boolean pause) {
        this.lblPause.setVisible(pause);
    }

    /**
     * Draw all entities of the game adapted to the current resolution.
     * @param gameEntities Collection of each game entity taken from the game loop.
     * @param score The score of the player.
     * @param highScore The top score.
     * @param lives The remained life ot the player.
     */
    public void render(final Set<GameObject> gameEntities, final int score, final int highScore, final int lives) {
        drawScoreAndLives(score, highScore, lives);
        drawWorld(gameEntities);

    }

    private void drawScoreAndLives(final Integer highScore, final Integer score, final Integer lives) {
        this.lblHighScore.setText("HIGHSCORE: " + highScore.toString());
        this.lblScore.setText("SCORE: " + score.toString());
        this.lblLives.setText("LIVES: " + lives.toString());
    }

    private void drawWorld(final Set<GameObject> gameEntities) {
        gc.clearRect(0, 0, ScreenConstant.CANVAS_WIDTH, ScreenConstant.CANVAS_HEIGHT);
        final GraphicsAdapter ga = new GraphicsAdapterImpl(gc);
        gameEntities.stream().forEach(e -> {
            e.updateGraphics(ga);
        });
    }

    public void setBackGroundImage(final BackGround backGround) {
        final BackgroundImage bg = new BackgroundImage(new Image(backGround.getLocation(), 
                                                           ScreenConstant.CANVAS_WIDTH,
                                                           ScreenConstant.CANVAS_HEIGHT,
                                                           false,
                                                           true),
                                                 BackgroundRepeat.REPEAT, 
                                                 BackgroundRepeat.NO_REPEAT, 
                                                 BackgroundPosition.DEFAULT,
                                                 BackgroundSize.DEFAULT);
        this.panel.setBackground(new Background(bg));
    }

    public Canvas getCanvas() {
        return this.canvas;
    }
}
