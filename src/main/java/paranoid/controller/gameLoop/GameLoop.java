package paranoid.controller.gameLoop;

import java.util.Map;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.scene.Scene;
import paranoid.common.PlayerId;
import paranoid.controller.GameController;
import paranoid.controller.GameOverController;
import paranoid.controller.NextLevelController;
import paranoid.model.component.input.InputController;
import paranoid.model.component.input.KeyboardInputController;
import paranoid.model.entity.World;
import paranoid.model.level.Level;
import paranoid.model.level.LevelSelection;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.Settings;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.model.settings.SettingsManager;
import paranoid.view.parameters.LayoutManager;

public class GameLoop implements Runnable {

    private static final int PERIOD = 20;
    private final Scene scene;
    private final GameController gameController = (GameController) LayoutManager.GAME.getGuiController();
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
                this.gameController.isPause(true);
                render();
                break;
            case RUNNING:
                this.gameController.isPause(false);
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

        if (gameState.getPhase() == GamePhase.WIN 
                && LevelSelection.isStoryLevel(gameState.getLevel().getLevelName()) 
                && LevelSelection.getSelectionFromLevel(gameState.getLevel()).hasNext()) {
            changeView(LayoutManager.NEXT_LEVEL);
        } else {
            changeView(LayoutManager.GAME_OVER);
        }
    }

    private void changeView(final LayoutManager lm) {
        SettingsBuilder settingsBuilder = new SettingsBuilder();

        if (lm.equals(LayoutManager.NEXT_LEVEL)) {
            UserManager.saveUser(gameState.getUser());
            SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                    .selectLevel(LevelSelection.getSelectionFromLevel(gameState.getLevel()).next().getLevel())
                    .build());
            NextLevelController nlc = (NextLevelController) lm.getGuiController();
            nlc.update(gameState.getLevel(), gameState.getUser());
        } else if (lm.equals(LayoutManager.GAME_OVER)) {
            UserManager.saveUser(new User());
            if (LevelSelection.isStoryLevel(gameState.getLevel().getLevelName())) {
                SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                        .selectLevel(LevelSelection.LEVEL1.getLevel())
                        .build());
            }
            GameOverController goc = (GameOverController) lm.getGuiController();
            goc.updateScore(gameState.getTopScores(), gameState.getUser(), gameState.getLevel());
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameController.getMusicPlayer().stopMusic();
                scene.setRoot(lm.getLayout());
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
            case SPACE:
                if (gameState.getPhase().equals(GamePhase.PAUSE)) {
                    gameState.setPhase(GamePhase.RUNNING);
                }
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
            default:
                break;
            }
        });
    }
}
