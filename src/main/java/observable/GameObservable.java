package observable;

import javafx.scene.SubScene;
import observers.GameObserver;

public interface GameObservable {
	void notifyObserver();
	void register(GameObserver go);
	SubScene getSubScene();
}
