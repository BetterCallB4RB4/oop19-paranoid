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
import paranoid.model.score.User;
import paranoid.view.parameters.LayoutManager;

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
     * 
     * @param subject
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
        this.scrollerLeft.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scrollerLeft.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.scrollerRight.setMinWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerRight.setMaxWidth(ScreenConstant.SCREEN_WIDTH / 2);
        this.scrollerRight.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.scrollerRight.setVbarPolicy(ScrollBarPolicy.NEVER);
    }

    /**
     * 
     */
    @FXML
    public void btnMenuOnClickHandler() {
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
        final Button bStory = new Button("STORIA");
        this.setButtonStyle(bStory, btnMenu);
        bStory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewScore(ScoreManager.loadStory());
            }

        });
        this.buttonContainer.getChildren().add(bStory);
        for (final Score score : ScoreManager.loadCustomList()) {
            final Button bCustom = new Button(score.getNameScore());
            this.setButtonStyle(bCustom, btnMenu);
            bCustom.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    viewScore(score);
                }
            });
            this.buttonContainer.getChildren().add(bCustom);
        }
    }

    private void viewScore(final Score score) {
        this.grid.getChildren().clear();
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
                final Label n = new Label(scoreList.get(x).getName());
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
            }
        }
    }

    private void setButtonStyle(final Button subject, final Button reference) {
        subject.setStyle(reference.getStyle());
        subject.setEffect(reference.getEffect());
        subject.setFont(reference.getFont());
    }

}
