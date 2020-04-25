package paranoid.controller.event;

/**
 * 
 * interface for applying the pattern.
 */
public interface WorldEventListener {

    /**
     * add events to the queue that will be resolved with each iteration of gameLoop.
     * @param ev the event generated
     */
    void notifyEvent(Event ev);

}
