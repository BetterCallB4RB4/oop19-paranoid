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

/**
 * ScoreManater. Provide the save of the top scores in file.
 */
public final class ScoreManager {

    private ScoreManager() {

    }

    /**
     * Save the current score in the story path with the name retrieved from
     * ParanoidApp.SCORE_STORY_NAME.
     * @param score the score to save in the file.
     */
    public static void saveStory(final Score score) {
        save(ParanoidApp.SCORE_STORY_PATH, score, ParanoidApp.SCORE_STORY_NAME);
    }

    /**
     * Save the current score in the custom path with the score name.
     * @param score the score to save in the file.
     */
    public static void saveCustom(final Score score) {
        save(ParanoidApp.SCORE_CUSTOM_PATH, score, score.getNameScore());
    }

    /**
     * Load the score from the story path with the name retrieved from
     * ParanoidApp.SCORE_STORY_NAME.
     * @return the score loaded from the file.
     */
    public static Score loadStory() {
        return load(ParanoidApp.SCORE_STORY_PATH, ParanoidApp.SCORE_STORY_NAME);
    }

    /**
     * Load the score from the custom path with the selected name.
     * @param nameScore the name of file to load.
     * @return the score loaded from the file.
     */
    public static Score loadCustom(final String nameScore) {
        return load(ParanoidApp.SCORE_CUSTOM_PATH, nameScore);
    }

    /**
     * Load all files from the custom score path.
     * @return the list of all scores saved in the custom score path.
     */
    public static List<Score> loadCustomScores() {
        try (Stream<Path> walk = Files.walk(Paths.get(ParanoidApp.SCORE_CUSTOM_PATH))) {
            return walk.filter(Files::isRegularFile)
                       .map(i -> i.toFile().getName())
                       .map(i -> load(ParanoidApp.SCORE_CUSTOM_PATH, i))
                       .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Score>();
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
