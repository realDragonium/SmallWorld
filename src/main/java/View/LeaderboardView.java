package View;


import Controller.HomeScreenController;
import Controller.LeaderboardController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import Observable.LeaderboardObservable;
import Observer.LeaderboardObserver;
import javafx.fxml.FXML;


public class LeaderboardView implements LeaderboardObserver {
    LeaderboardController con;

    public Group root;
    private Group group;
    public Button hoofdmenu;

    public LeaderboardView(Group group, LeaderboardController con){
        this.group = group;
        this.con = con;
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
        con.registreer(this);

    }




    @Override
    public void update(LeaderboardObservable ob) {
        System.out.println("joe joe" +ob.getWaarde()) ;
    }

    public void registreer(){
        con.registreer(this);
    }
@FXML
    public void drukKnop(){
        con.addValue();

    }
}
