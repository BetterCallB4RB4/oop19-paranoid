package paranoid.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Brick;
import paranoid.model.entity.GameObject;
import paranoid.model.entity.Player;

/**
 * Gui controller of game.fxml .
 * It take from game loop every entities of the game and render it adapted to the monitor resolution
 */
public final class GameController implements GuiController {

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;

    /**
     * At game.fxml load it initialize the width and height of the canvas and set his 
     * graphic context for draw entity.
     */
    @FXML
    public void initialize() {
        this.canvas.setWidth(ScreenConstant.CANVAS_WIDTH);
        this.canvas.setHeight(ScreenConstant.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Draw all entities of the game adapted to the current resolution.
     * @param gameEntities Collection of each game entity taken from the game loop.
     */
    public void render(final List<GameObject> gameEntities) {
        drawWorld(gameEntities);
    }

    private void drawWorld(final List<GameObject> gameEntities) {
        gc.clearRect(0, 0, ScreenConstant.CANVAS_WIDTH, ScreenConstant.CANVAS_HEIGHT);
        gc.setFill(Color.ALICEBLUE);
        gameEntities.stream().forEach(e -> {
            final P2d pos = e.getPos();
            final double xPos = getXinPixel(pos);
            final double yPos = getYinPixel(pos);
            final double w = getWinPixel(e.getWidth());
            final double h = getHinPixel(e.getHeight());

            if (e instanceof Ball) {
                gc.fillOval(xPos, yPos, w, h);
            } else if (e instanceof Player) {
                gc.fillRect(xPos, yPos, w, h);
            } else if (e instanceof Brick) {
                gc.fillRect(xPos, yPos, w, h);
            }
        });
    }

    private double getXinPixel(final P2d pos) {
        return pos.getX() * ScreenConstant.RATIO_X;
    }

    private double getYinPixel(final P2d pos) {
        return pos.getY() * ScreenConstant.RATIO_Y;
    }

    private double getWinPixel(final double wp) {
        return wp * ScreenConstant.RATIO_X;
    }

    private double getHinPixel(final double hp) {
        return hp * ScreenConstant.RATIO_Y;
    }
}
