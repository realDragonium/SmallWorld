
package views;

import controlers.PlayerController;
import controlers.PlayerListController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PlayerListView {
	
	Pane root;
	PlayerListController playerListController;
	PlayerController[] players = new PlayerController[4];
	
	public PlayerListView(PlayerListController plController){
		playerListController = plController;
	}
	
	public void loadScene() {
		root.setMinSize(200, 600);
		root.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void setPane(Pane pane) {
		this.root = pane;
		loadScene();
	}
}
