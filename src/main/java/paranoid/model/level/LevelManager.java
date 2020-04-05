package paranoid.model.level;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import paranoid.main.ParanoidApp;

public class LevelManager {

    public static void saveLevel(final Level level) {
        try (ObjectOutputStream ostream = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(ParanoidApp.LEVEL_FOLDER + ParanoidApp.SEPARATOR + level.getLevelName())))) {
            ostream.writeObject(level);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Level loadLevel(final String nameLevel) throws NullPointerException {
        Level level = null;
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(ParanoidApp.LEVEL_FOLDER + ParanoidApp.SEPARATOR + nameLevel)))) {
            level = (Level) istream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (level == null) {
            throw new NullPointerException();
        }
        return level;
    }

}

