package paranoid.model.score;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import paranoid.main.ParanoidApp;

public final class ScoreManager {
    private ScoreManager() {

    }

    public static void saveStory(final Score score) {
        save(ParanoidApp.SCORE_STORY, score, "storia");
    }

    public static Score loadStory() {
        return load(ParanoidApp.SCORE_STORY, "storia");
    }

    public static void saveCustom(final Score score) {
        save(ParanoidApp.SCORE_CUSTOM, score, score.getNameScore());
    }

    public static Score loadCustom(final String nameScore) {
        return load(ParanoidApp.SCORE_CUSTOM, nameScore);
    }

    public static List<Score> loadCustomList() {
        final List<Score> scores = new ArrayList<>();
        final String path = ParanoidApp.SCORE_CUSTOM;
        final File scoreFolder = new File(path);
        if (scoreFolder.exists() && scoreFolder.isDirectory()) {
            final File[] scoreFileList = scoreFolder.listFiles();
            for (final File file : scoreFileList) {
                scores.add(load(path, file.getName()));
            }
        }
        return scores;

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
