package Controller;

import Managers.SceneManager;
import Model.RoundModel;
import Observer.RoundObserver;

public class RoundController {
    private RoundModel model;
    private GameController gameCon;

    public RoundController(GameController gameCon){
        model = new RoundModel(8);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadRound(this);
    }

    public void nextRound() {
        if(model.currentRound >= model.getMaxRounds()) {
            gameCon.endGame();
            return;
        }
        model.nextRound();
    }

    public int getCurrentRound(){
        return model.currentRound;
    }

    public void register(RoundObserver ro ){
        model.register(ro);
    }
}
