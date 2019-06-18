package Observable;

import Observer.RoundObserver;

public interface RoundObservable {
    void register(RoundObserver ro);
    void notifyObservers();
    int getRound();
}
