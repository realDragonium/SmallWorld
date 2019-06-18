package observable;

import Observer.LobbyObserver;

public interface LobbyObservable {
    void register(LobbyObserver ob);
    void unregister(LobbyObserver mvo);
    void notifyAllObservers();
}

