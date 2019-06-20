package Controller;

import Managers.SceneManager;


public class VervallenController {

    GameController gameCon;

    VervallenController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadVerval(this);
    }

    public VervallenController() {

    }


    public void inVerval() {
        makeFicheToOne();
        makeCombinationNonActive();
        makePlayerSkipTurn();
        //eindig zijn beurt
    }



    //methode maakt in de Areacontroller fiches naar 1 per gebied (empty maar nog niet 1 per gebied)
    private void makeFicheToOne() {
        gameCon.getCurrentPlayer().getActiveCombination().destroyAllFichesButOne();

    }

    //methode maakt in de Racemodel racefiche !raceactive. (
    private void makeCombinationNonActive(){
        gameCon.getCurrentPlayer().getActiveCombination().setToNonActive();
    }

    //methode volgende ronde van dezelfde player overgeslagen (kom ik niet uit) (
    private void makePlayerSkipTurn(){
        gameCon.getGameTurn().endTurn();
    }
}
