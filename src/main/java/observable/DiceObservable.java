package observable;

import observers.DiceObserver;

public interface DiceObservable {
    void register(DiceObserver ob);
    void notifyAllObs();
    int getWaarde();
}