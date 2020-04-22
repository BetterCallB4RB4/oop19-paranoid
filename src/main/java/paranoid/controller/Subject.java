package paranoid.controller;

public interface Subject {

    /**
     * adds an observer to the list of items to be notified of a change.
     * @param obs added
     */
    void register(Observer obs);

    /**
     * remove an observer to the list.
     * @param obs removed
     */
    void unregister(Observer obs);

    /**
     * notifies all observers of a change.
     */
    void notifyObserver();
}
