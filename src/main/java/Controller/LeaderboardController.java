package Controller;

import Managers.SceneManager;
import Observer.LeaderboardObserver;
import Model.LeaderboardModel;
import View.HomeScreenView;
import javafx.scene.control.Label;

import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class LeaderboardController {
    LeaderboardModel leaderboardModel = new LeaderboardModel();


    public LeaderboardController(){
        SceneManager.getInstance().LeaderboardView(this);
        TreeMap<Double, String> map = SceneManager.getInstance().getApp().getFirebaseService().getTop3Player();
        System.out.println(map.toString());
        Set<Double> lijst = map.keySet();
        Stack<Double> stack = new Stack<>();
        for(double getal: lijst){
            stack.push(getal);
        }

        double place1 = stack.pop();
        double place2 = stack.pop();
        double place3 = stack.pop();
        String place1s =String.valueOf((int)place1);
        String place2s =String.valueOf((int)place2);
        String place3s =String.valueOf((int)place3);


        System.out.println(stack.toString());
        changePlayer(map.get(place1), map.get(place2), map.get(place3));
        changePoints(place1s,place2s, place3s);
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

    public void changePoints(String value1, String value2, String value3){
        leaderboardModel.playerPoints(value1, value2, value3);
    }



}
