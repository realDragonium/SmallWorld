package Controller;

import Enum.TurnFase;
import Firebase.FirebaseControllerObserver;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;

class GameTurn implements FirebaseControllerObserver {

    GameController gameCon;
    TimerController phaseTimer;
    private FirebaseServiceOwn fb = SceneManager.getInstance().getApp().getFirebaseService();

    void endTurn() {
        gameCon.getGameTimer().endPhase();
    }

    TurnFase currentPhase;
    PlayerController currentPlayer;

    public GameTurn(GameController gameCon, PlayerController player) {
        fb.timerListen(this);
        this.gameCon = gameCon;
        currentPlayer = player;
        currentPhase = TurnFase.none;
        System.out.println("Begin beurt: " + currentPlayer.getId());
        System.out.println("Jij bent speler: " + gameCon.getMyPlayerId());
        phaseTimer = new TimerController(this);
        SceneManager.getInstance().switchToSpectatingView();
    }

    void endPhase() {
        System.out.println(currentPhase);
        switch (currentPhase) {
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

    void startPreperationPhase() {
        currentPhase = TurnFase.preparing;
        gameCon.getTurnCon().setFase(currentPhase);

        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            SceneManager.getInstance().switchToPreperationPhase();
            if (currentPlayer.hasActiveCombination()) {
                currentPlayer.returnFiches();
                SceneManager.getInstance().addToScene("vervalGroup");
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
            } else {
                SceneManager.getInstance().addToScene("shopGroup");
            }
        }

    }

    void startAttackPhase() {
        currentPhase = TurnFase.conquering;
        gameCon.getTurnCon().setFase(currentPhase);
        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            if (currentPlayer.hasActiveCombination()) {
                SceneManager.getInstance().switchToAttackPhase();
            } else {
                endTurn();
            }
        }
    }

    void startEndingPhase() {
        currentPhase = TurnFase.redeploying;
        gameCon.getTurnCon().setFase(currentPhase);
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
        Platform.runLater(() -> {
            endPhase();
            phaseTimer.setTime((int)Math.round(ds.getDouble("time")));
            gameCon.getGameTimer().resetTimer();
        });
    }
}
