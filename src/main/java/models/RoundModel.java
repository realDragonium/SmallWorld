package main.java.models;

import main.java.observers.RoundObservable;
import main.java.observers.RoundObserver;

import java.util.Collection;
import java.util.Observable;

public class RoundModel implements RoundObservable {

    private int maxRounds;
    private int currentRound;
    private boolean viewActive;
    //private enum UIType;

    public RoundModel(int maxRounds, int currentRound, boolean viewActive){
        this.maxRounds = maxRounds;
        this.currentRound = currentRound;
        this.viewActive = viewActive;
    }


    public void configureRoundData(Collection textures){

    }

    @Override
    public void register(RoundObserver ro) {
        observers.add(ro);
    }

    @Override
    public void notifyAllObservers() {

    }

    @Override
    public boolean playerOneTurn() {
        return false;
    }

    public void changeStateRound() {
    }
}
