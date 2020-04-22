package paranoid.model.score;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import paranoid.main.ParanoidApp;

/**
 * UserManager. It save the current user in file when a you win or lose a game level.
 */
public final class UserManager {
    private UserManager() {

    }

    /**
     * Save user in user path.
     * @param user the current user to save.
     */
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

    /**
     * Load the user from user path.
     * @return the current user.
     */
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
