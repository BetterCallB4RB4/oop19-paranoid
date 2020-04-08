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
                this.gameState.setLives(0);

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
