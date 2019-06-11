package controlers;

import models.HomeScreenModel;
import observers.ModelViewObserver;

public class HomeScreenController {
	
    private HomeScreenModel hsModel = new HomeScreenModel();

    public HomeScreenController(){
        
    }
    
    public void register(ModelViewObserver mvo) {
    	hsModel.register(mvo);
    }
}
