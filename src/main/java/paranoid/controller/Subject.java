package paranoid.controller;

public interface Subject {

    void register(Observer obs);

    void unregister(Observer obs);

    void notifyObserver();
}
