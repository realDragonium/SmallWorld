package controlers;

import javafx.scene.Scene;
import models.HomeScreenModel;
import views.HomeScreenView;

public class HomeScreenController{
    private ApplicatieController appCon;
    private HomeScreenModel hsModel;

    HomeScreenController(ApplicatieController appCon){
        this.appCon = appCon;
        new HomeScreenView(this);
        hsModel = new HomeScreenModel();
    }

    public void changeScene(Scene scene){
        appCon.setActiveScene(scene);
    }
    public void changeSceneToTest() {
        appCon.changeSceneToTest();
    }
}
