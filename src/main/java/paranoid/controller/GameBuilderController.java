package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import paranoid.common.Pair;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.entity.PlaceHolder;
import paranoid.model.level.BackGround;
import paranoid.model.level.LevelBuilder;
import paranoid.model.level.LevelManager;
import paranoid.model.level.LevelSelection;
import paranoid.model.level.Music;
import paranoid.model.score.Score;
import paranoid.model.score.ScoreManager;
import paranoid.view.parameters.LayoutManager;

public class GameBuilderController implements GuiController {

    private int tileY;
    private int tileX;
    private GraphicsContext gc;
    private LevelBuilder levelBuilder;
    private static final int PLAYER_ZONE = 4;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox indestructible;

    @FXML
    private Slider pointSlider;

    @FXML
    private Slider lifeSlider;

    @FXML
    private TextField levelName;

    @FXML
    private Button build;

    @FXML
    private Button menu;

    @FXML
    private Button del;

    @FXML
    private Canvas canvas;

    @FXML
    private ComboBox<String> ost;

    @FXML
    private ComboBox<String> backGround;

    @FXML
    private VBox formContainer;

    /**
     * init the canvas.
     */
    @FXML
    public void initialize() {
        this.ost.getItems().addAll(Music.getMusicNames());
        this.backGround.getItems().addAll(BackGround.getBackGroundNames());
        this.colorPicker.setValue(Color.HOTPINK);
        this.colorPicker.setEditable(false);
        this.levelBuilder = new LevelBuilder();
        this.setCanvas();
        this.canvas.setOnMouseClicked(e -> {
            if (e.getY() < (tileY * (ScreenConstant.BRICK_NUMBER_Y - PLAYER_ZONE))) {
                final Pair<PlaceHolder, Boolean> res = levelBuilder.hit(e.getX(), e.getY(),
                                                                  colorPicker.getValue(),
                                                                  indestructible.isSelected(),
                                                                  (int) pointSlider.getValue(),
                                                                  (int) lifeSlider.getValue());
                if (res.getY()) {
                    gc.setFill(colorPicker.getValue());
                    gc.fillRect(res.getX().getPos().getX(), res.getX().getPos().getY(), res.getX().getWidth(), res.getX().getHeight());
                    gc.strokeRect(res.getX().getPos().getX(), res.getX().getPos().getY(), res.getX().getWidth(), res.getX().getHeight());
                } else {
                    gc.clearRect(res.getX().getPos().getX(), res.getX().getPos().getY(), res.getX().getWidth(), res.getX().getHeight());
                    gc.strokeRect(res.getX().getPos().getX(), res.getX().getPos().getY(), res.getX().getWidth(), res.getX().getHeight());
                }
            }
        });
    }

    /**
     * draw a grid on canvas.
     */
    private void setCanvas() {
        this.canvas.setWidth(ScreenConstant.CANVAS_WIDTH);
        this.canvas.setHeight(ScreenConstant.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.gc.setStroke(Color.BLACK);
        this.gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        this.tileX = (int) (this.canvas.getWidth() / ScreenConstant.BRICK_NUMBER_X);
        this.tileY = (int) (this.canvas.getHeight() / ScreenConstant.BRICK_NUMBER_Y);
        final double wastePixel = ScreenConstant.CANVAS_WIDTH % ScreenConstant.BRICK_NUMBER_X;
        int currentYpos = 0;
        for (int i = 0; i < ScreenConstant.BRICK_NUMBER_Y; i++) {
            gc.strokeLine(0, currentYpos, canvas.getWidth() - wastePixel, currentYpos);
            currentYpos += tileY;
        }
        int currentXpos = 0;
        for (int i = 0; i < ScreenConstant.BRICK_NUMBER_X; i++) {
            gc.strokeLine(currentXpos, 0, currentXpos, canvas.getHeight());
            currentXpos += tileX;
        }
        this.gc.strokeLine(currentXpos, 0, currentXpos, canvas.getHeight());
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, tileY * (ScreenConstant.BRICK_NUMBER_Y - PLAYER_ZONE), canvas.getWidth() - wastePixel, canvas.getHeight());
    }

    /**
     * go back to menu.
     */
    @FXML
    public void backToMenu() {
        final Scene scene = menu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }

    /**
     * go back to menu.
     */
    @FXML
    public void buildLvl() {
        if (levelName.getText().isBlank() || ost.getValue() == null || backGround.getValue() == null) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("You must fill all the form");
            alert.showAndWait();
        } else if (LevelSelection.isStoryLevel(levelName.getText())) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("The name you have selected already belongs to a story level and cannot be overwritten");
            alert.showAndWait();
        } else {
            this.levelBuilder.setLevelName(levelName.getText());
            this.levelBuilder.setBackGround(backGround.getValue());
            this.levelBuilder.setSong(ost.getValue());
            LevelManager.saveLevel(this.levelBuilder.build());
            ScoreManager.saveCustom(new Score.Builder()
                    .defaultScore(this.levelName.getText())
                    .build());
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("The level was successfully created");
            alert.showAndWait();
        }
    }

    /**
     * cancella il livello costruito.
     */
    @FXML
    public void delateAll() {
        levelBuilder.delateAll();
        this.setCanvas();
    }

}
