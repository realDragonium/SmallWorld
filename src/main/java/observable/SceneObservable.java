package Observable;

import Observer.SceneObserver;
import javafx.scene.Scene;

public interface SceneObservable {
    void registerSO(SceneObserver so);
    void unregisterSO(SceneObserver so);
    void notifyObserverSO();
    Scene getSceneSO();
}
