package paranoid.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.view.parameters.LayoutManager;

public class GameOverController implements GuiController, SubjectScore {

    private List<ObserverScore> observer;
    private String name;
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
        this.name = this.txtName.getText();
        if (!this.name.contentEquals("")) {
            Score.Builder scoreBuilder = new Score.Builder();
            scoreBuilder.fromExistScore(this.topScores);
            scoreBuilder.addUserScore(this.name, this.playerScore);
            ScoreManager.saveScore(scoreBuilder.build());
            this.notifyObserver();
            this.btnSend.getScene().setRoot(LayoutManager.SCORE.getLayout());
        }
    }

    @FXML
    private void btnScoreOnClickHandler() {
        this.btnScore.getScene().setRoot(LayoutManager.SCORE.getLayout());
    }

    @FXML
    public void initialize() {
        this.observer = new ArrayList<>();
    }

    public void updateScore(final Integer playerScore) {
        this.topScores = ScoreManager.loadScore();
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

    @Override
    public void register(final ObserverScore obs) {
        this.observer.add(obs);

    }

    @Override
    public void unregister(final ObserverScore obs) {
        this.observer.remove(obs);

    }

    @Override
    public void notifyObserver() {
        this.observer.forEach(ObserverScore::update);

    }

}
