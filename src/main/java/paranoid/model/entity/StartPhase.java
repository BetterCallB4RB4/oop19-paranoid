package paranoid.model.entity;

import paranoid.common.P2d;

public enum StartPhase {

    /**
     * 
     */
    PLAYER(new P2d(290, 580), 78, 5),

    /**
     * 
     */
    BALL(new P2d(330, 570), 10, 10);

    private P2d spawnPoint;
    private int initWidth;
    private int initHeight;

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
