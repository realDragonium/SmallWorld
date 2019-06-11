package controlers;

import models.HomeScreenModel;
import observers.HomeScreenObserver;

public class HomeScreenController {
	
    private HomeScreenModel hsModel = new HomeScreenModel();

    public HomeScreenController(){
        
    }
    
    public void register(HomeScreenObserver mvo) {
    	hsModel.register(mvo);
    }
}
