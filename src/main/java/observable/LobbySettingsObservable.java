package Observable;

import Observer.LobbySettingsObserver;

public interface LobbySettingsObservable {
    void register(LobbySettingsObserver ob);
    void unregister(LobbySettingsObserver mvo);
    void notifyAllObservers();
}
