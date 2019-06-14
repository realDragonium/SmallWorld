package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlers.Map2DController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import managers.SceneManager;

public class Map2DView{

	private Map2DController mapCon;
	private Map<String, Color> colorMap = new HashMap<>();
	

	private TextField text;
	private Group graphic;
	private Pane root;
	private Group components = new Group();
	
	public Map2DView(Map2DController mapCon) {
		this.mapCon = mapCon;
		makeStage();
	}
	
	private void makeStage() {
		colorMap.put("forest", Color.DARKGREEN);
		colorMap.put("water", Color.AQUA);
		colorMap.put("hill", Color.LIGHTGREEN);
		colorMap.put("mountain", Color.GRAY);
		colorMap.put("swamp", Color.BROWN);
		colorMap.put("farm", Color.GOLD);
		
		mapLoader();
		makeAreas();
		Group buttons = makeButtons();

		text = new TextField();
		text.setTranslateY(50);

		components.getChildren().add(graphic);
		components.getChildren().add(buttons);
		components.getChildren().add(text);
		
	}
	
	private Group makeButtons() {
		Group group = new Group();

		Button add = new Button("Toevoegen");
		Button delete = new Button("Verwijderen");
		Button show = new Button("Show");
		Button attack = new Button("Attack");
	    
//        show.setOnAction(e -> {if(activeShape != null) mapCon.printCount(activeShape.getId());});
//        add.setOnAction(e -> {if(activeShape != null) mapCon.addFiche(activeShape.getId(), Integer.parseInt(text.getText()));});
//        delete.setOnAction(e -> {if(activeShape != null) mapCon.deleteFiche(activeShape.getId(), Integer.parseInt(text.getText()));});
//		attack.setOnAction(e -> {if(activeShape != null) mapCon.attackCountry(activeShape.getId());});
        
		HBox box = new HBox(5, add, delete, show, attack);
        group.getChildren().add(box);
        return group;
	}
	
	private void mapLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/Map/svgtest.fxml"));
        try {
			graphic = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void makeAreas() {
        for (Node area : graphic.getChildren()) {
        	Shape shape = (Shape)area;
        	decideColor(shape);
        	mapCon.createNewArea(area);
        }
	}
	
	private void decideColor(Shape shape) {
		shape.setFill(colorMap.get(shape.getId().split("_")[0]));
	}

	public void setPane(Pane myPane) {
		this.root = myPane;
		root.getChildren().add(components);
	}
}
