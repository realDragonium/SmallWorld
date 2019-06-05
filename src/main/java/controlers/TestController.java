package controlers;

import javafx.scene.Scene;
import models.TestModel;
import views.TestView;

public class TestController {

    TestModel testModel;
    ApplicatieController appCon;

    public TestController(ApplicatieController appCon){
        testModel = new TestModel();
        new TestView(this);
        this.appCon = appCon;
    }

    public void changeScene(Scene scene){
        appCon.setActiveScene(scene);
    }

    public void loadScene(){
        appCon.setActiveScene(testModel.getScene());
    }
}
