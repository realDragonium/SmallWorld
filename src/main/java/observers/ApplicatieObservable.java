package observers;

import javafx.scene.Scene;

public interface ApplicatieObservable {
    void register(ApplicatieObserver ao);
    void unregister(ApplicatieObserver ao);
    void notifyAllObservers();
    Scene getActiveScene();
}
