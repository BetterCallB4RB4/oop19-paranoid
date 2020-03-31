package paranoid.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import paranoid.common.P2d;

/**
 * Controllore della gui game.fxml .
 * Tramite il game engine inizializza il mondo ed i vari oggetti di gioco ad ogni frame.
 */
public final class GameController implements GuiController {

    private static final double CANVAS_WIDTH = 600;
    private static final double CANVAS_HEIGHT = 600;
    private static final double RATIO_X = CANVAS_WIDTH / 60;
    private static final double RATIO_Y = CANVAS_HEIGHT / 60;
    private final GraphicsContext gc;
    private final List<GameObj> gameEntities;

    @FXML
    private Canvas canvas;

    public GameController() {

    }

    /**
     * Setta le dimensioni del riquadro di gioco e 
     * inizializza la classe che disegna i vari oggetti.
     */
    @FXML
    public void initialize() {
        this.canvas.setWidth(CANVAS_WIDTH);
        this.canvas.setHeight(CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Metodo richiamato dal game engine per aggiornare la visuale del mondo di gioco.
     * @param gameEntities
     * Collezione contenente le varie entità di gioco.
     */
    public void render(final List<GameObj> gameEntities) {
        drawWorld(gameEntities);
    }

    /**
     * Metodo che disegna il mondo di gioco all'interno del riquadro.
     * @param gameEntities
     * Collezione contenente le varie entità del gioco. 
     */
    private void drawWorld(final List<GameObj> gameEntities) {
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        gameEntities.stream().foreach(e -> {
            final Pos2d pos = e.getCurrentPos();
            final int xPos = getXinPixel(pos);
            final int yPos = getYinPixel(pos);
            final int w = getWinPixel(e.getWidth);
            final int h = getHinPixel(e.getHeight);

            if (e instanceof Player) {
                gc.fillRect(xPos, yPos, w, h);
            } else if (e instanceof Ball) {
                gc.fillOval(xPos, yPos, w, h);
            } else if (e instanceof Brick) {
                gc.fillRect(xPos, yPos, w, h);
            }
        });
    }

    private int getXinPixel(final P2d pos) {
        return (int) Math.round(pos.getX() * RATIO_X);
    }

    private int getYinPixel(final P2d pos) {
        return (int) Math.round(pos.getY() * RATIO_Y);
    }

    private int getWinPixel(final double wp) {
        return (int) Math.round(wp * RATIO_X);
    }

    private int getHinPixel(final double hp) {
        return (int) Math.round(hp * RATIO_Y);
    }
}
