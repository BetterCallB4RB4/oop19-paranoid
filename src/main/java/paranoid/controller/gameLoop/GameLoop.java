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
import paranoid.model.component.input.InputHandler;
import paranoid.model.component.input.KeyboardInputController;
import paranoid.model.component.input.KeyboardInputHandler;
import paranoid.model.entity.World;
import paranoid.model.level.BackGround;
import paranoid.model.level.LevelSelection;
import paranoid.model.level.Music;
import paranoid.model.level.MusicPlayer;
import paranoid.model.score.User;
import paranoid.model.score.UserManager;
import paranoid.model.settings.Settings.SettingsBuilder;
import paranoid.model.settings.SettingsManager;
import paranoid.view.parameters.LayoutManager;

public class GameLoop implements Runnable {

    private static final int PERIOD = 20;

    private final Scene scene;
    private final World world;
    private final GameState gameState;
    private final GameController gameController;
    private final InputHandler inputHandler;
    private final Map<PlayerId, InputController> inputController = new HashMap<>();
    private final MusicPlayer player;

    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.gameState = new GameState();
        this.world = gameState.getWorld();
        this.player = new MusicPlayer();
        this.gameController = (GameController) LayoutManager.GAME.getGuiController();
        this.gameController.setBackGroundImage(BackGround.getBackGroundByName(gameState.getLevel().getBackGround()));
        this.player.setMusicEnable(gameState.isMusicActive());
        this.player.setEffectEnable(gameState.isEffectActive());
        this.world.getEventHanlder().addMusicPlayer(player);
        this.player.playMusic(Music.getMusicByName(gameState.getLevel().getMusic()));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scene.setRoot(LayoutManager.GAME.getLayout());
            }
        });
        this.inputController.put(PlayerId.ONE, new KeyboardInputController());
        this.inputController.put(PlayerId.TWO, new KeyboardInputController());
        this.inputHandler = new KeyboardInputHandler(this.inputController, this.gameController.getCanvas(), this.gameState);
        this.inputHandler.notifyInputEvent();
    }

    /**
     * 
     * follows the 3 steps of the gameLoop pattern.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (!gameState.getPhase().equals(GamePhase.WIN) 
            && !gameState.getPhase().equals(GamePhase.LOST)
            && !gameState.getPhase().equals(GamePhase.MENU)) {
            long current = System.currentTimeMillis();
            int elapsed = (int) (current - lastTime);
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

        if (gameState.getPhase().equals(GamePhase.WIN)
        && LevelSelection.isStoryLevel(gameState.getLevel().getLevelName()) 
        && LevelSelection.getSelectionFromLevel(gameState.getLevel()).hasNext()) {
            changeView(LayoutManager.NEXT_LEVEL);
        } else if (gameState.getPhase().equals(GamePhase.MENU)) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    player.stopMusic();
                    scene.setRoot(LayoutManager.MENU.getLayout());
                }
            });
        } else { 
            changeView(LayoutManager.GAME_OVER);
        }
    }

    private void changeView(final LayoutManager layoutManager) {
        final SettingsBuilder settingsBuilder = new SettingsBuilder();
        if (layoutManager.equals(LayoutManager.NEXT_LEVEL)) {
            UserManager.saveUser(gameState.getUser());
            SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                           .selectLevel(LevelSelection.getSelectionFromLevel(gameState.getLevel()).next().getLevel())
                           .build());
            final NextLevelController nextLevelController = (NextLevelController) layoutManager.getGuiController();
            nextLevelController.update(gameState.getLevel(), gameState.getUser());
        } else if (layoutManager.equals(LayoutManager.GAME_OVER)) {
            UserManager.saveUser(new User());
            if (LevelSelection.isStoryLevel(gameState.getLevel().getLevelName())) {
                SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                               .selectLevel(LevelSelection.LEVEL1.getLevel())
                               .build());
            }
            final GameOverController gameOverController = (GameOverController) layoutManager.getGuiController();
            gameOverController.updateScore(gameState.getTopScores(), gameState.getUser(), gameState.getLevel());
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                player.stopMusic();
                scene.setRoot(layoutManager.getLayout());
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
        final long dt = System.currentTimeMillis() - current;
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
}
