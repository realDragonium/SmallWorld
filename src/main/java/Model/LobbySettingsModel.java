package Model;

import Observer.LobbySettingsObserver;
import observable.LobbySettingsObservable;

import java.util.ArrayList;
import java.util.List;

public class LobbySettingsModel implements LobbySettingsObservable {

    private List<LobbySettingsObserver> observers = new ArrayList<>();


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




}
