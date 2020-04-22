package paranoid.model.settings;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import paranoid.main.ParanoidApp;

public final class SettingsManager {

    private SettingsManager() {

    }

    /**
     * save the current settings in the default game folder.
     * @param settings to save
     */
    public static void saveOption(final Settings settings) {
        try (ObjectOutputStream ostream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(ParanoidApp.OPTIONS)))) {
            ostream.writeObject(settings);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * load the saves that are in the default game folder.
     * @return current selected settings
     */
    public static Settings loadOption() {
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(ParanoidApp.OPTIONS)))) {
            return (Settings) istream.readObject();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e3) {
            e3.printStackTrace();
        }
        return null;
    }

}
