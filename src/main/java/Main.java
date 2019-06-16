import Applicatie.Applicatie;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		new Applicatie(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
