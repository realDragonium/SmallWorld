package Model;

import Controller.CombinationController;
import Observable.PlayerObservable;
import Observer.PlayerObserver;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel implements PlayerObservable {
    private List<PlayerObserver> observer = new ArrayList<>();
    private String playerID = "";
    public int fiches;
    public int punten;
    private CombinationController activeCombi;
    private CombinationController deprecatedCombi;

    public PlayerModel(String playerId) {
        playerID = playerId;
        punten = 5;
    }

    public void setFicheAmount(int amount){
        fiches = amount;
    }

    public void removePoints(int amount){
        punten -= amount;
        notifyObserver();
    }

    public void setActiveCombi(CombinationController combi){
        deprecatedCombi = activeCombi;
        activeCombi = combi;
    }

    @Override
    public String getId(){
        return playerID;
    }

    @Override
    public void register(PlayerObserver po) {
        observer.add(po);
    }

    @Override
    public void notifyObserver() {
        for(PlayerObserver obs :observer){
            obs.update(this);
        }
    }

    @Override
    public int getFiches() {
        return fiches;
    }

    @Override
    public int getPunten() {
        return punten;
    }

    @Override
    public CombinationController getActive() {
        return activeCombi;
    }

    @Override
    public CombinationController getDeprec() {
        return deprecatedCombi;
    }

    public void addPunten(int amount) {
        punten += amount;
        notifyObserver();
    }
}
