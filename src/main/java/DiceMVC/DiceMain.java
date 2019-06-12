package DiceMVC;

import javafx.application.Application;
import javafx.stage.Stage;

public class DiceMain extends Application {

	
	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage arg0) throws Exception {

		new DiceView(arg0);
		
		
	}
}
