package paranoid.model.level;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Music implements Serializable {

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_1("music/song1.wav", "opal downers"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_2("music/song2.wav", "permanent anger"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_3("music/song3.wav", "lost heart"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_4("music/song4.wav", "easy clouds"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_5("music/song5.wav", "acid island"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_6("music/song6.wav", "sky system"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_7("music/song7.wav", "reverb sea"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_8("music/song8.wav", "heart phobia"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_9("music/song9.wav", "blanket fuxx"),

    /**
     * contains the reference to the resource and the name.
     */
    MUSIC_10("music/song10.wav", "cold ceremony");

    private String location;
    private String name;

    Music(final String location, final String name) {
        this.location = location;
        this.name = name;
    }

    /**
     * @return the resource in the form of a url
     */
    public URL getLocation() {
        return ClassLoader.getSystemResource(location);
    }

    /**
     * @return the name of the music
     */
    public String getName() {
        return name;
    }

    /**
     * list to preserve the order in which the songs are displayed.
     * @return the list of song names returns.
     */
    public static List<String> getMusicNames() {
        return Arrays.asList(Music.values()).stream()
                                            .map(i -> i.getName())
                                            .collect(Collectors.toList());
    }

    /**
     * 
     * @param name to search
     * @return the corresponding enumeration linked to the music.
     */
    public static Music getMusicByName(final String name) {
        return Arrays.asList(Music.values()).stream()
                                            .filter(i -> i.getName().equals(name))
                                            .findFirst()
                                            .get();
    }

}
