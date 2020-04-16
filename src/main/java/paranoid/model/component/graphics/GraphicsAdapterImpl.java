package paranoid.model.component.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.entity.Ball;

public class GraphicsAdapterImpl implements GraphicsAdapter {

    private GraphicsContext gc;

    public GraphicsAdapterImpl(final GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * 
     */
    @Override
    public void drawBall(final Ball ball, final Image sprite) {
        final double screenPositionX = getXinPixel(ball.getPos().getX());
        final double screenPositionY = getYinPixel(ball.getPos().getY());
        final double screenWidth = getWinPixel(ball.getWidth());
        final double screenHeight = getHinPixel(ball.getHeight());
        this.gc.drawImage(sprite, screenPositionX, screenPositionY, screenWidth, screenHeight);
    }

    private double getXinPixel(final double posX) {
        return posX * ScreenConstant.RATIO_X;
    }

    private double getYinPixel(final double posY) {
        return posY * ScreenConstant.RATIO_Y;
    }

    private double getWinPixel(final double wp) {
        return wp * ScreenConstant.RATIO_X;
    }

    private double getHinPixel(final double hp) {
        return hp * ScreenConstant.RATIO_Y;
    }

}
