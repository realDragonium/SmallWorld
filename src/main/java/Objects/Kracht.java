package Objects;

import Controller.RaceController;
import Enum.TurnFase;

public interface Kracht {

    void setRaceCon(RaceController raceCon);

    Kracht getKracht();

    void doAction();

    boolean checkPhaseAction(TurnFase curPhase);
}
