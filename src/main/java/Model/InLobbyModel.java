package Model;

import Observer.InLobbyObserver;
import Observer.LobbyObserver;
import observable.InLobbyObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class InLobbyModel implements InLobbyObservable {
    private List<InLobbyObserver> observers = new ArrayList<>();

    private String lobbyNaam;

    public void setLobbyNaam(String lobbyNaam){
        this.lobbyNaam = lobbyNaam;
    }



    @Override
    public void notifyAllObservers() {
        for(InLobbyObserver obs : observers) {
            obs.update();
        }
    }


    @Override
    public void register(InLobbyObserver ob){
        observers.add(ob);
    }

    @Override
    public void unregister(InLobbyObserver ob){
        observers.remove(ob);
    }

}
