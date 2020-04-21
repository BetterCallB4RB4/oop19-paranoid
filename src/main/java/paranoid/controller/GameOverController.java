package paranoid.controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import paranoid.main.ParanoidApp;
import paranoid.model.level.Level;
import paranoid.model.level.LevelSelection;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.User;
import paranoid.view.parameters.LayoutManager;

public class GameOverController implements GuiController  {
    private static final int MAX_LENGTH = 10;
    private Score topScores;
    private User user;
    private Level level;
    private Score updatedScore;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblScore;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnScore;

    @FXML
    private Label lblGameOver;

    @FXML
    private ScrollPane scroller;

    @FXML
    private GridPane grid;

    /**
     * 
     */
    @FXML
    public void initialize() {
        this.scroller.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scroller.setVbarPolicy(ScrollBarPolicy.NEVER);
    }

    /**
     * 
     */
    @FXML
    public void btnSendOnClickHandler() {

        if (!this.txtName.getText().contentEquals("")) {
            String username = this.txtName.getText();
            if (username.length() > MAX_LENGTH) {
                username = this.txtName.getText().substring(0, MAX_LENGTH);
            }
            this.user.setName(username);

            this.updatedScore = new Score.Builder()
            .fromExistScore(this.topScores)
            .addUserScore(user)
            .build();
            if (LevelSelection.isStoryLevel(this.level.getLevelName())) {
                ScoreManager.saveStory(this.updatedScore);
            } else {
                ScoreManager.saveCustom(this.updatedScore);
            }
            viewScore(this.updatedScore);
        }
    }

    /**
     * 
     */
    @FXML
    public void btnScoreOnClickHandler() {
        this.btnScore.getScene().setRoot(LayoutManager.MENU.getLayout());
    }

    /**
     * 
     * @param score
     * @param user
     * @param level
     */
    public void updateScore(final Score score, final User user, final Level level) {
        this.grid.getChildren().clear();
        this.btnScore.setVisible(false);
        this.topScores = score;
        this.user = user;
        this.level = level;
        this.updatedScore = this.topScores;

        if (this.user.getLives() <= 0) {
            this.lblGameOver.setText("HAI PERSO!");
        } else {
            this.lblGameOver.setText("HAI VINTO!");
        }
        this.lblScore.setText("IL TUO PUNTEGGIO: " + user.getScore().toString());
        final List<User> scoreList = this.topScores.getScoreList();
        if (scoreList.isEmpty() || scoreList.size() < ParanoidApp.SCORE_MAX_ELEM) {
            this.setNameVisible(true);
        } else {
            final Integer minScore = scoreList.get(scoreList.size() - 1).getScore();
            if (this.user.getScore() > minScore) {
                this.setNameVisible(true);
            } else {
                this.setNameVisible(false);
                viewScore(this.updatedScore);
            }
        }
    }

    private void setNameVisible(final boolean condition) {
        this.txtName.setVisible(condition);
        this.lblName.setVisible(condition);
        this.btnSend.setVisible(condition);
    }

    private void viewScore(final Score score) {
        this.btnScore.setVisible(true);
        this.setNameVisible(false);
        final Label name = new Label("NOME: ");
        name.setStyle("-fx-font-size: 20;"
                + "-fx-font-weight: bold");
        name.setTextFill(Color.LAWNGREEN);

        final Label point = new Label("PUNTEGGIO: ");
        point.setStyle("-fx-font-size: 20;"
                + "-fx-font-weight: bold");
        point.setTextFill(Color.LAWNGREEN);
        this.grid.add(name, 1, 0);
        this.grid.add(point, 2, 0);
        final List<User> scoreList = score.getScoreList();

        if (!scoreList.isEmpty()) {
            for (int x = 0; x < scoreList.size(); x++) {
                final Label i = new Label(" " + Integer.toString(x + 1) + " ");
                i.setStyle("-fx-font-size: 20;"
                        + "-fx-font-weight: bold");
                i.setTextFill(Color.ALICEBLUE);
                Label n = new Label(scoreList.get(x).getName());
                n.setStyle("-fx-font-size: 20;"
                        + "-fx-font-weight: bold");
                n.setTextFill(Color.ALICEBLUE);

                final Label s = new Label(scoreList.get(x).getScore().toString());
                s.setStyle("-fx-font-size: 20;"
                        + "-fx-font-weight: bold");
                s.setTextFill(Color.ALICEBLUE);
                grid.add(i, 0, x + 1);
                grid.add(n, 1, x + 1);
                grid.add(s, 2, x + 1);
                if (this.user.equals(scoreList.get(x))) {
                    i.setTextFill(Color.DARKRED);
                    n.setTextFill(Color.DARKRED);
                    s.setTextFill(Color.DARKRED);
                }
            }
        }
    }
}
