package Objects;

import Enum.TurnFase;

public class WizzardsKracht implements Kracht{

    private TurnFase phase = TurnFase.redeploying;

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        System.out.println("op bepaalde gebieden plus 1");
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}