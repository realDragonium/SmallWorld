package controlers;

import javafx.scene.Scene;
import models.ApplicatieModel;
import observers.ApplicatieObserver;

public class ApplicatieController{
    private ApplicatieModel applicatieModel;

    public ApplicatieController(){
        applicatieModel = new ApplicatieModel();
    }

    public void registerObserver(ApplicatieObserver ao){
        applicatieModel.register(ao);
    }

    public void setActiveScene(Scene s){
        applicatieModel.setScene(s);
    }


}
