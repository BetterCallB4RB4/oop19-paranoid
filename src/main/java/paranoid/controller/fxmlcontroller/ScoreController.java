package paranoid.controller.fxmlcontroller;

import java.io.File;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import paranoid.common.ScreenConstant;
import paranoid.main.ParanoidApp;
import paranoid.model.level.Level;
import paranoid.model.level.LevelManager;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.User;
import paranoid.view.layoutmanager.LayoutManager;

/**
 * Controller of score.fxml.
 */
public class ScoreController implements GuiController, Observer {

    @FXML
    private SplitPane mainPanel;

    @FXML
    private ScrollPane scrollerLeft;

    @FXML
    private ScrollPane scrollerRight;

    @FXML
    private VBox buttonContainer;

    @FXML
    private GridPane grid;

    @FXML
    private VBox vBoxMenu;

    @FXML
    private Button btnMenu;

    @FXML
    private VBox labelContainer;

    /**
     * Initialize the window with default settings adapted to the monitor resolution
     * and remove the ScrollBar from the scrollPane.
     * @param subject connect the observer to the subject.
     */
    @FXML
    public void initialize(final Subject subject) {
        subject.register(this);
        this.mainPanel.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenConstant.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenConstant.SCREEN_HEIGHT);
        this.scrollerLeft.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerLeft.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerRight.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerRight.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerLeft.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scrollerLeft.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.scrollerRight.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scrollerRight.setVbarPolicy(ScrollBarPolicy.NEVER);
    }

    /**
     * Go back to menu when click with mouse the button menu.
     */
    @FXML
    public void btnMenuOnClickHandler() {
        final Scene scene = labelContainer.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }

    /**
     * implement pattern observer.
     * Update called from the menuController subject.
     * It check if exist the correct score for every level retrieved from the level path folder
     * and write a default score in custom score folder with level name every if the check resulted is false.
     * Then it update the gui with a button for every score present in the story/custom folder.
     */
    @Override
    public void update() {
        updateScoreList();
        this.buttonContainer.getChildren().clear();
        this.grid.getChildren().clear();
        final Score scoreStory = ScoreManager.loadStory();
        final Button btnStory = new Button(scoreStory.getNameScore());
        setButtonStyle(btnStory, btnMenu);
        btnStory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewScore(ScoreManager.loadStory());
            }

        });
        this.buttonContainer.getChildren().add(btnStory);
        for (final Score scoreCustom : ScoreManager.loadCustomScores()) {
            final Button btnCustom = new Button(scoreCustom.getNameScore());
            this.setButtonStyle(btnCustom, btnMenu);
            btnCustom.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    viewScore(scoreCustom);
                }
            });
            this.buttonContainer.getChildren().add(btnCustom);
        }
    }

    private void updateScoreList() {
        final Set<Level> levelList = LevelManager.loadLevels();
        levelList.forEach(i -> {
            final String levelName = i.getLevelName();
            if (!new File(ParanoidApp.SCORE_CUSTOM_PATH + ParanoidApp.SEPARATOR + levelName).exists()) {
                ScoreManager.saveCustom(new Score.Builder()
                        .defaultScore(levelName)
                        .build());
            }
        });
    }

    /**
     * update the right scrollPane panel of the gui with the information of the user present in the
     * top score list of the current score file.
     */
    private void viewScore(final Score score) {
        this.grid.getChildren().clear();
        final Label name = new Label("NAME: ");
        setLabelStyle(name, Color.LAWNGREEN);
        final Label point = new Label("SCORE: ");
        setLabelStyle(point, Color.LAWNGREEN);
        this.grid.add(name, 1, 0);
        this.grid.add(point, 2, 0);
        final List<User> scoreList = score.getScoreList();

        if (!scoreList.isEmpty()) {
            Integer counter = 1;
            for (final User ele : scoreList) {
                final User user = ele;
                final Label pos = new Label(counter.toString());
                setLabelStyle(pos, Color.ALICEBLUE);
                scoreList.iterator();
                final Label username = new Label(user.getName());
                setLabelStyle(username, Color.ALICEBLUE);
                final Label scorePlayer = new Label(user.getScore().toString());
                setLabelStyle(scorePlayer, Color.ALICEBLUE);
                grid.add(pos, 0, counter);
                grid.add(username, 1, counter);
                grid.add(scorePlayer, 2, counter);
                counter++;
            }
        } else {
            final Label emptyList = new Label("SCORE EMPTY");
            setLabelStyle(emptyList, Color.ALICEBLUE);
            grid.add(emptyList, 1, 1);
        }
    }

    private void setButtonStyle(final Button subject, final Button reference) {
        subject.setStyle(reference.getStyle());
        subject.setEffect(reference.getEffect());
        subject.setFont(reference.getFont());
    }

    private void setLabelStyle(final Label label, final Color color) {
        label.setStyle("-fx-font-size: 20;"
                + "-fx-font-weight: bold");
        label.setTextFill(color);
    }

}
