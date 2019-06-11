package views;

import java.util.ArrayList;
import java.util.List;

import controlers.HomeScreenController;
import observable.ModelViewObservable;
import observable.SceneObservable;
import observers.ModelViewObserver;
import observers.SceneObserver;
import managers.SceneManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class BeginScreenView implements ModelViewObserver{
	private List<SceneObserver> sceneObserver = new ArrayList<>();
	SceneManager sceneManager;
	
	Scene scene;

    public BeginScreenView(SceneManager manager){
    	this.sceneManager = manager;
        loadScene();
    }
    
    private void loadScene(){
        GridPane gPane = new GridPane();
        Button button = new Button("Login");
        button.setOnAction(e->{
        	goToLoginScreen();
        });
        gPane.add(button, 0, 2);
        Group group = new Group();
        BorderPane bPane = new BorderPane();
        bPane.setLeft(gPane);
        group.getChildren().add(gPane);
        scene = new Scene(group, 400, 400);
        
        sceneManager.setNewScene(scene);
    }
    
    public void goToLoginScreen() {
    	sceneManager.createLoginScreen();
    }

	@Override
	public void update(ModelViewObservable mvo) {
		System.out.println("mvo test message");
	}
}
