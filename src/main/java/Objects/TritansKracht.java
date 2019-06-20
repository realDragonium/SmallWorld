package Objects;

import Controller.RaceController;
import Enum.TurnFase;

public class TritansKracht implements Kracht{

    private TurnFase phase = TurnFase.conquering;
    private RaceController raceCon;

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        if(raceCon.getCombiCon().getPlayer().getGameCon().getAttCon().getAttackArea().isNextToWater()){
            raceCon.getCombiCon().getPlayer().getGameCon().getAttCon().removeFichesNeeded(1);
        }
        System.out.println("Op landen naast water heb je 1 extra aanvals punt");
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}
