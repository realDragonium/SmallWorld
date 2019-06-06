package controlers;

import models.HomeScreenModel;
import Observer.ModelViewObserver;

public class HomeScreenController {

    private ApplicatieController appCon;
    private HomeScreenModel hsModel = new HomeScreenModel();

    public HomeScreenController(){
        this.appCon = ApplicatieController.getInstance();
    }
    
    public void register(ModelViewObserver mvo) {
    	hsModel.register(mvo);
    }

    
}
