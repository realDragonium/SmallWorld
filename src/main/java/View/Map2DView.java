package View;

import Controller.Map2DController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Map2DView{

	private Map2DController mapCon;

	private Group graphic;
	private Pane pane;

	public Map2DView( Map2DController mapCon) {
		this.mapCon = mapCon;
		makeStage();
	}
	
	private void makeStage() {
		mapLoader();
		makeAreas();

	}
	public void setPane(Pane pane) {
		this.pane = pane;
		pane.getChildren().addAll(graphic);
	}
	
	private void mapLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/UglyMap.fxml"));
        try {
			graphic = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void makeAreas() {
        for (Node area : graphic.getChildren()) {
        	mapCon.createArea((Group) area);
        }
	}

}
