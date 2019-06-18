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
    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void nextTurn(){
        if(model.currentTurn >= model.getTurnPerRound()) {
            model.currentTurn = 0;
            gameCon.getRoundCon().nextRound();
            model.notifyObservers();
        }
        if(gameCon.isGameOver()) return;
        model.currentTurn++;
        gameCon.changePlayerTurn("player"+model.currentTurn);
        model.notifyObservers();
    }

    public String getCurrentPlayer(){
        return "player"+model.currentTurn;
    }

    public void nextFase(){
        if(model.peek() == null){
            nextTurn();
            model.makeStack();
            model.notifyObservers();
            return;
        }
        if(gameCon.isGameOver()) return;
        model.nextFase();
    }

}