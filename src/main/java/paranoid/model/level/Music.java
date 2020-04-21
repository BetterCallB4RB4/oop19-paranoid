package paranoid.model.level;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Music implements Serializable {

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_1("music/song1.wav", "opal downers"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_2("music/song2.wav", "permanent anger"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_3("music/song3.wav", "lost heart"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_4("music/song4.wav", "easy clouds"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_5("music/song5.wav", "acid island"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_6("music/song6.wav", "sky system"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_7("music/song7.wav", "reverb sea"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_8("music/song8.wav", "heart phobia"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_9("music/song9.wav", "blanket fuxx"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_10("music/song10.wav", "cold ceremony");

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
    public URL getLocation() {
        return ClassLoader.getSystemResource(location);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public static List<String> getMusicNames() {
        return Arrays.asList(Music.values()).stream()
                                            .map(i -> i.getName())
                                            .collect(Collectors.toList());
    }

    public static Music getMusicByName(final String name) {
        return Arrays.asList(Music.values()).stream()
                                            .filter(i -> i.getName().equals(name))
                                            .findFirst()
                                            .get();
    }

}
