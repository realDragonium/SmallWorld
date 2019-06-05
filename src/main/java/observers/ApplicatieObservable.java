package observers;

import javafx.scene.Scene;

public interface ApplicatieObservable {
    public void register(ApplicatieObserver ao);
    public void unregister(ApplicatieObserver ao);
    public void notifyAllObservers();
    public Scene getScene();
}
