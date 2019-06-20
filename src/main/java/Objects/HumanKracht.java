package Objects;

import Enum.TurnFase;

public class HumanKracht implements Kracht{

    private TurnFase phase = TurnFase.redeploying;

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        System.out.println("op fields heb je een extra punt");
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}
