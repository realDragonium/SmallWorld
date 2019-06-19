package Objects;

import Enum.TurnFase;

public class TritansKracht implements Kracht{

    private TurnFase phase = TurnFase.conquering;

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        System.out.println("Op landen naast water heb je 1 extra aanvals punt");
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}
