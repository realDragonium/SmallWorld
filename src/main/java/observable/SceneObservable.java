package observable;

import javafx.scene.Scene;
import observers.SceneObserver;

public interface SceneObservable {
    void registerSO(SceneObserver so);
    void unregisterSO(SceneObserver so);
    void notifyObserverSO();
    Scene getSceneSO();
}
