package Objects;

import Enum.TurnFase;

public interface Power {

    void doAction();
    String getId();
    boolean checkPhaseAction(TurnFase phase);
}
