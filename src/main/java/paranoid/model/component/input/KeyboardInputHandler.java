package paranoid.model.component.input;

import java.util.Map;

import javafx.scene.canvas.Canvas;
import paranoid.common.PlayerId;
import paranoid.controller.gameloop.GamePhase;
import paranoid.controller.gameloop.GameState;

public class KeyboardInputHandler implements InputHandler {

    private final Canvas canvas;
    private final Map<PlayerId, InputController> inputController;
    private final GameState gameState;

    public KeyboardInputHandler(final Map<PlayerId, InputController> inputController, final Canvas canvas, final GameState gameState) {
        this.inputController = inputController;
        this.canvas = canvas;
        this.gameState = gameState;
    }

    @Override
    public void notifyInputEvent()  {
        onKeyPressed();
        onKeyReleased();
    }

    private void onKeyPressed() {
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case RIGHT:
                inputController.get(PlayerId.ONE).notifyMoveRight(true);
                break;
            case LEFT:
                inputController.get(PlayerId.ONE).notifyMoveLeft(true);
                break;
            case D:
                inputController.get(PlayerId.TWO).notifyMoveRight(true);
                break;
            case A:
                inputController.get(PlayerId.TWO).notifyMoveLeft(true);
                break;
            case ESCAPE:
                gameState.setPhase(GamePhase.MENU);
            case SPACE:
                if (gameState.getPhase().equals(GamePhase.PAUSE)) {
                    gameState.setPhase(GamePhase.RUNNING);
                }
                break;
            default:
                break;
            }
        });
    }

    private void onKeyReleased() {
        canvas.setOnKeyReleased(e -> {
            switch (e.getCode()) {
            case RIGHT:
                inputController.get(PlayerId.ONE).notifyMoveRight(false);
                break;
            case LEFT:
                inputController.get(PlayerId.ONE).notifyMoveLeft(false);
                break;
            case D:
                inputController.get(PlayerId.TWO).notifyMoveRight(false);
                break;
            case A:
                inputController.get(PlayerId.TWO).notifyMoveLeft(false);
                break;
            default:
                break;
            }
        });
    }

}
