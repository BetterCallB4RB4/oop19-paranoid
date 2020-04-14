package paranoid.controller;

import java.util.List;


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
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.model.score.TypeScore;
import paranoid.model.score.User;
import paranoid.view.parameters.LayoutManager;

public class ScoreController implements GuiController, ObserverScore {

    private SubjectScore subject;
    
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

    @FXML
    public void initialize(final SubjectScore subject) {
        this.subject = subject;
        this.subject.register(this);
        
        this.mainPanel.setMinWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenConstant.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenConstant.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenConstant.SCREEN_HEIGHT);


        this.scrollerLeft.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerLeft.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerLeft.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scrollerLeft.setVbarPolicy(ScrollBarPolicy.NEVER);

        this.scrollerRight.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerRight.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerRight.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scrollerRight.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.btnMenu.setStyle("-fx-background-color:  linear-gradient(to bottom right, #FFE74E, #A2FD24);"
                + "-fx-background-radius: 30;"
                + "-fx-font-size: 20;"
                + "-fx-font-weight: bold");
    }

    @FXML
    private void btnMenuOnClickHandler() {
        final Scene scene = labelContainer.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }

    /**
     * implement pattern observer.
     */
    @Override
    public void update() {
        this.buttonContainer.getChildren().clear();
        this.grid.getChildren().clear();

        Label name = new Label("NOME: ");
        name.setStyle("-fx-background-color:  linear-gradient(to bottom right, #FFE74E, #A2FD24);"
                + "-fx-background-radius: 30;"
                + "-fx-font-size: 20;"
                + "-fx-font-weight: bold");

        Label point = new Label("PUNTEGGIO: ");
        point.setStyle("-fx-background-color:  linear-gradient(to bottom right, #FFE74E, #A2FD24);"
                + "-fx-background-radius: 30;"
                + "-fx-font-size: 20;"
                + "-fx-font-weight: bold");
        this.grid.add(name, 1, 0);
        this.grid.add(point, 2, 0);

        for (final Score score : ScoreManager.loadScores(TypeScore.STORY)) {
            Button b = new Button(score.getScoreName().toUpperCase());
            b.setStyle("-fx-background-color:  linear-gradient(to bottom right, #FFE74E, #A2FD24);"
                     + "-fx-background-radius: 30;"
                     + "-fx-font-size: 20;"
                     + "-fx-font-weight: bold");
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    grid.getChildren().clear();
                    List<User> scoreList = score.getScoreList();
                    grid.add(name, 1, 0);
                    grid.add(point, 2, 0);
                    if (!scoreList.isEmpty()) {
                        for (int x = 0; x < scoreList.size(); x++) {
                            Label i = new Label(" " + Integer.toString(x + 1) + " ");
                            i.setStyle("-fx-font-size: 20;"
                                    + "-fx-font-weight: bold");
                            i.setTextFill(Color.ALICEBLUE);
                            Label n = new Label(scoreList.get(x).getName().toUpperCase());
                            n.setStyle("-fx-font-size: 20;"
                                    + "-fx-font-weight: bold");
                            n.setTextFill(Color.ALICEBLUE);

                            Label s = new Label(scoreList.get(x).getScore().toString());
                            s.setStyle("-fx-font-size: 20;"
                                    + "-fx-font-weight: bold");
                            s.setTextFill(Color.ALICEBLUE);
                            grid.add(i, 0, x + 1);
                            grid.add(n, 1, x + 1);
                            grid.add(s, 2, x + 1);
                        }

                    }

                }
            });
            this.buttonContainer.getChildren().add(b);
        }
        for (final Score score : ScoreManager.loadScores(TypeScore.CUSTOM)) {
            Button b = new Button(score.getScoreName().toUpperCase());
            b.setStyle("-fx-background-color:  linear-gradient(to bottom right, #FFE74E, #A2FD24);"
                     + "-fx-background-radius: 30;"
                     + "-fx-font-size: 20;"
                     + "-fx-font-weight: bold");
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    grid.getChildren().clear();
                    List<User> scoreList = score.getScoreList();
                    grid.add(name, 1, 0);
                    grid.add(point, 2, 0);
                    if (!scoreList.isEmpty()) {
                        for (int x = 0; x < scoreList.size(); x++) {
                            Label i = new Label(" " + Integer.toString(x + 1) + " ");
                            i.setStyle("-fx-font-size: 20;"
                                    + "-fx-font-weight: bold");
                            i.setTextFill(Color.ALICEBLUE);
                            Label n = new Label(scoreList.get(x).getName().toUpperCase());
                            n.setStyle("-fx-font-size: 20;"
                                    + "-fx-font-weight: bold");
                            n.setTextFill(Color.ALICEBLUE);

                            Label s = new Label(scoreList.get(x).getScore().toString());
                            s.setStyle("-fx-font-size: 20;"
                                    + "-fx-font-weight: bold");
                            s.setTextFill(Color.ALICEBLUE);
                            grid.add(i, 0, x + 1);
                            grid.add(n, 1, x + 1);
                            grid.add(s, 2, x + 1);
                        }

                    }

                }
            });
            this.buttonContainer.getChildren().add(b);
        }
    }

}