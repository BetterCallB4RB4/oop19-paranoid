package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private void btnSendOnClickHandler() {

        if (!this.txtName.getText().contentEquals("")) {
            String username = this.txtName.getText();
            if (username.length() > MAX_LENGTH) {
                username = this.txtName.getText().substring(0, MAX_LENGTH);
            }
            this.user.setName(username);
            Score.Builder scoreBuilder = new Score.Builder();
            scoreBuilder.fromExistScore(this.topScores);
            scoreBuilder.addUserScore(user);
            if (LevelSelection.isStoryLevel(this.level.getLevelName())) {
                ScoreManager.saveStory(scoreBuilder.build());
            } else {
                ScoreManager.saveCustom(scoreBuilder.build());
            }
            this.btnSend.getScene().setRoot(LayoutManager.MENU.getLayout());
        }
    }

    @FXML
    private void btnScoreOnClickHandler() {
        this.btnScore.getScene().setRoot(LayoutManager.MENU.getLayout());
    }

    public void updateScore(final Score score, final User user, final Level level) {
        this.topScores = score;
        this.user = user;
        this.level = level;
        if (this.user.getLives() <= 0) {
            this.lblGameOver.setText("HAI PERSO!");
        } else {
            this.lblGameOver.setText("HAI VINTO!");
        }
        this.lblScore.setText("PUNTEGGIO: " + user.getScore().toString());

        if (topScores.getScoreList().size() >= 10) {
            final Integer minValue = topScores.getScoreList().stream()
                               .mapToInt(s -> s.getScore()).min().getAsInt();

            setNameVisible(user.getScore() > minValue);
        } else {
            setNameVisible(true);
        }
    }

    private void setNameVisible(final boolean condition) {
        this.txtName.setVisible(condition);
        this.lblName.setVisible(condition);
        this.btnSend.setVisible(condition);
        this.btnScore.setVisible(!(condition));
    }
}
