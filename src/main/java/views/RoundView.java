package main.java.views;

import main.java.controlers.RoundControler;
import main.java.controlers.TurnControler;
import main.java.observers.RoundObservable;
import main.java.observers.RoundObserver;
import main.java.observers.TurnObservable;
import main.java.observers.TurnObserver;

import java.util.Collection;

public class RoundView implements RoundObserver, TurnObserver {


    private Collection textures;
    RoundControler roundControler = new RoundControler();
    TurnControler turnControler = new TurnControler();

    public RoundView(Collection textures){

        this.textures = textures;

        roundControler.configureTextureData(textures);
        roundControler.configureRoundData();
        roundControler.registerObserver(this);
        confActions();
    }

    private void confActions(TurnObservable to){
        if(to.playerOneTurn()) {
            roundControler.changeStateRound();
        }
    }

    private void changeTexture(){

    }


    @Override
    public void update(RoundObservable ro) {
        if(ro.playerOneTurn()) {
            changeRound();
            System.out.println("ronde voorbij");
        }
        else {
            System.out.println("nog geen ronde voorbij");
        }
    }

    private void changeRound(){

    }

    @Override
    public void update(TurnObservable to) {

    }
}
