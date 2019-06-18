package Controller;

import Managers.SceneManager;
import Model.CombinationModel;
import Observer.CombinationObserver;
import observable.CombinationObservable;

import java.util.ArrayList;
import java.util.List;

public class CombinationController {

    private RaceController race;
    private PowerController power;
    private PlayerController player;
    private CombinationModel model;

    public void registerObserver(CombinationObserver obs){
        model.register(obs);
    }

    public boolean isActive(){
        return model.isActive();
    }

    public CombinationController(RaceController race, PowerController power){
        this.race = race;
        this.power = power;
        model = new CombinationModel(race.getId(), power.getId());
        //SceneManager.getInstance().loadCombination(this);
    }

    public void setPlayer(PlayerController player){
        this.player = player;
    }

    public PlayerController getPlayer(){
        return this.player;
    }



    public RaceController getRace(){
        return race;
    }

    public PowerController getPower(){
        return power;
    }

    public void returnFiches() {
            race.returnFiches();
    }

    public void setToNonActive() {
        model.setToNonActive();
    }

    public void destroyAllFichesButOne() {
        race.destroyAllFichesButOne();
    }
}
