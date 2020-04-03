package paranoid.model.entity;

import paranoid.common.P2d;

public class Border {

    private final P2d upperLefCorner;
    private final P2d bottomRightCorner;
    private final double width;
    private final double height;

    public Border(final double width, final double height) {
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
    public double getHeight() {
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
