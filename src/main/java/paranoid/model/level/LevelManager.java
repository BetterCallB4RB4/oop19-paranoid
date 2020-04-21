package paranoid.model.level;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import paranoid.main.ParanoidApp;

public final class LevelManager {

    private LevelManager() {
    }

    public static void saveLevel(final Level level) {
        try (ObjectOutputStream ostream = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(ParanoidApp.LEVEL_FOLDER + ParanoidApp.SEPARATOR + level.getLevelName())))) {
            ostream.writeObject(level);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Level> loadLevels() {
        final List<Level> levels = new ArrayList<>();
        final File levelFolder = new File(ParanoidApp.LEVEL_FOLDER);
        if (levelFolder.exists() && levelFolder.isDirectory()) {
            for (int i = 0; i < levelFolder.list().length; i++) {
                levels.add(loadLevel(levelFolder.listFiles()[i].getName()));
            }
        }
        return levels;
    }

    private static Level loadLevel(final String nameLevel) {
        Level level = null;
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(ParanoidApp.LEVEL_FOLDER + ParanoidApp.SEPARATOR + nameLevel)))) {
            level = (Level) istream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return level;
    }

}

