package paranoid.controller;

public interface SubjectScore {

    void register(ObserverScore obs);

    void unregister(ObserverScore obs);

    void notifyObserver();
}
