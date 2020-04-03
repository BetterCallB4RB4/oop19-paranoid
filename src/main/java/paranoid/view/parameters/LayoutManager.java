package paranoid.view.parameters;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import paranoid.controller.GuiController;

/**
 * Enum per gestire i caricamenti dei file fxml da passare alla scena.
 *
 */
public enum LayoutManager {

    /**
     * menu.fxml path.
     */
    MENU("layouts/menu.fxml"),

    /**
     * game.fxml path.
     */
    GAME("layouts/game.fxml");

    private final GuiController guiController;
    private SplitPane layout;

    /**
     * Costruttore privato che carica all'avvio dell'applicazione tutti i file fxml ed 
     * i relativi controller da poter passare alle classi che li richiedono.
     * @param name
     * url dove prelevare il file fxml.
     */
    LayoutManager(final String name) {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(name));
        try {
            this.layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.guiController = loader.getController();
    }

    public SplitPane getLayout() {
        return this.layout;
    }

    public GuiController getGuiController() {
        return this.guiController;
    }
}
