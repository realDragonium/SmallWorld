package Objects;

import Controller.CombinationController;
import Enum.TurnFase;

public interface Power {

    void doAction();
    String getId();
    boolean checkPhaseAction(TurnFase phase);

    void setCombiCon(CombinationController combinationController);
}
