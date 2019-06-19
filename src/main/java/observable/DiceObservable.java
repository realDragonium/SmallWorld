package Observable;

import Observer.DiceObserver;

public interface DiceObservable {
    void register(DiceObserver ob);
    void notifyAllObs();
    int getWaarde();

}
