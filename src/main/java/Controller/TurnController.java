package Controller;

import Managers.SceneManager;
import Model.TurnModel;
import Observer.TurnObserver;

import java.util.Random;

public class TurnController {

    private TurnModel model;
    private GameController gameCon;
    private int startingPlayer;


    TurnController(GameController gameCon){
        model = new TurnModel(4);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadTurn(this);
        decideStartingPlayer();
    }

    public void decideStartingPlayer(){
        startingPlayer = new Random().nextInt(4) + 1;
        gameCon.setCurrentPlayer(startingPlayer);

    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void nextTurn(){
        if(gameCon.isGameOver()) return;
        model.nextTurn();
        int player = (model.currentTurn - 1) + startingPlayer;
        if(player > 4){
            player = player - startingPlayer;
        }
        gameCon.changePlayerTurn("player"+player);
    }

    String getCurrentPlayer() {
        return "player" + model.currentTurn;
    }
}