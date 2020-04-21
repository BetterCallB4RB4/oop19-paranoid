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
    LEVEL1("storyLevel/storyLevel1", 0, false),

    /**
     * level 2 location and the input to fit the iterator interface.
     */
    LEVEL2("storyLevel/storyLevel2", 1, true);

    private int index;
    private String path;
    private boolean isLast;

    /**
     * 
     * @param path
     * @param index
     * @param isLast
     */
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

    @Override
    public boolean hasNext() {
        return !this.isLast;
    }

    @Override
    public LevelSelection next() {
        return Arrays.asList(LevelSelection.values()).get(index + 1);
    }

    /**
     * 
     * @param nameLvl
     * @return if the level tt is part of the campaign. 
     */
    public static boolean isStoryLevel(final String nameLvl) {
        return Arrays.asList(LevelSelection.values()).stream()
                                                     .map(i -> i.getLevel().getLevelName())
                                                     .anyMatch(i -> i.equals(nameLvl));
    }

    /**
     * 
     * @param level
     * @return the corresponding enumeration linked to the story level.
     */
    public static LevelSelection getSelectionFromLevel(final Level level) {
        return Arrays.asList(LevelSelection.values()).stream()
                                                     .filter(i -> i.getLevel().equals(level))
                                                     .findFirst()
                                                     .get();
    }

}
