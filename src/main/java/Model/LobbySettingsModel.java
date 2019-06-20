package Model;

import Observer.LobbySettingsObserver;
import Observable.LobbySettingsObservable;
import java.util.ArrayList;
import java.util.List;


/**  This Model-class is part of the MVC design pattern and shows the lobbySettings screen.
 * @author: Lars Puente Blom
 * @version: June 2019
 */

public class LobbySettingsModel implements LobbySettingsObservable {
    private List<LobbySettingsObserver> observers = new ArrayList<>();
    private String lobbyNaam;

    @Override
    public void notifyAllObservers() {
        for(LobbySettingsObserver observer : observers){
            observer.update();
        }
    }

    @Override
    public void register(LobbySettingsObserver ob) {
        observers.add(ob);
    }

    @Override
    public void unregister(LobbySettingsObserver mvo) {
    }

    public String getNameLobby(){
        return lobbyNaam;
    }

}
