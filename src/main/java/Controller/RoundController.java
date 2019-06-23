package Controller;

import Managers.SceneManager;
import Model.RoundModel;
import Observer.RoundObserver;

/**
 * @author : Martijn van der Steen
 * @version : Juni 2019
 */

public class RoundController {



    private RoundModel model;
    private GameController gameCon;

    public RoundController(GameController gameCon){
        model = new RoundModel(8);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadRound(this);
    }

    public void nextRound() {
        //Als aantal rondes boven maximale rondes is.
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
