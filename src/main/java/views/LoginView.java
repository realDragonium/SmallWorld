package views;

import controlers.LoginController;
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
	
	Scene scene;
	LoginController loginController;
	Pane pane;
	Parent root;

    public LoginView(LoginController loginController){
    	this.loginController = loginController;
        loginController.register(this);
        loadScene();
    }
    
    public void setPane(Pane pane) {
    	this.pane = pane;
    	pane.getChildren().addAll(root);
    }
    
    private void loadScene(){
    	
    	 try {
             root = FXMLLoader.load(getClass().getResource("/LoginScreen/Loginscherm.fxml"));
             Button button = new Button();
             button.setBackground(Background.EMPTY);
             button.setMinHeight(85);
             button.setMinWidth(275);
             button.setLayoutX(813);
             button.setLayoutY(678);
             button.setOnAction(e->{
            	 String username = "";
            	 String password = "";
            	 for(Node node : ((AnchorPane) root).getChildren()) {
                	 if(node.getId() != null) {
	                	 if(node.getId().equals("Username")) {
	                		 username = ((TextField) node).getText();
	                	 }
	                	 if(node.getId().equals("Password")) {
	                		 password = ((PasswordField) node).getText();
	                	 }
                	 }
                 }
            	 loginController.buttonLoginClicked(username, password);            	 
             });
             ((AnchorPane) root).getChildren().add(button);            
             scene = new Scene(root);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    

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
