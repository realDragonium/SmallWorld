package Controller;

import Managers.SceneManager;
import Model.TurnModel;
import Observer.TurnObserver;

public class TurnController {

    private TurnModel model;
    private GameController gameCon;


    public TurnController(GameController gameCon){
        model = new TurnModel(4);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadTurn(this);
        gameCon.changePlayerTurn("player"+model.currentTurn);
    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void nextTurn(){
        System.out.println("volgende beurt");
        if(model.currentTurn >= model.getTurnPerRound()) {
            model.currentTurn = 0;
            gameCon.getRoundCon().nextRound();
        }
        if(gameCon.isGameOver()) return;
        model.nextTurn();
        gameCon.changePlayerTurn("player"+model.currentTurn);
    }

    public String getCurrentPlayer() {
        return "player" + model.currentTurn;
    }
}