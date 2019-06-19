package Controller;

import Firebase.FirebaseControllerObserver;
import Managers.SceneManager;
import Enum.TurnFase;
import com.google.cloud.firestore.DocumentSnapshot;

class GameTurn implements FirebaseControllerObserver {

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
        System.out.println("Begin beurt: " + currentPlayer.getId());
        System.out.println("Jij bent speler: " + gameCon.getMyPlayerId());

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
        phaseTimer = new TimerController(this);
        SceneManager.getInstance().switchToSpectatingView();
        currentPhase = TurnFase.preparing;
        if(currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            SceneManager.getInstance().switchToPreperationPhase();

        if(currentPlayer.hasActiveCombination()) {
            currentPlayer.returnFiches();
            currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
            }
        }
    }

    void startAttackPhase(){
        phaseTimer = new TimerController(this);
        SceneManager.getInstance().switchToSpectatingView();
        currentPhase = TurnFase.conquering;
        if(currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            SceneManager.getInstance().switchToAttackPhase();

            if (currentPlayer.hasActiveCombination()) {
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
            }
        }
    }

    void startEndingPhase() {
        phaseTimer = new TimerController(this);
        SceneManager.getInstance().switchToSpectatingView();
        currentPhase = TurnFase.redeploying;
        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            SceneManager.getInstance().switchToEndingPhase();
            currentPlayer.addRoundPoints();


            if (currentPlayer.hasActiveCombination()) {
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
            }
        }
    }

    @Override
    public void update(DocumentSnapshot ds) {

    }
}
