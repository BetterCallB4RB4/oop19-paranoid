package paranoid.controller.fxmlcontroller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import paranoid.common.dimension.ScreenConstant;
import paranoid.main.ParanoidApp;
import paranoid.model.level.Level;
import paranoid.model.level.LevelSelection;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.User;
import paranoid.view.parameters.LayoutManager;

/**
 * Controller of gameOver.fxml.
 */
public class GameOverController implements GuiController  {
    private static final int MAX_LENGTH = 10;
    private Score topScores;
    private User user;
    private Level level;

    @FXML
    private SplitPane mainPanel;

    @FXML
    private ScrollPane scroller;

    @FXML
    private VBox boxName;

    @FXML
    private GridPane grid;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblGameOver;

    @FXML
    private Label lblTop;

    @FXML
    private Label lblError;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnMenu;

    /**
     * Initialize the window with default settings adapted to the monitor resolution
     * and remove the ScrollBar from the scrollPane.
     */
    @FXML
    public void initialize() {
        this.mainPanel.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenConstant.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenConstant.SCREEN_HEIGHT);
        this.scroller.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scroller.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scroller.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scroller.setVbarPolicy(ScrollBarPolicy.NEVER);
    }

    /**
     * Go back to menu when click with mouse the button menu.
     */
    @FXML
    public void btnMenuOnClickHandler() {
        this.btnMenu.getScene().setRoot(LayoutManager.MENU.getLayout());
    }

    /**
     * Send button the allow you to send the name of the user that you have set
     * in txtName text field and save it in the current score file and update the GUI.
     * If txtName is empty show an error message on the screen.
     */
    @FXML
    public void btnSendOnClickHandler() {
        if (!this.txtName.getText().contentEquals("")) {
            String username = this.txtName.getText();
            if (username.length() > MAX_LENGTH) {
                username = this.txtName.getText().substring(0, MAX_LENGTH);
            }
            this.user.setName(username);

            final Score updatedScore = new Score.Builder()
            .fromExistScore(this.topScores)
            .addUserScore(user)
            .build();
            if (LevelSelection.isStoryLevel(this.level.getLevelName())) {
                ScoreManager.saveStory(updatedScore);
            } else {
                ScoreManager.saveCustom(updatedScore);
            }
            setNameVisible(false);
            viewScore(updatedScore);
        } else {
            this.lblError.setVisible(true);
        }
    }

    /**
     * Retrieve from gameLoop the score, the user and the level information.
     * Set to visible name field to set if the user score value is at least
     * before the minimum value present on the score list. Update the gui if the
     * user is not present.
     * Set label to win if user lives is more than 0, otherwise lost.
     * @param score the top scores to show in the GUI.
     * @param user the current user.
     * @param level the 
     */
    public void updateScore(final Score score, final User user, final Level level) {
        this.lblError.setVisible(false);
        this.topScores = score;
        this.user = user;
        this.level = level;

        if (this.user.getLives() > 0) {
            this.lblGameOver.setText("YOU WIN!!!");
        } else {
            this.lblGameOver.setText("YOU LOST!!!");
        }
        this.lblScore.setText("YOUR SCORE: " + user.getScore().toString());
        final List<User> scoreList = this.topScores.getScoreList();
        if (scoreList.isEmpty() || scoreList.size() < ParanoidApp.SCORE_MAX_ELEM) {
            setNameVisible(true);
        } else {
            final Integer minScore = scoreList.get(scoreList.size() - 1).getScore();
            if (this.user.getScore() > minScore) {
                setNameVisible(true);
            } else {
                setNameVisible(false);
                viewScore(this.topScores);
            }
        }
    }

    /**
     * Update the GUI and set different color if the current user is present in the
     * update score list.
     * @param score the score to show.
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
                if (this.user.equals(ele)) {
                    pos.setTextFill(Color.DARKRED);
                    username.setTextFill(Color.DARKRED);
                    scorePlayer.setTextFill(Color.DARKRED);
                }
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

    private void setNameVisible(final boolean condition) {
        this.scroller.setVisible(!condition);
        this.lblTop.setVisible(!condition);
        this.boxName.setVisible(condition);
        this.btnMenu.setVisible(!condition);
    }

    private void setLabelStyle(final Label label, final Color color) {
        label.setStyle("-fx-font-size: 20;"
                + "-fx-font-weight: bold");
        label.setTextFill(color);
    }
}
