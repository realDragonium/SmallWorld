package Observable;

import Observer.TimerObserver;

public interface TimerObservable {
    void register(TimerObserver to);
    void unregister(TimerObserver to);
    int getSeconds();
    void notifyAllObservers();
}
