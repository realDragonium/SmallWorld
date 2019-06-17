import Applicatie.Applicatie;
import Firebase.FirebaseApplicatie;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		new Applicatie(primaryStage);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
//		Applicatie.getFirebaseService().testen();
		launch(args);
	}
}
