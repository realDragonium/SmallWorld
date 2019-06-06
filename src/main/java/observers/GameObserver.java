package observers;

import observable.GameObservable;

public interface GameObserver {
	void update(GameObservable go);
}
