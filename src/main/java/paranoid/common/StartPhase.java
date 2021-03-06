package paranoid.common;

public enum StartPhase {

    /**
     * the default states.
     */
    PLAYER_ONE(new P2d(290, 580), 78, 20),

    /**
     * the default states.
     */
    PLAYER_TWO(new P2d(190, 580), 78, 20),

    /**
     * the default states.
     */
    BALL(new P2d(330, 570), 10, 10);

    private final P2d spawnPoint;
    private final int initWidth;
    private final int initHeight;

    StartPhase(final P2d spawnPoint, final int initWidth, final int initHeight) {
        this.spawnPoint = spawnPoint;
        this.initHeight = initHeight;
        this.initWidth = initWidth;
    }

    public P2d getSpawnPoint() {
        return spawnPoint;
    }

    public int getInitWidth() {
        return initWidth;
    }

    public int getInitHeight() {
        return initHeight;
    }

}
