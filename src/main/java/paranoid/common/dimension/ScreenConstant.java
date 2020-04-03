package paranoid.common.dimension;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
/**
 * Classe che contiene tutte le dimensioni da usare per costruire il mondo di gioco.
 */
public final class ScreenConstant {

    private static final Rectangle2D SCREEN_RES = Screen.getPrimary().getBounds();
    /**
     * Larghezza proporzionata alla risoluzione del monitor.
     */
    public static final double SCREEN_WIDTH = SCREEN_RES.getWidth() / 1.5; 

    /**
     * Altezza proporzionata alla risoluzione del monitor.
     */
    public static final double SCREEN_HEIGHT = SCREEN_RES.getHeight() / 1.5;

    /**
     * Larghezza del mondo di gioco (in base a questa si dispongono gli elementi del model).
     */
    public static final double WORLD_WIDTH = 600;

    /**
     * Altezza del mondo di gioco (in base a questa si dispongono gli elementi del model).
     */
    public static final double WORLD_HEIGHT = 600;

    /**
     * Larghezza del riquadro da usare nella gui (si adatta alla risoluzione del monitor).
     */
    public static final double CANVAS_WIDTH = SCREEN_HEIGHT / 1.025;

    /**
     * Altezza del riquadro da usare nella gui (si adatta alla risoluzione del monitor).
     */
    public static final double CANVAS_HEIGHT = SCREEN_HEIGHT / 1.025;

    /**
     * Larghezza di ogni elemento della view proporzionale al monitor.
     */
    public static final double RATIO_X = CANVAS_WIDTH / WORLD_WIDTH;

    /**
     * Altezza di ogni elemento della view proporzionale al monitor.
     */
    public static final double RATIO_Y = CANVAS_HEIGHT / WORLD_HEIGHT;

    private ScreenConstant() {

    }
}
