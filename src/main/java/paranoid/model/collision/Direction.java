package paranoid.model.collision;

import paranoid.common.V2d;

public enum Direction {

    /**
     * 
     */
    LEFT(155), 

    /**
     * 
     */
    MID_LEFT(140),

    /**
     * 
     */
    EDGE_LEFT(105),

    /**
     * 
     */
    EDGE_RIGHT(70),

    /**
     * 
     */
    MID_RIGHT(45),

    /**
     * 
     */
    RIGHT(25);

    private int angle;
    private static int unitVector = 1;

    /**
     * 
     * @param angle
     */
    Direction(final int angle) {
        this.angle = angle;
    }

    public V2d getVector() {
        final double py = Math.sin(Math.toRadians(angle)) * unitVector;
        final double px = Math.cos(Math.toRadians(angle)) * unitVector;
        return new V2d(px, py);
    }


}
