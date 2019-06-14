package views;

import controlers.GameScreenController;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GameScreenView {
	
	GameScreenController gameScreenController;
	Pane gameScreen;
	
	public GameScreenView(GameScreenController gsController) {
		gameScreenController = gsController;	
	}
	
	public void setPane(Pane pane) {
    	this.gameScreen = pane;
    }
	
	public void addPane(Pane component) {
		gameScreen.getChildren().add(component);
	}
}
