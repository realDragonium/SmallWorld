package Observable;

import Observer.HomeScreenObserver;

/**
 * This interface is the HomeScreenObservable which is implemented by the HomeScreenModel class.
 * @author
 * @version
 */

public interface HomeScreenObservable {

    /**
     * @param mvo registers an observer to the observable
     */
    void register(HomeScreenObserver mvo);

    /**
     * @param mvo unregisters an observer to the observable
     */
    void unregister(HomeScreenObserver mvo);

    /**
     * notifies an observer
     */
    void notifyAllObservers();

}
