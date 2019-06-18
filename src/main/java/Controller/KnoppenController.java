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
        List<AreaController> areaCons = gameCon.getMapCon().getActiveAreas();
        if (areaCons.size() > 0) {
            System.out.println("Je hebt " + areaCons.get(0).numbersNeeded() + " fiches nodig.");
        } else {
            System.out.println("Je hebt niks geselecteerd!");
        }
    }

    public void attackCountry() {

        AttackController attCon = gameCon.getAttCon();
        attCon.getTargetArea();
        attCon.attackArea();
    }

    public void fichesOver() {
        gameCon.getCurrentPlayer().showActiveCombiFichesLeft();
    }

    public void nextPhase(){gameCon.getGameTurn().endPhase();}
}
