package paranoid.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.User;
import paranoid.view.parameters.LayoutManager;

public class ScoreController implements GuiController {

    @FXML
    private Button btnMenu;

    @FXML
    private List<Label> nameList;

    @FXML
    private List<Label> scoreList;

    @FXML
    public void initialize() {

        try {
            final Score score = ScoreManager.loadScore();
            final List<User> scoreList = score.getScore();

            for (int x = 0; x < this.scoreList.size() && x < scoreList.size(); x++) {
                this.nameList.get(x).setText(scoreList.get(x).getName());
                this.scoreList.get(x).setText(scoreList.get(x).getScore().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnMenuOnClickHandler() {
        final Scene scene = this.btnMenu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }
}
