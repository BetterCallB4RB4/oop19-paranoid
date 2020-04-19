package paranoid.model.settings;

public enum Difficulty {

    /**
     * 
     */
    EASY(200, 5000, 1), 

    /**
     * 
     */
    NORMAL(400, 10000, 2),

    /**
     * 
     */
    HARD(600, 15000, 3);

    private double speed;
    private int gameOverBonus;
    private int flatMultiplier;

    Difficulty(final double speed, final int gameOverBonus, final int flatMultiplier) {
        this.speed = speed;
        this.gameOverBonus = gameOverBonus;
        this.flatMultiplier = flatMultiplier;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getGameOverBonus() {
        return gameOverBonus;
    }

    public int getFlatMultiplier() {
        return flatMultiplier;
    }

}
