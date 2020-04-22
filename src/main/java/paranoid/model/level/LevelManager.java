package paranoid.model.level;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import paranoid.main.ParanoidApp;

public final class LevelManager {

    private LevelManager() {
    }

    /**
     * save the level in the game folder created in the operating system.
     * @param level to save
     */
    public static void saveLevel(final Level level) {
        try (ObjectOutputStream ostream = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(ParanoidApp.LEVEL_FOLDER + ParanoidApp.SEPARATOR + level.getLevelName())))) {
            ostream.writeObject(level);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * @return all the levels in the custom level folder
     */
    public static Set<Level> loadLevels() {
        try (Stream<Path> walk = Files.walk(Paths.get(ParanoidApp.LEVEL_FOLDER))) {
            return walk.filter(Files::isRegularFile)
                       .map(i -> i.toFile().getName())
                       .map(i -> loadLevel(i))
                       .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<Level>();
    }

    /**
     * load the level of the name taken as input.
     * @param nameLevel 
     * @return the level from name
     */
    private static Level loadLevel(final String nameLevel) {
        Level level = null;
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(ParanoidApp.LEVEL_FOLDER + ParanoidApp.SEPARATOR + nameLevel)))) {
            level = (Level) istream.readObject();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e3) {
            e3.printStackTrace();
        }
        return level;
    }

}

