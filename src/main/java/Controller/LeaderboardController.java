package Controller;

import Managers.SceneManager;
import Observer.LeaderboardObserver;
import Model.LeaderboardModel;
import View.HomeScreenView;
import javafx.scene.control.Label;

public class LeaderboardController {
    LeaderboardModel leaderboardModel = new LeaderboardModel();


    public LeaderboardController(){
        SceneManager.getInstance().LeaderboardView(this);
        changePlayer("Martijn", "Wino", "Yoran");
    }


    public void addValue() {
        leaderboardModel.addValue();
    }

    public void registreer(LeaderboardObserver ob){
        leaderboardModel.register(ob);
    }

    public void changePlayer(String place1,String place2, String place3){

        leaderboardModel.playerValue(place1, place2, place3);
    }



}
