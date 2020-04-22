package paranoid.model.level;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BackGround implements Serializable {

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_1("animatedBackGround/bg1.gif", "The World That Never Was"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_2("animatedBackGround/bg2.gif", "Memory's Skyscraper"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_3("animatedBackGround/bg3.gif", "The Brink of Despair"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_4("animatedBackGround/bg4.gif", "Avenue to Dreams"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_5("animatedBackGround/bg5.gif", "Contorted City"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_6("animatedBackGround/bg6.gif", "Delusive Beginning"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_7("animatedBackGround/bg7.gif", "Walk of Delusions"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_8("animatedBackGround/bg8.gif", "Fact within Fiction"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_9("animatedBackGround/bg9.gif", "Purle heart"),

    /**
     * one of 10 backgrounds available.
     */
    BACKGROUND_10("animatedBackGround/bg10.gif", "Autonomous Sensory Meridian Response");

    private String location;
    private String name;

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

    /**
     * 
     * @return a list of all available backgorunds
     */
    public static List<String> getBackGroundNames() {
        return Arrays.asList(BackGround.values()).stream()
                                          .map(i -> i.getName())
                                          .collect(Collectors.toList());
    }

    /**
     * the reference to the enumeration of a background by its name.
     * @param name to map
     * @return background enum
     */
    public static BackGround getBackGroundByName(final String name) {
        return Arrays.asList(BackGround.values()).stream()
                                                 .filter(i -> i.getName().equals(name))
                                                 .findFirst()
                                                 .get();
    }

}
