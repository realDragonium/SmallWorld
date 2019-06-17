package main.java.RoundTurn;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RTView implements RTObserver {
	
	Stage primaryStage = new Stage();
	RTController turnController = new RTController();
	TextField player1;
	TextField textfieldRound;
	
	public RTView(Stage primaryStage) {
		turnController.registerObserver(this);
		this.primaryStage = primaryStage;
		CreateTurnScene();
	}

	
	public void CreateTurnScene() {
		
		player1 = new TextField("Player 1");
		
		textfieldRound = new TextField("Round 1");
				
		Button endTurn = new Button("End Turn");
		
		GridPane gridPaneMain = new GridPane();
		GridPane gridPane1 = new GridPane();
		GridPane gridPane2 = new GridPane();
		GridPane gridPane3 = new GridPane();

		gridPane1.add(endTurn,0,0);
		gridPane2.add(player1,0,1);
		gridPane3.add(textfieldRound,0,2);
		
		
		gridPaneMain.add(gridPane1,0,0);
		gridPaneMain.add(gridPane2,0,1);
		gridPaneMain.add(gridPane3,0,2);
		
		
		endTurn.setOnAction(e -> turnController.ClickedEndTurn()); 
		
		Scene scene = new Scene(gridPaneMain,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	@Override
	public void update(RTObservable ob) {
		// TODO Auto-generated method stub
		player1.setText(ob.getWhichTurn());
		textfieldRound.setText(ob.getRounds());
		
	}


	
	
	
	
}