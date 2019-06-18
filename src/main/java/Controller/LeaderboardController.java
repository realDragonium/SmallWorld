package Controller;

import Managers.SceneManager;
import Observer.LeaderboardObserver;
import Model.LeaderboardModel;
import View.HomeScreenView;

public class LeaderboardController {
    LeaderboardModel leaderboardModel = new LeaderboardModel();


    public LeaderboardController(){
        SceneManager.getInstance().LeaderboardView(this);
    }


    public void addValue() {
        leaderboardModel.addValue();
    }

    public void registreer(LeaderboardObserver ob){
        leaderboardModel.register(ob);
    }




//    public void backMenu(){
//        new HomeScreenView();
//
//    }
}
