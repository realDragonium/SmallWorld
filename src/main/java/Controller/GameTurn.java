package Controller;

import Managers.SceneManager;
import Enum.TurnFase;

public class GameTurn {

    GameController gameCon;
    TimerController phaseTimer;

    public void endTurn() {
        currentPhase = TurnFase.redeploying;
        phaseTimer.timerEnded();
    }
    TurnFase currentPhase;
    PlayerController currentPlayer;

    public GameTurn(GameController gameCon, PlayerController player){
        this.gameCon = gameCon;
        this.currentPlayer = player;
        startPreperationPhase();
    }

    public void endPhase() {

        switch(currentPhase){
            case preparing:
                startAttackPhase();
                break;

            case conquering:
                startEndingPhase();
                break;

            case redeploying:
                System.out.println("end of ending phase");
                gameCon.nextTurn();
                break;
    }
}

    public void startPreperationPhase(){
        currentPhase = TurnFase.preparing;
        SceneManager.getInstance().switchToPreperationPhase();
        if(currentPlayer.hasActiveCombination()){
            currentPlayer.returnFiches();
            currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
        }
        phaseTimer = new TimerController(this);


    }

    public void startAttackPhase(){
        currentPhase = TurnFase.conquering;
        SceneManager.getInstance().switchToAttackPhase();
        phaseTimer = new TimerController(this);
        if(currentPlayer.hasActiveCombination()) {
            currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
        }
    }

    public void startEndingPhase(){
        currentPhase = TurnFase.redeploying;
        SceneManager.getInstance().switchToEndingPhase();
        currentPlayer.addRoundPoints();
        phaseTimer = new TimerController(this);
        if(currentPlayer.hasActiveCombination()) {
            currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
        }
    }
}
