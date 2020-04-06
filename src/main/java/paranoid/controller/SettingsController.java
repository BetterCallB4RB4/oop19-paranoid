package paranoid.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import paranoid.main.ParanoidApp;
import paranoid.model.settings.Difficulty;
import paranoid.model.settings.Settings;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.model.settings.SettingsManager;
import paranoid.view.parameters.LayoutManager;

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
    private ComboBox<String> selectedLevel;

    @FXML
    private Button apply;

    @FXML
    private Button menu;

    /**
     * initialize the controller.
     */
    @FXML
    public void initialize() {
        File folderLevel = new File(ParanoidApp.LEVEL_FOLDER);
        List<String> levelList = new ArrayList<String>();
        for (int i = 0; i < folderLevel.list().length; i++) {
            levelList.add(folderLevel.list()[i]);
        }
        selectedLevel.getItems().addAll(levelList);
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
     * save current Options.
     */
    @FXML
    public void applyChanges() {
        List<RadioButton> difficulty = Arrays.asList(easy, normal, hard);
        List<RadioButton> playerNumber = Arrays.asList(onePlayer, twoPlayers);
        SettingsBuilder settingsBuilder = new SettingsBuilder();
        if (difficulty.stream().filter(i -> i.isSelected()).findFirst().get().getId().equals("easy")) {
            settingsBuilder.difficulty(Difficulty.EASY);
        } else if (difficulty.stream().filter(i -> i.isSelected()).findFirst().get().getId().equals("normal")) {
            settingsBuilder.difficulty(Difficulty.NORMAL);
        } else if (difficulty.stream().filter(i -> i.isSelected()).findFirst().get().getId().equals("hard")) {
            settingsBuilder.difficulty(Difficulty.HARD);
        }
        settingsBuilder.playEffect(effect.isSelected());
        settingsBuilder.playMusic(music.isSelected());
        settingsBuilder.selectLevel(selectedLevel.getValue());
        if (playerNumber.stream().filter(i -> i.isSelected()).findFirst().get().getId().equals("onePlayer")) {
            settingsBuilder.playerNumber(1);
        } else {
            settingsBuilder.playerNumber(2);
        }
        SettingsManager.saveOption(settingsBuilder.build());
    }
}
