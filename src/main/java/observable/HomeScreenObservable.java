package observable;

import observers.HomeScreenObserver;

public interface HomeScreenObservable {
    void register(HomeScreenObserver mvo);
    void unregister(HomeScreenObserver mvo);
    void notifyAllObservers();
}
