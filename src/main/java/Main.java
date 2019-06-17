import Applicatie.Applicatie;
import Controller.LobbyController;
import Firebase.FirebaseApplicatie;
import View.LobbyView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		new Applicatie(primaryStage);

		//new LobbyController();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
//		Applicatie.getFirebaseService().testen();
		launch(args);
	}
}
