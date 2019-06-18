package View;

import Controller.Map2DController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Map2DView{

	private Map2DController mapCon;
	private Pane graphic;
	private Group root = new Group();

	public Map2DView(Map2DController mapCon, Group group) {
		this.mapCon = mapCon;
		group.getChildren().add(root);
		makeStage();
	}
	
	private void makeStage() {
		mapLoader();
		makeAreas();
	}
	
	private void mapLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/UglyMap.fxml"));
        try {
			graphic = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
        root.getChildren().add(graphic);
	}
	
	private void makeAreas() {
        for (Node area : graphic.getChildren()) {
        	mapCon.createArea((Group) area);
        }
	}

}
