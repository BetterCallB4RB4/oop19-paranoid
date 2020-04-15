package paranoid.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.TypeScore;
import paranoid.view.parameters.LayoutManager;

public class GameOverController implements GuiController  {
    private static final int MAX_LENGTH = 10;
    private Score topScores;
    private Integer playerScore;

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
            Score.Builder scoreBuilder = new Score.Builder();
            scoreBuilder.fromExistScore(this.topScores);
            scoreBuilder.addUserScore(username, this.playerScore);
            ScoreManager.saveScore(TypeScore.STORY, scoreBuilder.build());
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

    public void updateScore(final Integer playerScore) {
        this.topScores = ScoreManager.loadScore(TypeScore.STORY, "storia");
        this.playerScore = playerScore;

        if (!topScores.getScoreList().isEmpty()) {
            final Integer minValue = topScores.getScoreList().stream()
                               .mapToInt(s -> s.getScore()).min().getAsInt();
            this.txtName.setVisible(this.playerScore >= minValue);
            this.btnSend.setVisible(this.playerScore >= minValue);
            this.btnScore.setVisible(this.playerScore < minValue);
        } else {
            this.txtName.setVisible(true);
            this.btnSend.setVisible(true);
            this.btnScore.setVisible(false);
        }
    }

}
