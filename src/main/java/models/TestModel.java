package models;

import javafx.scene.Scene;
import observable.ApplicatieObservable;
import observers.ApplicatieObserver;

import java.util.ArrayList;
import java.util.List;

public class TestModel implements ApplicatieObservable {
    private List<ApplicatieObserver> observers = new ArrayList<>();

    public TestModel(){

    }

    public void changeScene(){
        notifyAllObservers();
    }

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
    public Scene getActiveScene() {
        return null;
    }

}
