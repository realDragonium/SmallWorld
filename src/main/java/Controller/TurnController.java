package Controller;

import Managers.SceneManager;
import Model.TurnModel;
import Observer.TurnObserver;
import Enum.TurnFase;

import java.util.Random;

/**
 * @author : Martijn van der Steen
 * @version : Juni 2019
 */

public class TurnController {


    private TurnModel model;
    private GameController gameCon;
    private int currentPlayer;


    TurnController(GameController gameCon){
        model = new TurnModel(4);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadTurn(this);
        decideStartingPlayer();
    }

    public void decideStartingPlayer(){
        currentPlayer = 1;
        gameCon.setCurrentPlayer(currentPlayer);

    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void nextTurn(){
        if(gameCon.isGameOver()) return;
        model.nextTurn();
        currentPlayer++;
        if(currentPlayer == 5){
            currentPlayer = 1;
        }
        gameCon.changePlayerTurn("player"+currentPlayer);
    }

    String getCurrentPlayer() {
        return "player" + model.currentTurn;
    }

    public void setFase(TurnFase currentPhase) {
        model.setFase(currentPhase);
    }
}