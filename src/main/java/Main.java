import Applicatie.Applicatie;
import Controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		new Applicatie(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
