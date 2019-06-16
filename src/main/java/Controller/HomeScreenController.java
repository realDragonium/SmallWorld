package Controller;

import Managers.SceneManager;
import models.HomeScreenModel;
import observers.HomeScreenObserver;

public class HomeScreenController {
	
    private HomeScreenModel hsModel = new HomeScreenModel();

    public HomeScreenController(){
        SceneManager.getInstance().createHomeScreenView(this);
    }
    
    public void register(HomeScreenObserver mvo) {
    	hsModel.register(mvo);
    }
}
