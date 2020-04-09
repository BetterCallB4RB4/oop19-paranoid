package paranoid.controller.event;

import java.util.ArrayList;
import java.util.List;

import paranoid.controller.gameLoop.GameState;

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

            } else if (ev instanceof HitBrickEvent) {
                HitBrickEvent hit = (HitBrickEvent) ev;
                gameState.setScore(gameState.getScore() 
                        + (hit.getBrick().getPointEarned() * gameState.getMultiplier()));
                hit.getBrick().decEnergy();
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
