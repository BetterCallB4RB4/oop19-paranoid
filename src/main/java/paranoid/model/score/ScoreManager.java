package paranoid.model.score;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import paranoid.main.ParanoidApp;

public final class ScoreManager {
    private ScoreManager() {

    }

    public static void saveStory(final Score score) {
        save(ParanoidApp.SCORE_STORY_PATH, score, ParanoidApp.SCORE_STORY_NAME);
    }

    public static void saveCustom(final Score score) {
        save(ParanoidApp.SCORE_CUSTOM_PATH, score, score.getNameScore());
    }

    public static Score loadStory() {
        return load(ParanoidApp.SCORE_STORY_PATH, ParanoidApp.SCORE_STORY_NAME);
    }

    public static Score loadCustom(final String nameScore) {
        return load(ParanoidApp.SCORE_CUSTOM_PATH, nameScore);
    }

    public static List<Score> loadCustomList() {
        try (Stream<Path> walk = Files.walk(Paths.get(ParanoidApp.SCORE_CUSTOM_PATH))) {
            return walk.filter(Files::isRegularFile)
                       .map(i -> i.toFile().getName())
                       .map(i -> load(ParanoidApp.SCORE_CUSTOM_PATH, i))
                       .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static void save(final String path, final Score score, final String nameScore) {
        try (
                ObjectOutputStream w = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(path + ParanoidApp.SEPARATOR + nameScore)))
            ) {
            w.writeObject(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Score load(final String path, final String nameScore) {
        try (
                ObjectInputStream r = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(path + ParanoidApp.SEPARATOR + nameScore)))
                ) {
            return (Score) r.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
