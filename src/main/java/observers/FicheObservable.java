package observers;

import javafx.scene.transform.Translate;

public interface FicheObservable {
	public void register(FicheObserver fo);
	public void notifyAllObservers();
	public Translate getPosition();
}
