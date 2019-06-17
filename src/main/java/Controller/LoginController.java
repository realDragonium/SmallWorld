package Controller;

import Firebase.FirebaseServiceOwn;
import Applicatie.Applicatie;
import Managers.SceneManager;
import models.LoginModel;
import observers.LoginObserver;
import Controller.HomeScreenController;

public class LoginController {
	private FirebaseServiceOwn fb = Applicatie.getFirebaseService();
    private LoginModel hsModel = new LoginModel();

    public LoginController(){
        SceneManager.getInstance().createLoginView(this);
    }
    
    public void validateLoginInfo(String username, String password) {
    	if(fb.login(username, password)) {
    		hsModel.loginAccepted(true);
    	}
    }

    public void register(String username, String password){
        if(fb.register(username, password)) {
            hsModel.loginAccepted(true);
        }
    }
    
    public void register(LoginObserver lo) {
    	hsModel.register(lo);
    }

	public void goToHomeScreen() {
		new HomeScreenController();
	}
}