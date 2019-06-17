package Controller;

import Managers.SceneManager;

public class GameTurn {

    GameController gameCon;
    TimerController phaseTimer;

    enum Phase{PREPERATION, ATTACK, ENDING}
    Phase currentPhase;
    PlayerController currentPlayer;

    public GameTurn(GameController gameCon, PlayerController player){
        this.gameCon = gameCon;
        this.currentPlayer = player;
        startPreperationPhase();
    }

    public void endPhase() {
        switch(currentPhase){
            case PREPERATION:
                startAttackPhase();
                break;

            case ATTACK:
                startEndingPhase();
                break;

            case ENDING:
                gameCon.nextTurn();
                break;
    }
}

    public void startPreperationPhase(){
        currentPhase = Phase.PREPERATION;
        SceneManager.getInstance().switchToPreperationPhase();
        if(currentPlayer.getActiveCombination() != null){
            currentPlayer.returnFiches();
        }
        phaseTimer = new TimerController(this);

    }

    public void startAttackPhase(){
        currentPhase = Phase.ATTACK;
        SceneManager.getInstance().switchToAttackPhase();
        phaseTimer = new TimerController(this);
    }

    public void startEndingPhase(){
        currentPhase = Phase.ENDING;
        SceneManager.getInstance().switchToEndingPhase();
        phaseTimer = new TimerController(this);
    }
}
