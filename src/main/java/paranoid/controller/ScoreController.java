package paranoid.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.User;
import paranoid.view.parameters.LayoutManager;

public class ScoreController implements GuiController, ObserverScore {
    private SubjectScore subject;
    private Score score;

    @FXML
    private Button btnMenu;

    @FXML
    private List<Label> lblListName;

    @FXML
    private List<Label> lblListScore;

    @FXML
    public void initialize(final SubjectScore subject) {
        update();
        this.subject = subject;
        this.subject.register(this);
    }

    @FXML
    private void btnMenuOnClickHandler() {
        final Scene scene = this.btnMenu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }

    @Override
    public void update() {
            this.score = ScoreManager.loadScore();
            for (int x = 0; x < this.lblListName.size() && x < score.getScoreList().size(); x++) {
                this.lblListName.get(x).setText(score.getScoreList().get(x).getName());
                this.lblListScore.get(x).setText(score.getScoreList().get(x).getScore().toString());
            }

    }
}
