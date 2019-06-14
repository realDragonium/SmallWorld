package views;

import controlers.MapController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MapView {
	
	MapController mapController;
	Pane root;

	public MapView(MapController mapController) {
		this.mapController = mapController;
		loadScene();
	}
	
	public void loadScene() {
		root = new Pane();
		root.setMinSize(1200, 600);	
		root.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void setPane(Pane pane) {
		pane.getChildren().add(root);
	}
}
