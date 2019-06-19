package Controller;

import Managers.SceneManager;
import Enum.TurnFase;

class GameTurn {

    GameController gameCon;
    TimerController phaseTimer;

    void endTurn() {
        currentPhase = TurnFase.redeploying;
        phaseTimer.timerEnded();
    }
    TurnFase currentPhase;
    PlayerController currentPlayer;

    GameTurn(GameController gameCon, PlayerController player){
        this.gameCon = gameCon;
        this.currentPlayer = player;
        startPreperationPhase();
    }

    void endPhase() {
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

    void startPreperationPhase(){
        currentPhase = TurnFase.preparing;
        SceneManager.getInstance().switchToPreperationPhase();
        if(currentPlayer.hasActiveCombination()){
            currentPlayer.returnFiches();
            currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
        }
        phaseTimer = new TimerController(this);


    }

    void startAttackPhase(){
        currentPhase = TurnFase.conquering;
        SceneManager.getInstance().switchToAttackPhase();
        phaseTimer = new TimerController(this);
        if(currentPlayer.hasActiveCombination()) {
            currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
        }
    }

    void startEndingPhase(){
        currentPhase = TurnFase.redeploying;
        SceneManager.getInstance().switchToEndingPhase();
        currentPlayer.addRoundPoints();
        phaseTimer = new TimerController(this);
        if(currentPlayer.hasActiveCombination()) {
            currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
        }
    }
}
