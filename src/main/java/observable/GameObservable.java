package Observable;

import Observer.GameObserver;
import javafx.scene.SubScene;

public interface GameObservable {
	void notifyObserver();
	void register(GameObserver go);
	SubScene getSubScene();
}
