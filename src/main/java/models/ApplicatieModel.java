package models;

import javafx.scene.Scene;
import observers.ApplicatieObservable;
import observers.ApplicatieObserver;

import java.util.ArrayList;
import java.util.List;

public class ApplicatieModel implements ApplicatieObservable {
    private List<ApplicatieObserver> observers = new ArrayList<>();
    Scene scene;

    @Override
    public void register(ApplicatieObserver ao) {
        observers.add(ao);
    }

    @Override
    public void unregister(ApplicatieObserver ao) {
        observers.remove(ao);
    }

    @Override
    public void notifyAllObservers() {
        for(ApplicatieObserver ao : observers){
            ao.update(this);
        }
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    public void setScene(Scene scene){
        this.scene = scene;
        notifyAllObservers();
    }

}
