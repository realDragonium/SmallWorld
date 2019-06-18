package Objects;

import Enum.TurnFase;

public interface Kracht {

    Kracht getKracht();

    void doAction();

    boolean checkPhaseAction(TurnFase curPhase);
}
