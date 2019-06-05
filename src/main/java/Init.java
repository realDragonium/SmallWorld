import javafx.application.Application;
import javafx.stage.Stage;
import moleculesampleapp.Main;
import views.ApplicatieView;

import java.util.ArrayList;
import java.util.List;

public class Init extends Application{

    @Override
    public void start(Stage primaryStage) {

        ApplicatieView b = new ApplicatieView(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
