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
    MUSIC_1("music/song1.wav", "music1"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_2("music/song2.wav", "music2"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_3("music/song3.wav", "music3"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_4("music/song4.wav", "music4"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_5("music/song5.wav", "music5"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_6("music/song6.wav", "music6"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_7("music/song7.wav", "music7"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_8("music/song8.wav", "music8"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_9("music/song9.wav", "music9"),

    /**
     * uno di 10 sfondi animati.
     */
    MUSIC_10("music/song10.wav", "music10");

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
        list.add(Music.MUSIC_5.getName());
        list.add(Music.MUSIC_6.getName());
        list.add(Music.MUSIC_7.getName());
        list.add(Music.MUSIC_8.getName());
        list.add(Music.MUSIC_9.getName());
        list.add(Music.MUSIC_10.getName());
        return list;
    }

    public static Music getMusicByName(final String name) {
        Map<String, Music> musicName = new HashMap<>();
        musicName.put(Music.MUSIC_1.getName(), Music.MUSIC_1);
        musicName.put(Music.MUSIC_2.getName(), Music.MUSIC_2);
        musicName.put(Music.MUSIC_3.getName(), Music.MUSIC_3);
        musicName.put(Music.MUSIC_4.getName(), Music.MUSIC_4);
        musicName.put(Music.MUSIC_5.getName(), Music.MUSIC_5);
        musicName.put(Music.MUSIC_6.getName(), Music.MUSIC_6);
        musicName.put(Music.MUSIC_7.getName(), Music.MUSIC_7);
        musicName.put(Music.MUSIC_8.getName(), Music.MUSIC_8);
        musicName.put(Music.MUSIC_9.getName(), Music.MUSIC_9);
        musicName.put(Music.MUSIC_10.getName(), Music.MUSIC_10);
        return musicName.get(name);
    }

}
