package observers;

import javafx.scene.Scene;

public interface ApplicatieObserable {
    public void register(ApplicatieObserver ao);
    public void unregister(ApplicatieObserver ao);
    public void notifyAllObservers();
    public Scene getScene();
}
