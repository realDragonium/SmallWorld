package Observable;

import Observer.TurnObserver;
import Enum.TurnFase;

/**
 * This interface is the TurnObservable which is implemented by the TurnModel class.
 * @author Martijn van der Steen
 * @version June 2019
 */
public interface TurnObservable {

    /**
     * @param to registers an observer to the observable
     */
    void register(TurnObserver to);

    /**
     * Notifies an observer
     */
    void notifyObservers();

    /**
     * @return an integer of the turn
     */
    int getTurn();

    /**
     * @return the Turnfase of a turn.
     */
    TurnFase getFase();
}
