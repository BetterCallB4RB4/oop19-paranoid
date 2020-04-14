package paranoid.model.level;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Music {

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_1("music/futureFunk1.wav", "music1"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_2("music/lucky.wav", "music2"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_3("music/moderat.wav", "music3"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_4("music/stereo.wav", "music4");

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
        List<String> list = new ArrayList<>();
        list.add(Music.MUSIC_1.getName());
        list.add(Music.MUSIC_2.getName());
        list.add(Music.MUSIC_3.getName());
        list.add(Music.MUSIC_4.getName());
        return list;
    }

    public static Music getMusicByName(final String name) {
        Map<String, Music> musicName = new HashMap<>();
        musicName.put(Music.MUSIC_1.getName(), Music.MUSIC_1);
        musicName.put(Music.MUSIC_2.getName(), Music.MUSIC_2);
        musicName.put(Music.MUSIC_3.getName(), Music.MUSIC_3);
        musicName.put(Music.MUSIC_4.getName(), Music.MUSIC_4);
        return musicName.get(name);
    }

}
