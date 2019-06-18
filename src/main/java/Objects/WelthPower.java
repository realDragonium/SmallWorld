package Objects;

import Enum.TurnFase;

public class WelthPower implements Power {

    private String id = "Welth";
    private boolean used = false;
    private TurnFase usablePhase = TurnFase.preparing;

    @Override
    public void doAction(){
        System.out.println("plus 7 points");
        used = true;
    }



    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean checkPhaseAction(TurnFase phase) {
        return phase.equals(usablePhase);
    }
}
