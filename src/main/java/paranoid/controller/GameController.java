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
 * Controllore della gui game.fxml .
 * Tramite il game engine inizializza il mondo ed i vari oggetti di gioco ad ogni frame.
 */
public final class GameController implements GuiController {

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;

    /**
     * Setta le dimensioni del riquadro di gioco e 
     * inizializza la classe che disegna i vari oggetti.
     */
    @FXML
    public void initialize() {
        this.canvas.setWidth(ScreenConstant.CANVAS_WIDTH);
        this.canvas.setHeight(ScreenConstant.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Metodo richiamato dal game engine per aggiornare ogni elemento nello schermo.
     * @param gameEntities
     * Collezione contenente le varie entità di gioco.
     */
    public void render(final List<GameObject> gameEntities) {
        drawWorld(gameEntities);
    }

    /**
     * Metodo che disegna il mondo di gioco all'interno del riquadro proporzionato alla
     * risoluzione del monitor.
     * @param gameEntities
     * Collezione contenente le varie entità del gioco. 
     */
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
