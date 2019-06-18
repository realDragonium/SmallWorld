package observable;

import Observer.LobbyObserver;

import java.util.List;

public interface LobbyObservable {
    void register(LobbyObserver ob);
    void unregister(LobbyObserver mvo);
    void notifyAllObservers();
    List<String> getLobbyName();
}

