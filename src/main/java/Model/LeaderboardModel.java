package Model;

import Observable.LeaderboardObservable;
import Observer.LeaderboardObserver;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardModel implements LeaderboardObservable {

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
}
