package paranoid.common.dimension;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
/**
 * Contains global information for setup the view adaptable to the resolution
 */
public final class ScreenConstant {

    /**
     * @return current screen size
     */
    private static final Rectangle2D SCREEN_RES = Screen.getPrimary().getBounds();
    
    /**
     * Width of the gui (portion adapted to the resolution).
     */
    public static final double SCREEN_WIDTH = SCREEN_RES.getWidth() / 1.5; 

    /**
     * Height of the gui (portion adapted to the resolution).
     */
    public static final double SCREEN_HEIGHT = SCREEN_RES.getHeight() / 1.5;

    /**
     * Width of the game world (used in model World).
     */
    public static final double WORLD_WIDTH = 600;

    /**
     * Height of the game world (used in model World).
     */
    public static final double WORLD_HEIGHT = 600;

    /**
     * Width of the canvas in GameController gui (adapted to the height res for make a square).
     */
    public static final double CANVAS_WIDTH = SCREEN_HEIGHT / 1.025;

    /**
     * Height of the canvas in GameController gui (adapted to the height res for make a square).
     */
    public static final double CANVAS_HEIGHT = SCREEN_HEIGHT / 1.025;

    /**
     * Convert every entity of the game width (adapted to the current resolution).
     */
    public static final double RATIO_X = CANVAS_WIDTH / WORLD_WIDTH;

    /**
     * Convert every entity of the game height (adapted to the current resolution).
     */
    public static final double RATIO_Y = CANVAS_HEIGHT / WORLD_HEIGHT;

    private ScreenConstant() {

    }
}
