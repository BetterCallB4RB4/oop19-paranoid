package paranoid.model.collision;

import paranoid.common.V2d;

/**
 * the more the ball hits to the right, the sharper the angle will be returned.
 *
 */
public enum Direction {

    /**
     * the angle of the direction of the ball.
     */
    LEFT(155), 

    /**
     * the angle of the direction of the ball.
     */
    MID_LEFT(140),

    /**
     * the angle of the direction of the ball.
     */
    EDGE_LEFT(105),

    /**
     * the angle of the direction of the ball.
     */
    EDGE_RIGHT(70),

    /**
     * the angle of the direction of the ball.
     */
    MID_RIGHT(45),

    /**
     * the angle of the direction of the ball.
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

    /**
     * 
     * @return the vector calculated trigonometrically
     */
    public V2d getVector() {
        final double py = Math.sin(Math.toRadians(angle)) * unitVector;
        final double px = Math.cos(Math.toRadians(angle)) * unitVector;
        return new V2d(px, py);
    }


}
