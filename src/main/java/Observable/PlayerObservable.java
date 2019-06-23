package Observable;

import Controller.CombinationController;
import Observer.PlayerObserver;

public interface PlayerObservable {
    void register(PlayerObserver po);
    void notifyObserver();
    int getFiches();
    int getPunten();
    CombinationController getActive();
    CombinationController getDeprec();
    String getId();
}
