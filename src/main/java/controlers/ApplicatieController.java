package controlers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import models.ApplicatieModel;
import observers.ApplicatieObserver;
import views.ApplicatieView;

public class ApplicatieController{
    private ApplicatieModel applicatieModel;
    private HomeScreenController hsController;
    private AreaControler areaCon;
    private TestController testCon;


    public ApplicatieController(Stage s){
        applicatieModel = new ApplicatieModel();
        new ApplicatieView(s, this);
        hsController = new HomeScreenController(this);
        testCon = new TestController(this);
    }

    public void registerObserver(ApplicatieObserver ao){
        applicatieModel.register(ao);
    }

    public void setActiveScene(Scene s){
        applicatieModel.setScene(s);
    }

    public void changeSceneToTest() {
        testCon.loadScene();
    }

}
