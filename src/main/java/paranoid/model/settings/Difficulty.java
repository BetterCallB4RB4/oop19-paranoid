package paranoid.model.settings;

public enum Difficulty {

    /**
     * set the speed of the player, the base of the multiplier 
     * and the bonus of points at the end of the game.
     */
    EASY(200, 5000, 1), 

    /**
     * set the speed of the player, the base of the multiplier 
     * and the bonus of points at the end of the game.
     */
    NORMAL(400, 10_000, 2),

    /**
     * set the speed of the player, the base of the multiplier and 
     * the bonus of points at the end of the game.
     */
    HARD(600, 15_000, 3);

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
