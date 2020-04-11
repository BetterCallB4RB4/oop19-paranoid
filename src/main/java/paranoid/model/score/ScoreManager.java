package paranoid.model.score;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import java.util.ArrayList;
import java.util.Collections;
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

    public static void saveScore(final Score score) throws IOException, InvalidKeyException {
        final List<User> scoreList = score.getScore();

        Collections.sort(scoreList, (o1, o2) -> (o1.getScore().equals(o2.getScore())) 
                ? o1.getName().compareTo(o2.getName()) 
                        : o2.getScore() - o1.getScore());

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        final byte[] iv = cipher.getIV();
        try (
                FileOutputStream fileOut = new FileOutputStream(ParanoidApp.SCORE)
        ) {
            fileOut.write(iv);

            try (
                    ObjectOutputStream w = new ObjectOutputStream(
                            new BufferedOutputStream(
                            new CipherOutputStream(fileOut, cipher)))
                ) {
                w.writeObject(scoreList);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static Score loadScore() throws IOException, InvalidKeyException, InvalidAlgorithmParameterException, ClassNotFoundException {
        try (
                FileInputStream fileIn = new FileInputStream(ParanoidApp.SCORE)
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
                final Object obj = r.readObject();
                List<User> scoreList = new ArrayList<>();
                if (obj instanceof List) {
                    scoreList = (List<User>) obj;
                }

                return new Score.Builder().fromExScore(scoreList).build();
            }
        }
    }
}
