package paranoid.common;

public enum Collision {

    /**
     *
     * given two objects A and B, the wall where object A touches object B is the right one.
     */
    RIGHT,

    /**
     * 
     * given two objects A and B, the wall where object A touches object B is the left one.
     */
    LEFT,

    /**
     * 
     * given two objects A and B, the wall where object A touches object B is the top one.
     */
    TOP,

    /**
     * 
     * given two objects A and B, the wall where object A touches object B is the bottom one.
     */
    BOTTOM;

}
