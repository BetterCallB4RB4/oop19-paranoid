package paranoid.model.collision;

/**
 * 
 * given two objects A and B, the wall where object A touches object B.
 *
 */
public enum Collision {

    /**
     * collision with right wall.
     */
    RIGHT,

    /**
     * collision with left wall.
     */
    LEFT,

    /**
     * collision with top wall.
     */
    TOP,

    /**
     * collision with bottom wall.
     */
    BOTTOM;

}
