package paranoid.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.TypeScore;
import paranoid.model.score.User;
import paranoid.view.parameters.LayoutManager;

public class GameOverController implements GuiController  {
    private static final int MAX_LENGTH = 10;
    private Score topScores;
    private User user;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnScore;

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
            if (this.topScores.getScoreName().contentEquals("storia")) {
                ScoreManager.saveScore(TypeScore.STORY, scoreBuilder.build());
            } else {
                ScoreManager.saveScore(TypeScore.CUSTOM, scoreBuilder.build());
            }
            this.btnSend.getScene().setRoot(LayoutManager.MENU.getLayout());
        }
    }

    @FXML
    private void btnScoreOnClickHandler() {
        this.btnScore.getScene().setRoot(LayoutManager.MENU.getLayout());
    }

    @FXML
    public void initialize() {

    }

    public void updateScore(final Score score, final User user) {
        this.topScores = score;
        this.user = user;

        if (topScores.getScoreList().size() >= 10) {
            final Integer minValue = topScores.getScoreList().stream()
                               .mapToInt(s -> s.getScore()).min().getAsInt();
            this.txtName.setVisible(user.getScore() >= minValue);
            this.btnSend.setVisible(user.getScore() >= minValue);
            this.btnScore.setVisible(!(user.getScore() >= minValue));
        } else {
            this.txtName.setVisible(user.getScore() > 0);
            this.btnSend.setVisible(user.getScore() > 0);
            this.btnScore.setVisible(!(user.getScore() > 0));
        }
    }
}
