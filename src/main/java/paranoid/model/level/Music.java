package paranoid.model.level;

public enum Music {

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_1("animatedBackGround/futureFunk1.wav", "music1"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_2("animatedBackGround/lucky.wav", "music2"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_3("animatedBackGround/moderat.wav", "music3"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_4("animatedBackGround/stereo.wav", "music4");

    private String location;
    private String name;

    /**
     * Constructor.
     * @param name url where take the fxml
     */
    Music(final String location, final String name) {
        this.location = location;
        this.name = name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
