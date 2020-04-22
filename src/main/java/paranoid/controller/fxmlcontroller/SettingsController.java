package paranoid.controller.fxmlcontroller;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import paranoid.model.settings.Difficulty;
import paranoid.model.settings.Settings;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.view.layoutmanager.LayoutManager;
import paranoid.model.settings.SettingsManager;

public class SettingsController implements GuiController {

    @FXML
    private RadioButton easy;

    @FXML
    private RadioButton normal;

    @FXML
    private RadioButton hard;

    @FXML
    private RadioButton onePlayer;

    @FXML
    private RadioButton twoPlayers;

    @FXML
    private CheckBox effect;

    @FXML
    private CheckBox music;

    @FXML
    private Button apply;

    @FXML
    private Button menu;

    @FXML
    private VBox buttonContainer;

    /**
     * initializes the settings screen.
     */
    @FXML
    public void initialize() {
        this.updateForm();
    }

    /**
     * return to the main menu.
     */
    @FXML
    public void backToMenu() {
        final Scene scene = menu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }

    /**
     * 
     * save current settings.
     */
    @FXML
    public void applyChanges() {
        final List<RadioButton> difficulty = Arrays.asList(easy, normal, hard);
        final List<RadioButton> playerNumber = Arrays.asList(onePlayer, twoPlayers);
        final SettingsBuilder settingsBuilder = new SettingsBuilder();
        settingsBuilder.selectLevel(SettingsManager.loadOption().getSelectedLevel());
        if (difficulty.stream().filter(i -> i.isSelected()).findFirst().get().getId().equals("easy")) {
            settingsBuilder.difficulty(Difficulty.EASY);
        } else if (difficulty.stream().filter(i -> i.isSelected()).findFirst().get().getId().equals("normal")) {
            settingsBuilder.difficulty(Difficulty.NORMAL);
        } else if (difficulty.stream().filter(i -> i.isSelected()).findFirst().get().getId().equals("hard")) {
            settingsBuilder.difficulty(Difficulty.HARD);
        }
        settingsBuilder.playEffect(effect.isSelected());
        settingsBuilder.playMusic(music.isSelected());
        if (playerNumber.stream().filter(i -> i.isSelected()).findFirst().get().getId().equals("onePlayer")) {
            settingsBuilder.playerNumber(1);
        } else {
            settingsBuilder.playerNumber(2);
        }
        SettingsManager.saveOption(settingsBuilder.build());
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("the settings have been saved");
        alert.showAndWait();
    }

    /**
     * updates the form with the currently selected information.
     */
    public void updateForm() {
        final Settings settings = SettingsManager.loadOption();
        switch (settings.getDifficulty()) {
            case EASY:
                easy.setSelected(true);
            break;
            case NORMAL:
                normal.setSelected(true);
            break;
            case HARD:
                hard.setSelected(true);
            break;
            default:
            break;
        }
        if (settings.isPlayEffects()) {
            this.effect.setSelected(true);
        } else {
            this.effect.setSelected(false);
        }
        if (settings.isPlayMusic()) {
            this.music.setSelected(true);
        } else {
            this.music.setSelected(false);
        }
        if (settings.getPlayerNumber() == 1) {
            this.onePlayer.setSelected(true);
        } else {
            this.twoPlayers.setSelected(true);
        }
    }
}
