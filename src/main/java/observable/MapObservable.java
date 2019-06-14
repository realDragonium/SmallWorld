package observable;

import controlers.AreaController;
import observers.MapObserver;

public interface MapObservable {
	void register(MapObserver mvo);
    void unregister(MapObserver mvo);
    void notifyAllObservers();
    AreaController getActiveArea();
}
