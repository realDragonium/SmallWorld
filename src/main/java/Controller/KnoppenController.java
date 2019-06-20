package Controller;

import Managers.SceneManager;

import java.util.List;

public class KnoppenController {

    private GameController gameCon;

    public KnoppenController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadButtons(this);
    }

    public void showInfo() {
        SceneManager.getInstance().addToStandardScene("infoGroup");
    }

    public void fichesOver() {
        gameCon.getCurrentPlayer().showActiveCombiFichesLeft();
    }

    public void nextPhase(){
        //gameCon.getGameTurn().endPhase();
         }

}
