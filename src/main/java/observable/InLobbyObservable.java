package Observable;

import Observer.InLobbyObserver;


public interface InLobbyObservable {
    void notifyAllObservers();
    void register(InLobbyObserver ob);
    void unregister(InLobbyObserver ob);

    boolean getStart();
}
