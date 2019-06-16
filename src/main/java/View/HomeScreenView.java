package View;

import java.io.IOException;

import Controller.GameController;
import Controller.HomeScreenController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import observers.HomeScreenObserver;
import Managers.SceneManager;
import observable.HomeScreenObservable;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HomeScreenView implements HomeScreenObserver{

//	private Parent root;
	private Pane pane;
	private Group group;
	private Scene scene;
	private HomeScreenController hsCon;

	@FXML
	public Pane root;

    public HomeScreenView(HomeScreenController hsController, Group group){
    	this.group = group;
        hsCon = hsController;
        hsCon.register(this);
    }

	public void initialize() {
    	group.getChildren().add(root);
	}
    
    private void loadScene(){
//		try {
////			root = FXMLLoader.load(getClass().getResource("/HomeScreen/Homescreen.fxml"));
//			for(Node node : ((AnchorPane) root).getChildren()){
//				if(node.getId() != null) {
//					if(node.getId().equals("select")) {
//						((HBox) node).setOnMouseClicked(e -> {
//							new GameController();
//						});
//						((HBox) node).setOnMouseEntered(e -> {
//							enterHoverTitle(node);
//						});
//						((HBox) node).setOnMouseExited(e -> {
//							exitHoverTitle(node);
//						});
//					}
//				}
//			}
//
//			scene = new Scene(root);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }

    @FXML
    private void createGame(){
    	new GameController();
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
	public void update(HomeScreenObservable mvo) {
		System.out.println("mvo test message");
	}

	public void setPane(Pane pane) {
    	this.pane = pane;
    	pane.getChildren().addAll(root);
    }
    


    
    
	
}
