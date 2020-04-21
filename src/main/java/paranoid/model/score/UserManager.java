package paranoid.model.score;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import paranoid.main.ParanoidApp;

public final class UserManager {

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

    private UserManager() {

    }

    public static void saveUser(final User user) {
        try (
                FileOutputStream fileOut = new FileOutputStream(ParanoidApp.USER)
        ) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final byte[] iv = cipher.getIV();
            fileOut.write(iv);

            try (
                    ObjectOutputStream w = new ObjectOutputStream(
                            new BufferedOutputStream(
                            new CipherOutputStream(fileOut, cipher)))
                ) {
                w.writeObject(user);
            }
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static User loadUser() {
        try (
                FileInputStream fileIn = new FileInputStream(ParanoidApp.USER);
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

                return (User) r.readObject();
            }
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
}
