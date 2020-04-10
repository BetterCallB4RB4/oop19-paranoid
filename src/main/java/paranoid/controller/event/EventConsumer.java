package paranoid.controller.event;

import java.util.ArrayList;
import java.util.List;

import paranoid.common.Collision;
import paranoid.controller.gameLoop.GamePhase;
import paranoid.controller.gameLoop.GameState;
import paranoid.model.entity.Ball;
import paranoid.model.entity.Brick;

public class EventConsumer {

    private List<Event> events = new ArrayList<>();
    private GameState gameState;

    public EventConsumer(final GameState gameState) {
        this.gameState = gameState;
    }
    /**
     * each event will have specific behavior based on its instance.
     */
    public void resolveEvent() {
        events.stream().forEach(ev -> {
            if (ev instanceof HitBorderEvent) {
                HitBorderEvent hit = (HitBorderEvent) ev;
                if (hit.getCollision().equals(Collision.BOTTOM)) {
                    gameState.getWorld().removeBall(hit.getBall());
                    if (gameState.getWorld().getBalls().isEmpty()) {
                        gameState.decLives();
                        gameState.setPhase(GamePhase.INIT);
                    }
                }
            } else if (ev instanceof HitBrickEvent) {
                Brick brick = ((HitBrickEvent) ev).getBrick();
                gameState.setScore(gameState.getScore() 
                        + (brick.getPointEarned() * gameState.getMultiplier()));
                brick.decEnergy();
                if (brick.getEnergy() == 0 && !brick.isIndestructible()) {
                    gameState.getWorld().removeBrick(brick);
                }
            }
        });
        isOver();
        events.clear();
    }

    /**
     * 
     * @param event to add to event queue
     */
    public void addEvent(final Event event) {
        this.events.add(event);
    }

    private void isOver() {
        if (gameState.getLives() == 0) {
            gameState.setPhase(GamePhase.LOST);
        } else if (gameState.getWorld().getBricks().stream()
            .filter(i -> !i.isIndestructible()).count() == 0) {
            gameState.setPhase(GamePhase.WIN);
           }
    }
}
