package Observable;

import Observer.LobbyObserver;

import java.util.List;

public interface ObservableLobby {
    void register(LobbyObserver ob);
    void unregister(LobbyObserver mvo);
    void notifyAllObservers();
    List<String> getLobbyName();
}
