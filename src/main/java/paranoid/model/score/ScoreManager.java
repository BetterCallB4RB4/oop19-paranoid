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

    public static void saveScore(final TypeScore typeScore, final Score score) {
        try (
                FileOutputStream fileOut = new FileOutputStream(typeScore.getPath() + ParanoidApp.SEPARATOR + score.getScoreName())
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

    public static List<Score> loadScores(final TypeScore typeScore) {
        List<Score> scores = new ArrayList<>();
        File scoreFolder = new File(typeScore.getPath());
        if (scoreFolder.exists() && scoreFolder.isDirectory()) {
            for (int i = 0; i < scoreFolder.list().length; i++) {
                scores.add(loadScore(typeScore, scoreFolder.listFiles()[i].getName()));
            }
        }
        return scores;

    }
    public static Score loadScore(final TypeScore typeScore, final String scoreName) {
        try (
                FileInputStream fileIn = new FileInputStream(typeScore.getPath() + ParanoidApp.SEPARATOR + scoreName)
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
