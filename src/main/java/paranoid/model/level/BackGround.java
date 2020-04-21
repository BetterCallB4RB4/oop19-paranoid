package paranoid.model.level;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BackGround implements Serializable {

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_1("animatedBackGround/bg1.gif", "The World That Never Was"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_2("animatedBackGround/bg2.gif", "Memory's Skyscraper"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_3("animatedBackGround/bg3.gif", "The Brink of Despair"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_4("animatedBackGround/bg4.gif", "Avenue to Dreams"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_5("animatedBackGround/bg5.gif", "Contorted City"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_6("animatedBackGround/bg6.gif", "Delusive Beginning"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_7("animatedBackGround/bg7.gif", "Walk of Delusions"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_8("animatedBackGround/bg8.gif", "Fact within Fiction"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_9("animatedBackGround/bg9.gif", "Purle heart"),

    /**
     * uno di 10 sfondi animati.
     */
    BACKGROUND_10("animatedBackGround/bg10.gif", "Autonomous Sensory Meridian Response");

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

    public static List<String> getBackGroundNames() {
        return Arrays.asList(BackGround.values()).stream()
                                          .map(i -> i.getName())
                                          .collect(Collectors.toList());
    }

    public static BackGround getBackGroundByName(final String name) {
        return Arrays.asList(BackGround.values()).stream()
                                                 .filter(i -> i.getName().equals(name))
                                                 .findFirst()
                                                 .get();
    }

}
