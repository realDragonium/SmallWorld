package controlers;

import models.LoginModel;
import observers.LoginObserver;

public class LoginController {
	
    private LoginModel hsModel = new LoginModel();

    public LoginController(){
        
    }
    
    public void buttonLoginClicked(String username, String password) {
    	validateLoginInfo(username, password);
    }
    
    public void validateLoginInfo(String username, String password) {
    	//checkInfoFirebase();
    	//checkInfoView();
    	if(true) {
    		hsModel.loginAccepted(true);
    	}
    }
    
    public void register(LoginObserver lo) {
    	hsModel.register(lo);
    }
}