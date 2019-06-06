package observable;

import javafx.scene.transform.Translate;
import observers.FicheObserver;

public interface FicheObservable {
	public void register(FicheObserver fo);
	public void notifyAllObservers();
	public Translate getPosition();
}
