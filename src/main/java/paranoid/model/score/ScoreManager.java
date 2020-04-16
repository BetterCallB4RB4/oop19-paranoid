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

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import paranoid.main.ParanoidApp;

public final class ScoreManager {
    /**
     * Number of score elements that you want in file.
     */
    private static SecretKey secretKey;
    private static Cipher cipher;

    static {
        try {
            secretKey = new SecretKeySpec("aesEncryptionKey".getBytes(), "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        List<Score> scores = new ArrayList<>();
        String path = ParanoidApp.SCORE_CUSTOM;
        File scoreFolder = new File(path);
        if (scoreFolder.exists() && scoreFolder.isDirectory()) {
            for (int i = 0; i < scoreFolder.list().length; i++) {
                scores.add(load(path, scoreFolder.listFiles()[i].getName()));
            }
        }
        return scores;

    }

    private static void save(final String path, final Score score, final String nameScore) {
        try (
                FileOutputStream fileOut = new FileOutputStream(path + ParanoidApp.SEPARATOR + nameScore)
        ) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final byte[] iv = cipher.getIV();
            fileOut.write(iv);

            try (
                    ObjectOutputStream w = new ObjectOutputStream(
                            new BufferedOutputStream(
                            new CipherOutputStream(fileOut, cipher)))
                ) {
                w.writeObject(score);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Score load(final String path, final String nameScore) {
        try (
                FileInputStream fileIn = new FileInputStream(path + ParanoidApp.SEPARATOR + nameScore)
        ) {
            final byte[] fileIv = new byte[16];
            if (fileIn.read(fileIv) == -1) {
                throw new IOException();
            }
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

            try (
                    ObjectInputStream r = new ObjectInputStream(
                            new BufferedInputStream(
                            new CipherInputStream(fileIn, cipher)))

                ) {

                return (Score) r.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
