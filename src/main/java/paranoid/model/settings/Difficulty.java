package paranoid.model.settings;

public enum Difficulty {

    /**
     * 
     */
    EASY(5), 

    /**
     * 
     */
    NORMAL(8),

    /**
     * 
     */
    HARD(11);

    private double speed;

    Difficulty(final double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }
}
