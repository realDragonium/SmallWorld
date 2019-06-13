package main.java.models;

import main.java.observers.TurnObservable;

import java.util.Collection;

public class TurnModel implements TurnObservable {

    private int whichTurn;
    private String currentPlayer;
    private Collection playerList;
    private boolean viewActive;
    //private UITypeEnum UIType;

    public void setPlayerList(){
        return;
    }

    public void nextPlayer(){
        return;
    }

    public void changeActive(){
        return;
    }

    public void changeStateTurn() {
    }


    @Override
    public boolean playerOneAanDeBeurt() {
        return false;
    }

    @Override
    public boolean endTurn() {
        return false;
    }




}
