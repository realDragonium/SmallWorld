package Controller;

import Enum.TurnFase;
import Firebase.FirebaseControllerObserver;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;

/** This class handles the game turn logic, it decides what phase it is and what should happen in the phase.
 * There should only be one GameTurn at a time.
 *
 * @author yoran
 * @version June 2019
 */

class GameTurn implements FirebaseControllerObserver {

    GameController gameCon;
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

        SceneManager.getInstance().switchToSpectatingView();
    }

    void endPhase() {
        System.out.println("switching van: " + currentPhase);
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
                currentPhase = TurnFase.none;
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
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);

                SceneManager.getInstance().addToScene("vervalGroup");
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
            gameCon.getTimer().setTime(gameCon.getGameTimer().maxTime);
            gameCon.getGameTimer().resetTimer(ds.getBoolean("endPhase"));
        });
    }

    public void newTurn(PlayerController currentPlayer) {
        this.currentPlayer = currentPlayer;
        endPhase();
        System.out.println("Begin beurt: " + currentPlayer.getId());
        System.out.println("Jij bent speler: " + gameCon.getMyPlayerId());
    }
}
