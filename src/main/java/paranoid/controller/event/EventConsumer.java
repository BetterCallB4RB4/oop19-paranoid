package paranoid.controller.event;

import java.util.ArrayList;
import java.util.List;

public class EventConsumer {

    private List<Event> events = new ArrayList<>();

    /**
     * each event will have specific behavior based on its instance.
     */
    public void resolveEvent() {
        events.stream().forEach(ev -> {

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
