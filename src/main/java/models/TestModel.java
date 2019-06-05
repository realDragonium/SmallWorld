package models;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import observers.ApplicatieObservable;
import observers.ApplicatieObserver;

public class TestModel implements ApplicatieObservable {

    Scene scene;

    public TestModel(){
        loadScene();
    }

    private void loadScene(){
        GridPane gPane = new GridPane();
        Button button = new Button("BOE!!!");
        gPane.add(button, 0, 0);
        scene = new Scene(gPane, 400, 400);
    }

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
        return scene;
    }
}
