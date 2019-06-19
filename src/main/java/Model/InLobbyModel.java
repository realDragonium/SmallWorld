package Model;

import Observer.InLobbyObserver;
import Observer.LobbyObserver;
import Observable.InLobbyObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class InLobbyModel implements InLobbyObservable {
    private List<InLobbyObserver> observers = new ArrayList<>();
    private boolean start = false;
    private String lobbyNaam;

    public void setLobbyNaam(String lobbyNaam){
        this.lobbyNaam = lobbyNaam;
    }

    public String getLobbyNaam(){
        return lobbyNaam;
    }

    public void startGame(boolean start){
        this.start = start;
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers() {
        for(InLobbyObserver obs : observers) {
            obs.update(this);
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

    @Override
    public boolean getStart() {
        return start;
    }

}
