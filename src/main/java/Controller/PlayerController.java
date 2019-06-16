package Controller;

import Managers.SceneManager;
import Model.PlayerModel;
import Observer.PlayerObserver;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    private GameController gameCon;
    private PlayerModel model;
    private List<CombinationController> combinations = new ArrayList<>();

    public PlayerController(String playerID, GameController gameCon) {
        model = new PlayerModel(playerID);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadPlayer(playerID, this);
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
    }

    void lowerFiches(int count) {
        model.fiches -= count;
        model.notifyObserver();
    }

    void higherFiches(int count) {
        model.fiches += count;
        model.notifyObserver();
    }

}
