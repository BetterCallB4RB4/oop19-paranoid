package paranoid.model.level;

public enum BackGround {

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_1("animatedBackGround/backGround0.gif", "bg1"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_2("animatedBackGround/backGround1.gif", "bg2"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_3("animatedBackGround/backGround2.gif", "bg3"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_4("animatedBackGround/backGround3.gif", "bg4"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_5("animatedBackGround/backGround4.gif", "bg5"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_6("animatedBackGround/backGround5.gif", "bg6"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_7("animatedBackGround/backGround6.gif", "bg7"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_8("animatedBackGround/backGround7.gif", "bg8"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_9("animatedBackGround/backGround8.gif", "bg9"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_10("animatedBackGround/backGround9.gif", "bg10");

    private String location;
    private String name;

    /**
     * Constructor.
     * @param name url where take the fxml
     */
    BackGround(final String location, final String name) {
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
