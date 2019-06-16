package View;

import Controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import observable.LoginObservable;
import observers.LoginObserver;

public class LoginView implements LoginObserver{
	@FXML
	private Pane root;
	@FXML
	private TextField Username;
	@FXML
	private TextField Password;
	@FXML
	private Button registeerButton;

	private Group group;
	private LoginController loginController;

    public LoginView(LoginController loginController, Group group){
    	this.group = group;
		this.loginController = loginController;
    }

	public void initialize() {
		group.getChildren().add(root);
		loginController.register(this);
	}

	@FXML
	private void login(){
		loginController.validateLoginInfo(Username.getText(), Password.getText());
	}

	@FXML
    private void goToHomeScreen() {
    	loginController.goToHomeScreen();
    }

    @FXML
	private void registeren(){
    	loginController.register(Username.getText(), Password.getText());
	}

	@Override
	public void update(LoginObservable lo) {
		if(lo.getLoginState()) {
			goToHomeScreen();
		}
	}
}
