package main.java.RoundTurn;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	
	private RTView turnviewke;
	
	@Override
	public void start(Stage arg0) {

		new RTView(arg0);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
