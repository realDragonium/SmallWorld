package Model;

import javafx.scene.control.Label;
import observable.LeaderboardObservable;
import Observer.LeaderboardObserver;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardModel implements LeaderboardObservable {

    String place1;
    String place2;
    String place3;
    String waarde = "";

    List<LeaderboardObserver> lijst = new ArrayList<>();

    public void addValue(){
        waarde="groetjes";
        notifyAllObs();
    }


    @Override
    public void register(LeaderboardObserver ob) {
        lijst.add(ob);
    }

    @Override
    public void notifyAllObs() {
        for(LeaderboardObserver ob : lijst){
            ob.update(this);
        }
    }

    @Override
    public String getWaarde() {
        return waarde;
    }

    @Override
    public String getPlace1() {
        return place1;
    }

    @Override
    public String getPlace2() {
        return place2;
    }

    @Override
    public String getPlace3() {
        return place3;
    }


}
