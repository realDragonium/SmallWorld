package main.java.views;

import main.java.controlers.TurnControler;
import main.java.observers.RoundObserver;
import main.java.observers.TurnObservable;
import main.java.observers.TurnObserver;

import java.util.Collection;

public class TurnView implements TurnObserver, RoundObserver {

    private Collection textures;
    TurnControler turnControler = new TurnControler();

    private void changeTexture(){

    }

    @Override
    public void update(TurnObservable to) {

        if(to.endTurn()){
            nextPlayerTurn();
            if(to.isPlayerOne()){
                updateRound();
            }
        }else{

        }

    }

    private void nextPlayerTurn() {

    }

    private void updateTurn(){

    }

}
