package observable;

import javafx.scene.Scene;
import observers.ApplicatieObserver;

public interface ApplicatieObservable {
    void register(ApplicatieObserver ao);
    void unregister(ApplicatieObserver ao);
    void notifyAllObservers();
    Scene getActiveScene();
}
