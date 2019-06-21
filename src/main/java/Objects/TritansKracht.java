package Objects;

import Controller.RaceController;
import Enum.TurnFase;

public class TritansKracht implements Kracht{

    private TurnFase phase = TurnFase.conquering;
    private RaceController raceCon;

    @Override
    public void setRaceCon(RaceController raceCon){
        this.raceCon = raceCon;
    }

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        System.out.println();
        if(raceCon.getCombiCon().getPlayer().getGameCon().getAttCon().getAttackArea().isNextToWater()){
            raceCon.getCombiCon().getPlayer().getGameCon().getAttCon().removeFichesNeeded(1);
        }
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}
