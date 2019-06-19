package Observable;

import Observer.CombinationObserver;

public interface CombinationObservable {
    void register(CombinationObserver mvo);
    void unregister(CombinationObserver mvo);
    void notifyAllObservers();
    String getRaceId();
    String getPowerId();
}
