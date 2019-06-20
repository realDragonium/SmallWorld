package Objects;

import Controller.AreaController;
import Controller.RaceController;
import Enum.TurnFase;
import Enum.AreaProperty;

public class DwarvesKracht implements Kracht{

    private TurnFase phase = TurnFase.redeploying;
    private RaceController raceCon;

    @Override
    public void setRaceCon(RaceController raceCon) {
        this.raceCon = raceCon;
    }

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        for(AreaController area : raceCon.getAllAreas()){
            if(area.getSpecialProp().equals(AreaProperty.mine)){
                raceCon.getCombiCon().getPlayer().addPoints(1);
            }
        }
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}
