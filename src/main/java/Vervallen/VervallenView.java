package main.java.Vervallen;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VervallenView implements VervallenObserver {

	VervallenController vervalController = new VervallenController();
	
	Stage primaryStage = new Stage();
	Button vervallen;

	private List<Image> image = new ArrayList<>();

	ImageView switchImage = new ImageView();
	
	public VervallenView() {
		vervalController.registerObserver(this);
		this.primaryStage = primaryStage;
		createScene();
	}

	
	
	public void createScene() {
		

		image.add(new Image("/images/DiceOne.jpg"));
		image.add(new Image("/images/DiceTwo.jpg"));
		
		vervallen = new Button("In verval gaan");
		vervallen.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		
		
		switchImage.setImage(image.get(0));
		
		GridPane gridPaneMain = new GridPane();
		GridPane gridPane1 = new GridPane();
		GridPane gridPane2 = new GridPane();
		
		
		gridPaneMain.add(gridPane1,0,0);
		gridPaneMain.add(gridPane2,0,1);
		
		gridPane1.add(vervallen,0,0);
		gridPane2.add(switchImage,0,1);
		
		
		
		
		vervallen.setOnAction(e -> vervalController.ClickedVervallen());
		
		
		Scene scene = new Scene(gridPaneMain);
		primaryStage.setScene(scene);
		primaryStage.setTitle("test");
		primaryStage.show();
		
	}
	
	

	@Override
	public void update(VervallenObservable ob) {
		System.out.println(ob.getFichesActive());
		if(!ob.getFichesActive()) {
			switchImage.setImage(image.get(1));
		} else {
			switchImage.setImage(image.get(0));
		}
		
	}

}
