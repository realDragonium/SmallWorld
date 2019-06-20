package Observable;

import Observer.TurnObserver;
import Enum.TurnFase;

public interface TurnObservable {
    void register(TurnObserver to);
    void notifyObservers();
    int getTurn();
    TurnFase getFase();
}
