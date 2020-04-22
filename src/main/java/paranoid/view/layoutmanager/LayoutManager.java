package paranoid.view.layoutmanager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import paranoid.controller.fxmlcontroller.GuiController;

/**
 * Enumeration for static load GUI layouts at the start of application.
 */
public enum LayoutManager {

    /**
     * menu.fxml path.
     */
    MENU("layouts/menu.fxml"),

    /**
     * game.fxml path.
     */
    GAME("layouts/game.fxml"),

    /**
     * score.fxml path.
     */
    SCORE("layouts/score.fxml"),

    /**
     * settings.fxml path.
     */
    SETTINGS("layouts/settings.fxml"),

    /**
     * tutorial.fxml path.
     */
    TUTORIAL("layouts/tutorial.fxml"),

    /**
     * chooseLvl.fxml path.
     */
    CHOOSE_LVL("layouts/chooseLvl.fxml"),

    /**
     * levelBuilder.fxml path.
     */
    LEVEL_BUILDER("layouts/levelBuilder.fxml"),

    /**
     * nextlevel.fxml path.
     */
    NEXT_LEVEL("layouts/nextLevel.fxml"),

    /**
     * gameOver.fxml path.
     */
    GAME_OVER("layouts/gameOver.fxml");

    private final transient GuiController guiController;
    private transient SplitPane layout;

    /**
     * Constructor.
     * @param name the path where get the layout.
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

    /**
     * @return layout from selected fxml path
     */
    public SplitPane getLayout() {
        return this.layout;
    }

    /**
     * 
     * @return guiController from selected fxml path
     */
    public GuiController getGuiController() {
        return this.guiController;
    }
}
