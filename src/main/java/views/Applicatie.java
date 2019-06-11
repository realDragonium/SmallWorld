package views;

import controlers.ApplicatieController;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import managers.SceneManager;

public class Applicatie {
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;
	private Stage primaryStage;
	private Group root = new Group();
	private ApplicatieController appCon = ApplicatieController.getInstance();
	SceneManager manager = new SceneManager(this);
	
	public Applicatie(Stage primaryStage) {
		this.primaryStage = primaryStage;
		loadPrimaryStage();
	}
	
	private void loadPrimaryStage() {
            Scene scene = new Scene(root, width,height);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Small World");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();
            manager.createBeginScreen();

    }

	public void changeScene(Scene scene) {
		
		primaryStage.setScene(scene);
		this.root = root;
	}
	
	public void addToScene(SubScene scene) {
		GridPane pane = ((GridPane) root.getChildren().get(0));
		 GridPane.setConstraints(scene, 5, 5, 5, 5,HPos.CENTER, VPos.CENTER);
		pane.getChildren().addAll(scene);
		
		
	}

	public void removeScene(SubScene scene) {
		root.getChildren().remove(scene);
		
	}
}
