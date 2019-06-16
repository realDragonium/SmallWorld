package Observable;

import Observer.PlayerObserver;

public interface PlayerObservable {
    void register(PlayerObserver po);
    void notifyObserver();
    int getFiches();
    int getPunten();
}
