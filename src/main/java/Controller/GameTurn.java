package Controller;

import Firebase.FirebaseControllerObserver;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Enum.TurnFase;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.Map;

class GameTurn implements FirebaseControllerObserver {

    GameController gameCon;
    TimerController phaseTimer;
    private FirebaseServiceOwn fb = SceneManager.getInstance().getApp().getFirebaseService();

    void endTurn() {
        gameCon.getGameTimer().endPhase();
    }

    TurnFase currentPhase;
    PlayerController currentPlayer;

    public GameTurn(GameController gameCon, PlayerController player){
        this.gameCon = gameCon;
        this.currentPlayer = player;
        System.out.println("Begin beurt: " + currentPlayer.getId());
        System.out.println("Jij bent speler: " + gameCon.getMyPlayerId());
        phaseTimer = new TimerController(this);

        fb.timerListen(this);
        SceneManager.getInstance().switchToSpectatingView();
        startPreperationPhase();
    }

    void endPhase() {
        switch(currentPhase){
            case none:
                startPreperationPhase();
                break;

            case preparing:
                startAttackPhase();
                break;

            case conquering:
                startEndingPhase();
                break;

            case redeploying:
                SceneManager.getInstance().switchToSpectatingView();
                gameCon.nextTurn();
                break;
        }
    }

    void startPreperationPhase(){

        currentPhase = TurnFase.preparing;

        if(currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            SceneManager.getInstance().switchToPreperationPhase();
            if(currentPlayer.hasActiveCombination()) {
                currentPlayer.returnFiches();
                SceneManager.getInstance().addToScene("vervalGroup");
            }
            else{
                SceneManager.getInstance().addToScene("shopGroup");
            }
        }

    }

    void startAttackPhase(){
        currentPhase = TurnFase.conquering;
        if(currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            SceneManager.getInstance().switchToAttackPhase();

            if (currentPlayer.hasActiveCombination()) {
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
            }
        }
    }

    void startEndingPhase() {
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
        System.out.println("Timer Update!");
        boolean endPhase = ds.getBoolean("endPhase");
        int timer = (int) Math.round(ds.getDouble("timer"));
        phaseTimer.setTime(timer);
        if(endPhase){
            endPhase();
        }
    }
}
