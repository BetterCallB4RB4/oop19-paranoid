package paranoid.model.component.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Brick;
import paranoid.model.entity.Player;

public class GraphicsAdapterImpl implements GraphicsAdapter {

    private final GraphicsContext gc;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPlayer(final Player player, final Image sprite) {
        final double screenPositionX = getXinPixel(player.getPos().getX());
        final double screenPositionY = getYinPixel(player.getPos().getY());
        final double screenWidth = getWinPixel(player.getWidth());
        final double screenHeight = getHinPixel(player.getHeight());
        this.gc.drawImage(sprite, screenPositionX, screenPositionY, screenWidth, screenHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawBrick(final Brick brick, final Color color) {
        final double screenPositionX = getXinPixel(brick.getPos().getX());
        final double screenPositionY = getYinPixel(brick.getPos().getY());
        final double screenWidth = getWinPixel(brick.getWidth());
        final double screenHeight = getHinPixel(brick.getHeight());
        this.gc.setFill(color);
        this.gc.fillRect(screenPositionX, screenPositionY, screenWidth, screenHeight);
        this.gc.setStroke(Color.BLACK);
        this.gc.strokeRect(screenPositionX, screenPositionY, screenWidth, screenHeight);
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
