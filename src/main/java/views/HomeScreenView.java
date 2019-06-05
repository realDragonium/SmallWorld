package views;

import controlers.HomeScreenController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import observers.ApplicatieObservable;
import observers.ApplicatieObserver;

public class HomeScreenView implements ApplicatieObserver {

    Scene scene;
    HomeScreenController hsCon;

    public HomeScreenView(HomeScreenController hsCon){
        loadScene();
        this.hsCon = hsCon;
        setMySceneAsMain();
    }

    @Override
    public void update(ApplicatieObservable ao) {
        scene = ao.getScene();
    }

    private void loadScene(){
        GridPane gPane = new GridPane();
        Button button = new Button("HomeMenu!!!");
        button.setOnAction(e-> changeSceneToTest());
        gPane.add(button, 0, 0);
        scene = new Scene(gPane, 400, 400);
    }

    private void setMySceneAsMain(){
        hsCon.changeScene(scene);
    }

    private void changeSceneToTest(){
        hsCon.changeSceneToTest();
    }
}
