package paranoid.model.level;

public enum Effect {

    /**
     * uno di 10 sfondi animati.
     */
    BOARD_COLLISION("effetti/laser1.wav"),

    /**
     * uno di 10 sfondi animati.
     */
    BRICK_COLLISION("effetti/pepSound1.wav"),

    /**
     * uno di 10 sfondi animati.
     */
    PLAYER_COLLISION("effetti/zap1.wav");

    private String location;

    /**
     * Constructor.
     * @param name url where take the fxml
     */
    Effect(final String location) {
        this.location = location;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

}
