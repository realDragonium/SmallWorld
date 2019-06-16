package Controller;

import Managers.SceneManager;
import Model.PlayerModel;
import Observer.PlayerObserver;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    private GameController gameCon;
    private PlayerModel model;
    private List<RaceController> raceCons = new ArrayList<>();


    public PlayerController(String playerID, GameController gameCon){
        model = new PlayerModel(playerID);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadPlayer(playerID, this);
    }

    public void makeNewRace(){
        raceCons.add(new RaceController(this));
    }

    RaceController getActiveRace(){
        return raceCons.get(0);
    }

    String getId(){
        return model.getId();
    }

    public void register(PlayerObserver po){
        model.register(po);
    }

    public void setFiches(int fiches){
        model.fiches = fiches;
        model.notifyObserver();
    }

    void lowerFiches(int count){
        model.fiches -= count;
        model.notifyObserver();
    }

    void higherFiches(int count){
        model.fiches += count;
        model.notifyObserver();
    }

}
