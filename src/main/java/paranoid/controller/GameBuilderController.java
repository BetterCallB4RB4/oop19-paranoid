package paranoid.controller;

import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import paranoid.common.Pair;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.entity.PlaceHolder;
import paranoid.model.level.LevelBuilder;
import paranoid.model.level.LevelManager;
import paranoid.view.parameters.LayoutManager;

public class GameBuilderController implements GuiController {

    private LevelBuilder levelBuilder;
    private GraphicsContext gc;
    private static final int BRICK_X = 11;
    private static final int BRICK_Y = 22;
    private static final int PLAYER_ZONE = 4;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox isDestructible;

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
    private Canvas canvas;

    /**
     * initialize the controller.
     */
    @FXML
    public void initialize() {
        this.setCanvas();
        this.canvas.setOnMouseClicked(e -> {
            Pair<PlaceHolder, Boolean> res = levelBuilder.getTileClicked(e.getX(), e.getY(),
                                                                         colorPicker.getValue(),
                                                                         isDestructible.isSelected(),
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
        });
    }

    /**
     * draw a grid on canvas.
     */
    private void setCanvas() {
        this.canvas.setWidth(ScreenConstant.CANVAS_WIDTH);
        this.canvas.setHeight(ScreenConstant.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        int tileX = (int) (this.canvas.getWidth() / BRICK_X);
        int tileY = (int) (this.canvas.getHeight() / BRICK_Y);
        int currentYpos = 0;
        for (int i = 0; i < BRICK_Y; i++) {
            gc.strokeLine(0, currentYpos, canvas.getWidth(), currentYpos);
            currentYpos += tileY;
        }
        int currentXpos = 0;
        for (int i = 0; i < BRICK_X; i++) {
            gc.strokeLine(currentXpos, 0, currentXpos, canvas.getHeight());
            currentXpos += tileX;
        }
        gc.fillRect(0, tileY * (BRICK_Y - PLAYER_ZONE), canvas.getWidth(), canvas.getHeight());
        this.levelBuilder = new LevelBuilder(tileX, tileY, BRICK_X, BRICK_Y);
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
        if (levelName.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Inserisci un nome al livello");
        } else {
            this.levelBuilder.setLevelName(levelName.getText());
            LevelManager.saveLevel(this.levelBuilder.build());
            JOptionPane.showMessageDialog(null, "Livello creato con successo");
        }
    }
}
