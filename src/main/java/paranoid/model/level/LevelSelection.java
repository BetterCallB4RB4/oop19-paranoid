package paranoid.model.level;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public enum LevelSelection implements Iterator<LevelSelection> {

    /**
     * 
     */
    LEVEL1("storyLevel/level1", 0, false),

    /**
     * 
     */
    LEVEL2("storyLevel/level2", 1, false),

    /**
     * 
     */
    LEVEL3("storyLevel/level3", 2, false),

    /**
     * 
     */
    LEVEL4("storyLevel/level4", 3, false),

    /**
     * 
     */
    LEVEL5("storyLevel/level5", 4, true);

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

    public Level getLevel() {
        Level level = null;
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(ClassLoader.getSystemResourceAsStream(path)))) {
            level = (Level) istream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return level;
    }

    public boolean hasNext() {
        return !this.isLast;
    }

    @Override
    public LevelSelection next() {
        return Arrays.asList(LevelSelection.values()).get(index + 1);
    }

    public static boolean isStoryLevel(final String nameLvl) {
        List<LevelSelection> lvlSel = Arrays.asList(LevelSelection.values());
        return lvlSel.stream()
                     .map(i -> i.getLevel().getLevelName())
                     .anyMatch(i -> i.equals(nameLvl));
    }

}