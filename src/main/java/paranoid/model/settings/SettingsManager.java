package paranoid.model.settings;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import paranoid.main.ParanoidApp;

public class SettingsManager {

    public static void saveOption(final Settings settings) {
        try (ObjectOutputStream ostream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(ParanoidApp.OPTIONS)))) {
            ostream.writeObject(settings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Settings loadOption() {
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(ParanoidApp.OPTIONS)))) {
            return (Settings) istream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
