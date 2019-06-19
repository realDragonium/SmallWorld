package View;


import Controller.HomeScreenController;
import Controller.LeaderboardController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import observable.LeaderboardObservable;
import Observer.LeaderboardObserver;
import javafx.fxml.FXML;


public class LeaderboardView implements LeaderboardObserver {
    LeaderboardController leadCon;

    public Label place1;
    public Label place2;
    public Label place3;



    public Group root;
    private Group group;
    public Button hoofdmenu;

    public LeaderboardView(Group group, LeaderboardController leadCon){
        this.group = group;
        this.leadCon = leadCon;

    }



    public void back(){
        new HomeScreenController();

    }



    /*
    public LeaderboardView(Stage primaryStage){
        startScene(primaryStage);
    }
    public LeaderboardView(){
        registreer();
    }



    public void startScene(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen/Leaderboard.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Leaderboard");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

*/

    public void initialize(){
        group.getChildren().add(root);
        leadCon.registreer(this);

    }




    @Override
    public void update(LeaderboardObservable ob) {
        place1.setText(ob.getPlace1());
        place2.setText(ob.getPlace2());
        place3.setText(ob.getPlace3());

    }

    public void registreer(){
        leadCon.registreer(this);
    }
@FXML
    public void drukKnop(){
    leadCon.addValue();

    }


}
