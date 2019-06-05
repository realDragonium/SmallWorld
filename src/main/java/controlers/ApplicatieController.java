package controlers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import models.ApplicatieModel;
import observers.ApplicatieObserver;
import views.ApplicatieView;

public class ApplicatieController{
    private ApplicatieModel applicatieModel;

    public ApplicatieController(Stage s){
        applicatieModel = new ApplicatieModel();
        new ApplicatieView(s, this);
    }

    public void registerObserver(ApplicatieObserver ao){
        applicatieModel.register(ao);
    }

    public void setActiveScene(Scene s){
        applicatieModel.setScene(s);
    }


}
