package View;

import Controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import observable.LoginObservable;
import observers.LoginObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class LoginView implements LoginObserver{

	@FXML
	public Pane root;
	@FXML
	public TextField Username;
	@FXML
	public TextField Password;

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
    private void goToHomeScreen() {
    	loginController.goToHomeScreen();
    }

	@Override
	public void update(LoginObservable lo) {
		if(lo.getLoginState()) {
			goToHomeScreen();
		}
	}
}
