package View;

import Controller.GameController;
import Controller.HomeScreenController;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import observable.HomeScreenObservable;
import observers.HomeScreenObserver;

public class HomeScreenView implements HomeScreenObserver{

	private Pane pane;
	private Group group;
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

    @FXML
    private void createGame(){
    	new GameController();
		//new LeaderboardController();
	}

	@FXML
    private void enterHoverTitle(MouseEvent e) {
    	for(Node node2 : ((HBox) e.getSource()).getChildren()) {
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
	
    @FXML
	private void exitHoverTitle(MouseEvent e) {
		for(Node node2 : ((HBox)  e.getSource()).getChildren()) {
	    	if(node2.getId().equals("Power")) {
		
				TranslateTransition translateTransition = new TranslateTransition(); 
				translateTransition.setDuration(Duration.millis(200));
				translateTransition.setByX(-((ImageView)((HBox)  e.getSource()).getChildren().get(0)).getTranslateX());
				translateTransition.setCycleCount(1); 
				translateTransition.setAutoReverse(false); 
				translateTransition.setNode(((ImageView)((HBox)  e.getSource()).getChildren().get(0)));
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
