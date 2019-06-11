package managers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import views.Applicatie;
import views.BeginScreenView;
import views.HomeScreenView;
import views.LoginView;

public class SceneManager {
	
	Applicatie app;
	
	public SceneManager(Applicatie app) {
		this.app = app;
	}

	public void createHomeScreen(){
		new HomeScreenView(this);
	}
	
	public void createLoginScreen() {
		new LoginView(this);
	}
	
	public void createBeginScreen() {
		new BeginScreenView(this);
	}
	
	public void createAreaInfo() {
		
	}
	
	public void createGame() {
		
	}
	
	public void setNewScene(Scene scene) {
		app.changeScene(scene);
	}
	
	public void addToScene(SubScene scene) {
		app.addToScene(scene);
	}

	public void removeScene(SubScene scene) {
		app.removeScene(scene);
		
	}
	
}
