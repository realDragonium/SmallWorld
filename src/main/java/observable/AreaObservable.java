package Observable;

import Observer.AreaObserver;

public interface AreaObservable {
    void register(AreaObserver ao);
    void notifyObserver();
    boolean getActive();
    int getNumberOfFiches();
}
