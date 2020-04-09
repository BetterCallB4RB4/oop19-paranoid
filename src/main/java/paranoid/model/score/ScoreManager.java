package paranoid.model.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import paranoid.common.Pair;
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
    private static final int MAX_SCORE_ELE = 5;
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
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();
        try (
                FileOutputStream fileOut = new FileOutputStream(ParanoidApp.SCORE)
        ) {
            fileOut.write(iv);

            try (
                    BufferedWriter w = new BufferedWriter(
                            new OutputStreamWriter(
                            new CipherOutputStream(fileOut, cipher)))
                ) {
                final List<Pair<String, Integer>> scoreList = score.getScore();

                Collections.sort(scoreList, java.util.Comparator.comparing(Pair::getY));
                Collections.reverse(scoreList);
                while (scoreList.size() > MAX_SCORE_ELE) {
                    scoreList.remove(scoreList.size() - 1);
                }

                for (final Pair<String, Integer> ele : scoreList) {
                    w.write(ele.getX());
                    w.newLine();
                    w.write(ele.getY().toString());
                    w.newLine();
                }
            }
        }
    }

    public static Score loadScore() throws IOException, InvalidKeyException, InvalidAlgorithmParameterException {
        try (
                FileInputStream fileIn = new FileInputStream(ParanoidApp.SCORE)
        ) {
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

            try (
                    BufferedReader r = new BufferedReader(
                            new InputStreamReader(
                            new CipherInputStream(fileIn, cipher)))

                ) {
                final List<Pair<String, Integer>> scoreList = new ArrayList<>();
                String name = null;
                int score;
                while ((name = r.readLine()) != null) {
                    score = Integer.parseInt(r.readLine());
                    scoreList.add(new Pair<>(name, score));
                }
                return new Score.Builder().fromExScore(scoreList).build();
            }
        }
    }
}
