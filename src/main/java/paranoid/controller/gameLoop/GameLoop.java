package paranoid.controller.gameLoop;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import paranoid.common.P2d;
import paranoid.common.PlayerId;
import paranoid.common.V2d;
import paranoid.common.dimension.ScreenConstant;
import paranoid.controller.GameController;
import paranoid.controller.GameOverController;
import paranoid.model.component.input.InputController;
import paranoid.model.component.input.KeyboardInputController;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.Brick;
import paranoid.model.entity.Player;
import paranoid.model.entity.World;
import paranoid.view.parameters.LayoutManager;

public class GameLoop implements Runnable {

    private static final int PERIOD = 20;
    private final Scene scene;
    private final GameController gameController = (GameController) LayoutManager.GAME.getGuiController();
    private final GameOverController gameOverController = (GameOverController) LayoutManager.GAME_OVER.getGuiController();
    private final Map<PlayerId, InputController> inputController = new HashMap<>();
    private World world;
    private GameState gameState;

    public GameLoop(final Scene scene) {
        this.scene = scene;
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                scene.setRoot(LayoutManager.GAME.getLayout());
            }
        });
        this.inputController.put(PlayerId.ONE, new KeyboardInputController());
        this.inputController.put(PlayerId.TWO, new KeyboardInputController());
        this.gameState = new GameState();
        this.world = gameState.getWorld();
        /*
        List<Ball> ballContainer = new ArrayList<>();
        List<Brick> brickContainer = new ArrayList<>();
        List<Player> playerContainer = new ArrayList<>();

        ballContainer.add(new Ball(new P2d(330, 500), new V2d(100, -200), 1, 10, 10));
        brickContainer.add(new Brick.Builder().position(new P2d(50, 100))
                    .width(80)
                    .height(40)
                    .color(Color.DARKGREEN)
                    .destructible(true)
                    .energy(1)
                    .pointEarned(10)
                    .build());
        playerContainer.add(new Player.Builder().position(new P2d(350,500))
                .width(80)
                .height(10)
                .color(Color.DARKGREEN)
                .playerId(PlayerId.ONE)
                .build());

        playerContainer.add(new Player.Builder().position(new P2d(290,500))
                .width(80)
                .height(10)
                .color(Color.RED)
                .playerId(PlayerId.TWO)
                .build());
        this.world = new World(ballContainer, brickContainer, playerContainer, new Border(ScreenConstant.WORLD_WIDTH,
                ScreenConstant.WORLD_HEIGHT));
        */
        notifyInputEvent();
    }

    /**
     * 
     * follows the 3 steps of the gameLoop pattern.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (gameState.getPhase() != GamePhase.WIN && gameState.getPhase() != GamePhase.LOST) {
            long current = System.currentTimeMillis();
            int elapsed = (int) (current - lastTime);

            switch (gameState.getPhase()) {
            case INIT:
                gameState.init();
                render();
                break;
            case PAUSE:
                render();
                break;
            case RUNNING:
                processInput();
                updateGame(elapsed);
                render();
                break;
            default:
                break;
            }
            waitForNextFrame(current);
            lastTime = current;
        }
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                gameController.getMusicPlayer().stopMusic();
                scene.setRoot(LayoutManager.GAME_OVER.getLayout());
                gameOverController.updateScore(gameState.getPlayerScore());
            }
        });
    }

    /**
     * 
     * @param current the execution time before the computational time
     * 
     * pauses the thread based on the difference between 
     * the input time and the computational time 
     * of the 3 steps of the game loop
     */
    private void waitForNextFrame(final long current) {
        long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (Exception ex) {
            }
        }
    }

    /**
     * 
     * keyboard commands are executed.
     */
    private void processInput() {
        inputController.entrySet().forEach(i -> {
            world.movePlayer(i.getKey(), i.getValue());
        });
    }

    /**
     * 
     * @param elapsed game physics is updated
     */
    private void updateGame(final int elapsed) {
        world.getEventHanlder().resolveEvent();
        world.updateState(elapsed);
    }

    private void render() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameController.render(world.getSceneEntities(), gameState.getHighScoreValue(), 
                        gameState.getPlayerScore(), gameState.getLives());
            }
        });
    }

    private void notifyInputEvent() {

        this.scene.setOnKeyPressed(e -> {
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
            default:
                break;
            }
        });

        this.scene.setOnKeyReleased(e -> {
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
            case SPACE:
                if (gameState.getPhase().equals(GamePhase.PAUSE)) {
                    gameState.setPhase(GamePhase.RUNNING);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            gameController.isPause(false);
                        }
                    });
                } else {
                    gameState.setPhase(GamePhase.PAUSE);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            gameController.isPause(true);
                        }
                    });
                }
                break;
            default:
                break;
            }
        });
    }
}
