package observable;

import observers.ModelViewObserver;

public interface ModelViewObservable {
    void register(ModelViewObserver mvo);
    void unregister(ModelViewObserver mvo);
    void notifyAllObservers();
}
