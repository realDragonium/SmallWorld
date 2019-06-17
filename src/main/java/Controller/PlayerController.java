package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseControllerObserver;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.PlayerModel;
import Observer.PlayerObserver;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PlayerController implements FirebaseControllerObserver {
    private GameController gameCon;
    private FirebaseServiceOwn fb = Applicatie.getFirebaseService();
    private PlayerModel model;
    private List<CombinationController> combinations = new ArrayList<>();

    public PlayerController(String playerID, GameController gameCon) {
        model = new PlayerModel(playerID);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadPlayer(playerID, this);
        fb.playerListen(playerID, this);
    }

    public void buyFromShop(CombinationController combo, int costs) {
        System.out.println(getId() + " voegt combinatie toe");
        model.removePoints(costs);
        combinations.add(combo);
        combo.setPlayer(this);
        setFiches(combo.getRace().fichesCount());
    }

    public void showActiveCombiFichesLeft() {
        for (CombinationController combiCon : combinations) {
            combiCon.getRace().fichesOver();
        }
    }

    public CombinationController getActiveCombination() {
        if (combinations.size() > 0) return combinations.get(0);
        return null;
    }

    String getId() {
        return model.getId();
    }

    public void register(PlayerObserver po) {
        model.register(po);
    }

    public void setFiches(int fiches) {
        model.fiches = fiches;
        model.notifyObserver();
        fb.playerUpdateFiches(model.getId(), fiches);
    }

    void lowerFiches(int count) {
        model.fiches -= count;
        model.notifyObserver();
        fb.playerUpdateFiches(model.getId(), model.fiches);
    }

    void higherFiches(int count) {
        model.fiches += count;
        model.notifyObserver();
        fb.playerUpdateFiches(model.getId(), model.fiches);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        if(gameCon.getCurrentPlayer()==this) return;
        //model.fiches = (int) Math.round(ds.getDouble("fiches"));
        model.notifyObserver();
    }

    public void returnFiches() {
        getActiveCombination().returnFiches();
    }

    public void addRoundPoints() {
        model.addPunten(getActiveCombination().getRace().getAreasAmount());
    }
}
