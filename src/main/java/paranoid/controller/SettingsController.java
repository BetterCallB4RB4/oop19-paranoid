package paranoid.controller;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import paranoid.common.dimension.ScreenConstant;
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
    private Button apply;

    @FXML
    private Button menu;

    @FXML
    private VBox buttonContainer;

    /**
     * 
     */
    @FXML
    public void initialize() {
        this.updateForm();
        BackgroundImage myBI2 = new BackgroundImage(new Image("backgrounds/menu4.jpg", 
                                                              ScreenConstant.SCREEN_WIDTH,
                                                              ScreenConstant.SCREEN_HEIGHT,
                                                              false,
                                                              true),
                                                    BackgroundRepeat.NO_REPEAT, 
                                                    BackgroundRepeat.NO_REPEAT, 
                                                    BackgroundPosition.DEFAULT,
                                                    BackgroundSize.DEFAULT);
        this.buttonContainer.setBackground(new Background(myBI2));
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
    }

    /**
     * 
     */
    public void updateForm() {
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
        if (settings.getPlayerNumber() == 1) {
            this.onePlayer.setSelected(true);
        } else {
            this.twoPlayers.setSelected(true);
        }
    }
}
