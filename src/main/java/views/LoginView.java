package views;

import controlers.LoginController;
import observable.LoginObservable;
import observers.LoginObserver;
import managers.SceneManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class LoginView implements LoginObserver{
	SceneManager sceneManager;
	
	Scene scene;
	LoginController hsCon;

    public LoginView(SceneManager manager){
    	this.sceneManager = manager;
        hsCon = new LoginController();
        hsCon.register(this);
        loadScene();
    }
    
    private void loadScene(){
    	
    	 try {
             Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen/Loginscherm.fxml"));
             for(Node node : ((AnchorPane) root).getChildren()){
 				if(node.getId() != null) {
 					
 				}
             }
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
            	 hsCon.buttonLoginClicked(username, password);            	 
             });
             ((AnchorPane) root).getChildren().add(button);            
             scene = new Scene(root);
         } catch (Exception e) {
             e.printStackTrace();
         }
        
        sceneManager.setNewScene(scene);
    }

    private void goToHomeScreen() {
    	sceneManager.createHomeScreen();
    }

	@Override
	public void update(LoginObservable lo) {
		if(lo.getLoginState()) {
			goToHomeScreen();
		}
	}
}
