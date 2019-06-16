package Observer;

import Observable.TurnObservable;

public interface TurnObserver {
    void update(TurnObservable to);
}
