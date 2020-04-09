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

public class SettingsController implements GuiController, Observer {

    private Subject subject;

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
     * 
     * @param subject observed
     */
    @FXML
    public void initialize(final Subject subject) {
        this.update();
        this.subject = subject;
        this.subject.register(this);
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

    /**
     * permette di caricare tutti i livelli disponibili.
     */
    @Override
    public void update() {
        Settings settings = SettingsManager.loadOption();
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
        selectedLevel.getItems().clear();
        File folderLevel = new File(ParanoidApp.LEVEL_FOLDER);
        List<String> levelList = new ArrayList<String>();
        for (int i = 0; i < folderLevel.list().length; i++) {
            levelList.add(folderLevel.list()[i]);
        }
        selectedLevel.getItems().addAll(levelList);
        selectedLevel.getSelectionModel().select(settings.getSelectedLevel());
        if (settings.getPlayerNumber() == 1) {
            this.onePlayer.setSelected(true);
        } else {
            this.twoPlayers.setSelected(true);
        }
    }
}
