package Controller;

import Managers.SceneManager;
import Model.CombinationModel;
import Objects.Power;
import Observer.CombinationObserver;
import observable.CombinationObservable;
import Enum.TurnFase;
import java.util.ArrayList;
import java.util.List;

public class CombinationController {

    private RaceController race;
    private Power power;
    private PlayerController player;
    private CombinationModel model;

    public void registerObserver(CombinationObserver obs){
        model.register(obs);
    }

    public boolean isActive(){
        return model.isActive();
    }

    public CombinationController(RaceController race, Power power){
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

    public void checkForSpecialActions(TurnFase curPhase){
        if(race.checkPhaseActoin(curPhase)){
            race.doKractAction();
        }
        if(power.checkPhaseAction(curPhase)){
            power.doAction();
        }
    }

    public RaceController getRace(){
        return race;
    }

    public Power getPower(){
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
