package paranoid.model.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<String> getBackGroundNames() {
        List<String> list = new ArrayList<>();
        list.add(BackGround.BACKGROUND_1.getName());
        list.add(BackGround.BACKGROUND_2.getName());
        list.add(BackGround.BACKGROUND_3.getName());
        list.add(BackGround.BACKGROUND_4.getName());
        list.add(BackGround.BACKGROUND_5.getName());
        list.add(BackGround.BACKGROUND_6.getName());
        list.add(BackGround.BACKGROUND_7.getName());
        list.add(BackGround.BACKGROUND_8.getName());
        list.add(BackGround.BACKGROUND_9.getName());
        list.add(BackGround.BACKGROUND_10.getName());
        return list;
    }

    public static BackGround getBackGroundByName(final String name) {
        Map<String, BackGround> musicName = new HashMap<>();
        musicName.put(BackGround.BACKGROUND_1.getName(), BackGround.BACKGROUND_1);
        musicName.put(BackGround.BACKGROUND_2.getName(), BackGround.BACKGROUND_2);
        musicName.put(BackGround.BACKGROUND_3.getName(), BackGround.BACKGROUND_3);
        musicName.put(BackGround.BACKGROUND_4.getName(), BackGround.BACKGROUND_4);
        musicName.put(BackGround.BACKGROUND_5.getName(), BackGround.BACKGROUND_5);
        musicName.put(BackGround.BACKGROUND_6.getName(), BackGround.BACKGROUND_6);
        musicName.put(BackGround.BACKGROUND_7.getName(), BackGround.BACKGROUND_7);
        musicName.put(BackGround.BACKGROUND_8.getName(), BackGround.BACKGROUND_8);
        musicName.put(BackGround.BACKGROUND_9.getName(), BackGround.BACKGROUND_9);
        musicName.put(BackGround.BACKGROUND_10.getName(), BackGround.BACKGROUND_10);
        return musicName.get(name);
    }

}
