package observable;

import observers.AreaObserver;

public interface AreaObservable {
	void register(AreaObserver mvo);
    void unregister(AreaObserver mvo);
    void notifyAllObservers();
    boolean isActive();
}
