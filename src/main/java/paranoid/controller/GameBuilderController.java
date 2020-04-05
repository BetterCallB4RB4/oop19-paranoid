package paranoid.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import paranoid.common.dimension.ScreenConstant;
import paranoid.model.level.LevelBuilder;
import paranoid.view.parameters.LayoutManager;


public class GameBuilderController implements GuiController {

    private GraphicsContext gc;
    private LevelBuilder levelBuilder;

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
     * 
     */
    @FXML
    public void initialize() {
        this.canvas.setWidth(ScreenConstant.CANVAS_WIDTH);
        this.canvas.setHeight(ScreenConstant.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        this.levelBuilder = new LevelBuilder();
    }


    /**
     * go back to menu.
     */
    @FXML
    public void backToMenu() {
        final Scene scene = menu.getScene();
        scene.setRoot(LayoutManager.MENU.getLayout());
    }



}
