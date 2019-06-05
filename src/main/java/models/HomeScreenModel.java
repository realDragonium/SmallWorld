package models;

import javafx.scene.Scene;
import observers.ApplicatieObservable;
import observers.ApplicatieObserver;

public class HomeScreenModel  implements ApplicatieObservable {
    @Override
    public void register(ApplicatieObserver ao) {

    }

    @Override
    public void unregister(ApplicatieObserver ao) {

    }

    @Override
    public void notifyAllObservers() {

    }

    @Override
    public Scene getScene() {
        return null;
    }
}
