package paranoid.controller.gameloop;

import java.util.Map;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.scene.Scene;
import paranoid.common.PlayerId;
import paranoid.controller.fxmlcontroller.GameController;
import paranoid.controller.fxmlcontroller.GameOverController;
import paranoid.controller.fxmlcontroller.NextLevelController;
import paranoid.model.component.input.InputController;
import paranoid.model.component.input.InputHandler;
import paranoid.model.component.input.KeyboardInputController;
import paranoid.model.component.input.KeyboardInputHandler;
import paranoid.model.entity.World;
import paranoid.model.level.LevelSelection;
import paranoid.model.level.MusicPlayer;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.model.settings.SettingsManager;
import paranoid.view.parameters.LayoutManager;

/**
 * class that represents the application of the game loop pattern 
 * divided into process update and render.
 */
public class GameLoop implements Runnable {

    private static final int PERIOD = 20;

    private final Scene scene;
    private final World world;
    private final GameState gameState;
    private final GameController gameController;
    private final Map<PlayerId, InputController> inputController = new HashMap<>();
    private final MusicPlayer player;

    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.gameState = new GameState();
        this.world = gameState.getWorld();
        this.player = new MusicPlayer();
        this.gameController = (GameController) LayoutManager.GAME.getGuiController();
        this.gameController.setBackGroundImage(gameState.getLevel().getBackGround());
        this.player.setMusicEnable(gameState.isMusicActive());
        this.player.setEffectEnable(gameState.isEffectActive());
        this.world.getEventHanlder().addMusicPlayer(player);
        this.player.playMusic(gameState.getLevel().getMusic());
        this.changeView(LayoutManager.GAME);
        this.inputController.put(PlayerId.ONE, new KeyboardInputController());
        this.inputController.put(PlayerId.TWO, new KeyboardInputController());
        final InputHandler inputHandler = new KeyboardInputHandler(this.inputController, this.gameController.getCanvas(), this.gameState);
        inputHandler.notifyInputEvent();
    }

    /**
     * apply the three game loop steps based on the game phase.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (!gameState.getPhase().equals(GamePhase.WIN) 
            && !gameState.getPhase().equals(GamePhase.LOST)
            && !gameState.getPhase().equals(GamePhase.MENU)) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            switch (gameState.getPhase()) {
            case INIT:
                gameState.init();
                break;
            case PAUSE:
                this.gameController.setPause(true);
                render();
                break;
            case RUNNING:
                this.gameController.setPause(false);
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
        player.stopMusic();
        if (gameState.getPhase().equals(GamePhase.WIN)
        && LevelSelection.isStoryLevel(gameState.getLevel().getLevelName()) 
        && LevelSelection.getSelectionFromLevel(gameState.getLevel()).hasNext()) {
            saveState(GamePhase.WIN);
            changeView(LayoutManager.NEXT_LEVEL);
        } else if (gameState.getPhase().equals(GamePhase.MENU)) {
            changeView(LayoutManager.MENU);
        } else { 
            saveState(GamePhase.LOST);
            changeView(LayoutManager.GAME_OVER);
        }
    }

    /**
     * set the gui to show the post game screen.
     * @param layoutManager 
     */
    private void changeView(final LayoutManager layoutManager) {
        if (layoutManager.equals(LayoutManager.NEXT_LEVEL)) {
            final NextLevelController nextLevelController = (NextLevelController) layoutManager.getGuiController();
            nextLevelController.update(gameState.getLevel(), gameState.getUser());
        } else if (layoutManager.equals(LayoutManager.GAME_OVER)) {
            final GameOverController gameOverController = (GameOverController) layoutManager.getGuiController();
            gameOverController.updateScore(gameState.getTopScores(), gameState.getUser(), gameState.getLevel());
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scene.setRoot(layoutManager.getLayout());
            }
        });
    }

    /**
     * stores the game progression as a checkpoint.
     * @param phase to set 
     */
    private void saveState(final GamePhase phase) {
        final SettingsBuilder settingsBuilder = new SettingsBuilder();
        if (phase.equals(GamePhase.WIN)) {
            UserManager.saveUser(gameState.getUser());
            SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                           .selectLevel(LevelSelection.getSelectionFromLevel(gameState.getLevel()).next().getLevel())
                           .build());
        } else if (phase.equals(GamePhase.LOST)) {
            UserManager.saveUser(new User());
            if (LevelSelection.isStoryLevel(gameState.getLevel().getLevelName())) {
                SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                               .selectLevel(LevelSelection.LEVEL1.getLevel())
                               .build());
            }

        }
    }
    /**
     * pauses the thread based on the difference between 
     * the input time and the computational time 
     * of the 3 steps of the game loop.
     * @param current the execution time before the computational time
     */
    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
                try {
                    Thread.sleep(PERIOD - dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
     * update world physics.
     * @param elapsed game physics is updated
     */
    private void updateGame(final int elapsed) {
        world.getEventHanlder().resolveEvent();
        world.updateState(elapsed);
    }

    /**
     * draw the world elements on the screen.
     */
    private void render() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameController.render(world.getSceneEntities(), gameState.getHighScoreValue(), 
                                      gameState.getPlayerScore(), gameState.getLives());
            }
        });
    }
}
