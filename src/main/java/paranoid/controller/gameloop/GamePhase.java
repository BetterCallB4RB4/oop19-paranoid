package paranoid.controller.gameloop;

/**
 * here the various game phases are described in order to separate the game loop actions.
 */
public enum GamePhase {

    /**
     * initial phase of entity creation.
     */
    INIT,

    /**
     * game loop cycles.
     */
    RUNNING,

    /**
     * in this phase no component is updated keeping all the objects freeze.
     */
    PAUSE,

    /**
     * computation of progression between levels.
     */
    WIN,

    /**
     * return to the initial state of progression.
     */
    LOST,

    /**
     * pre-game.
     */
    MENU;
}
