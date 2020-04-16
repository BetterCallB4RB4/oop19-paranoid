package paranoid.model.settings;

public enum Difficulty {

    /**
     * 
     */
    EASY(200), 

    /**
     * 
     */
    NORMAL(400),

    /**
     * 
     */
    HARD(600);

    private double speed;

    Difficulty(final double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }
}
