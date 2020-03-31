package paranoid.model.entity;

import paranoid.common.P2d;

public class Border {

    private P2d upperLefCorner;
    private P2d bottomRightCorner;
    private int width;
    private int height;

    public Border(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.upperLefCorner = new P2d(0, 0);
        this.bottomRightCorner = new P2d(width, height);
    }

    /**
     * 
     * @return the bottom right corner
     */
    public P2d getBottomRightCorner()  {
        return this.bottomRightCorner;
    }

    /**
     * 
     * @return the upper left corner
     */
    public P2d getUpperleftCorner() {
        return this.upperLefCorner;
    }

    /**
     * 
     * @return width of border
     */
    public int getHeight() {
        return this.width;
    }

    /**
     * 
     * @return height of border
     */
    public double getWidth() {
        return this.height;
    }
}
