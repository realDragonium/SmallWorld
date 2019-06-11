package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controlers.HomeScreenController;
import observable.ModelViewObservable;
import observable.SceneObservable;
import observers.ModelViewObserver;
import observers.SceneObserver;
import managers.SceneManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class HomeScreenView implements ModelViewObserver{
	private List<SceneObserver> sceneObserver = new ArrayList<>();
	SceneManager sceneManager;
	
	Scene scene;
    HomeScreenController hsCon;

    public HomeScreenView(SceneManager manager){
    	this.sceneManager = manager;
        hsCon = new HomeScreenController();
        hsCon.register(this);
        loadScene();
    }
    
    private void loadScene(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/HomeScreen/Homescreen.fxml"));
			for(Node node : ((AnchorPane) root).getChildren()){
				if(node.getId() != null) {
					if(node.getId().equals("select")) {
						((HBox) node).setOnMouseEntered(e -> {
							enterHoverTitle(node);
						});
						((HBox) node).setOnMouseExited(e -> {
							exitHoverTitle(node);
						});
					}
				}
			}
			
			scene = new Scene(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
        sceneManager.setNewScene(scene);
    }

    private void enterHoverTitle(Node node) {
    	for(Node node2 : ((HBox) node).getChildren()) {
	    	if(node2.getId().equals("Power")) {
		    	TranslateTransition translateTransition = new TranslateTransition(); 
				translateTransition.setDuration(Duration.millis(500));
				translateTransition.setByX(-50 - ((ImageView)node2).getTranslateX()); 
				translateTransition.setCycleCount(1); 
				translateTransition.setAutoReverse(false); 
				translateTransition.setNode((ImageView)node2);
				translateTransition.play();
	    	}
			if(node2.getId().equals("Ras")) {
				TranslateTransition translateTransition2 = new TranslateTransition(); 
				translateTransition2.setDuration(Duration.millis(500));
				translateTransition2.setByX(10 - ((ImageView) node2).getTranslateX()); 
				translateTransition2.setCycleCount(1); 
				translateTransition2.setAutoReverse(false); 
				translateTransition2.setNode(node2);
				translateTransition2.play();
			}
	    }
    }
	
    
	private void exitHoverTitle(Node node) {
		for(Node node2 : ((HBox) node).getChildren()) {
	    	if(node2.getId().equals("Power")) {
		
				TranslateTransition translateTransition = new TranslateTransition(); 
				translateTransition.setDuration(Duration.millis(200));
				translateTransition.setByX(-((ImageView)((HBox) node).getChildren().get(0)).getTranslateX()); 
				translateTransition.setCycleCount(1); 
				translateTransition.setAutoReverse(false); 
				translateTransition.setNode(((ImageView)((HBox) node).getChildren().get(0)));
				translateTransition.play();
	    	}
	    	if(node2.getId().equals("Ras")) {
				TranslateTransition translateTransition2 = new TranslateTransition(); 
				translateTransition2.setDuration(Duration.millis(200));
				translateTransition2.setByX(-((ImageView)node2).getTranslateX()); 
				translateTransition2.setCycleCount(1); 
				translateTransition2.setAutoReverse(false); 
				translateTransition2.setNode((ImageView)node2);
				translateTransition2.play();
	    	}
		}
	}

	private void startGame() {
    	
    }

	@Override
	public void update(ModelViewObservable mvo) {
		System.out.println("mvo test message");
	}
    


    
    
	
}
