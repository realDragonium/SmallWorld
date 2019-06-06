package observable;

import observers.GameObserver;
import javafx.scene.SubScene;

public interface GameObservable {
	void notifyObserver();
	void register(GameObserver go);
	SubScene getSubScene();
}
