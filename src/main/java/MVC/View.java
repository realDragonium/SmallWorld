package MVC;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View implements Observer {
    Controller controller = new Controller();

    public View(Stage primaryStage){
        startScene(primaryStage);
    }
    public View(){
        registreer();
    }

    public void startScene(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Leaderboard");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void update(Observable ob) {
        System.out.println("joe joe" +ob.getWaarde()) ;
    }

    public void registreer(){
        controller.registreer(this);
    }
@FXML
    public void drukKnop(){
        controller.addValue();

    }
}
