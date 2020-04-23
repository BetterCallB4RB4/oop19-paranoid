package paranoid.model.level;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Iterator;

public enum LevelSelection implements Iterator<LevelSelection> {

    /**
     * level 1 location and the input to fit the iterator interface.
     */
    LEVEL1("storyLevel/The beginning of the journey", 0, false),

    /**
     * level 2 location and the input to fit the iterator interface.
     */
    LEVEL2("storyLevel/illusion", 1, false),

    /**
     * level 3 location and the input to fit the iterator interface.
     */
    LEVEL3("storyLevel/Chamber of evaporation", 2, false),

    /**
     * level 4 location and the input to fit the iterator interface.
     */
    LEVEL4("storyLevel/desperate", 3, false),

    /**
     * level 5 location and the input to fit the iterator interface.
     */
    LEVEL5("storyLevel/Fiji addicted", 4, false),

    /**
     * level 6 location and the input to fit the iterator interface.
     */
    LEVEL6("storyLevel/fear", 5, false),

    /**
     * level 7 location and the input to fit the iterator interface.
     */
    LEVEL7("storyLevel/Windows2098", 6, false),

    /**
     * level 8 location and the input to fit the iterator interface.
     */
    LEVEL8("storyLevel/awakening", 7, false),

    /**
     * level 9 location and the input to fit the iterator interface.
     */
    LEVEL9("storyLevel/Blind third eye", 8, false),

    /**
     * level 10 location and the input to fit the iterator interface.
     */
    LEVEL10("storyLevel/madness", 9, true);

    private int index;
    private String path;
    private boolean isLast;

    LevelSelection(final String path, final int index, final boolean isLast) {
        this.path = path;
        this.index = index;
        this.isLast = isLast;
    }

    public int getIndex() {
        return index;
    }

    public String getPath() {
        return path;
    }

    public boolean isLast() {
        return isLast;
    }

    /**
     * 
     * @return load the story level saved in the resources
     */
    public Level getLevel() {
        Level level = null;
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(ClassLoader.getSystemResourceAsStream(path)))) {
            level = (Level) istream.readObject();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ClassCastException e3) {
            e3.printStackTrace();
        } 
        return level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return !this.isLast;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelSelection next() {
        return Arrays.asList(LevelSelection.values()).get(index + 1);
    }

    /**
     * 
     * @param nameLvl the name level to search
     * @return if the level is part of the campaign. 
     */
    public static boolean isStoryLevel(final String nameLvl) {
        return Arrays.asList(LevelSelection.values()).stream()
                                                     .map(i -> i.getLevel().getLevelName())
                                                     .anyMatch(i -> i.equals(nameLvl));
    }

    /**
     * 
     * @param level to serch in this enum
     * @return the corresponding enumeration linked to the story level.
     */
    public static LevelSelection getSelectionFromLevel(final Level level) {
        return Arrays.asList(LevelSelection.values()).stream()
                                                     .filter(i -> i.getLevel().equals(level))
                                                     .findFirst()
                                                     .get();
    }

}
