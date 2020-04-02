package paranoid.controller.gameLoop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Scene;
import paranoid.common.P2d;
import paranoid.common.V2d;
import paranoid.controller.GameController;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Border;
import paranoid.model.entity.World;
import paranoid.view.parameters.LayoutManager;

public class GameLoop implements Runnable {

    private static final int PERIOD = 20;
    private final Scene scene;
    private final GameController gameController;

    private World world;

    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.scene.setRoot(LayoutManager.GAME.getLayout());
        this.gameController = (GameController) LayoutManager.GAME.getGuiController();
        List<Ball> ballContainer = new ArrayList<>();
        ballContainer.add(new Ball(new P2d(20, 20), new V2d(100, 100), 10, 10));
        this.world = new World(ballContainer, new Border(60, 60));
    }

    /**
     * 
     * follows the 3 steps of the gameLoop pattern.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (true) {
            long current = System.currentTimeMillis();
            int elapsed = (int) (current - lastTime);
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(current);
            lastTime = current;
        }
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

    }

    /**
     * 
     * @param elapsed game physics is updated
     */
    private void updateGame(final int elapsed) {

    }

    private void render() {
        gameController.render(world.getSceneEntities());
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                gameController.render(world.getSceneEntities());
//            }
//        });
    }

}
