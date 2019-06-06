package observable;

import observers.ModelViewObserver;
import javafx.scene.Scene;

public interface ModelViewObservable {
    void register(ModelViewObserver mvo);
    void unregister(ModelViewObserver mvo);
    void notifyAllObservers();
}
