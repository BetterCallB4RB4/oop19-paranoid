package paranoid.controller.event;

import java.util.ArrayList;
import java.util.List;

import paranoid.common.Collision;
import paranoid.controller.gameLoop.GamePhase;
import paranoid.controller.gameLoop.GameState;
import paranoid.model.entity.Ball;

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
                    if (gameState.getWorld().getBalls().size() == 0 ) {
                        this.gameState.decLives();
                        if (this.gameState.getLives() == 0) {
                            this.gameState.setPhase(GamePhase.LOST);
                        } else {
                            this.gameState.setPhase(GamePhase.INIT);
                        }
                    }
                }
            } else if (ev instanceof HitBrickEvent) {
                HitBrickEvent hit = (HitBrickEvent) ev;
                gameState.setScore(gameState.getScore() 
                        + (hit.getBrick().getPointEarned() * gameState.getMultiplier()));
                hit.getBrick().decEnergy();
                if (hit.getBrick().getEnergy() == 0 && !hit.getBrick().isIndestructible()) {
                    gameState.getWorld().removeBrick(hit.getBrick());
                    if (gameState.getWorld().getBricks().size() == 0) {
                        gameState.setPhase(GamePhase.WIN);
                    }
                }
            }
        });
        events.clear();
    }

    /**
     * 
     * @param event to add to event queue
     */
    public void addEvent(final Event event) {
        this.events.add(event);
    }

}
