package paranoid.model.score;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import paranoid.main.ParanoidApp;

public final class UserManager {
    private UserManager() {

    }

    public static void saveUser(final User user) {
        try (
                ObjectOutputStream w = new ObjectOutputStream(
                        new BufferedOutputStream(
                        new FileOutputStream(ParanoidApp.USER)))
            ) {
            w.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loadUser() {
        try (
                ObjectInputStream r = new ObjectInputStream(
                        new BufferedInputStream(
                        new FileInputStream(ParanoidApp.USER)))

            ) {
            return (User) r.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
