package Objects;

import Enum.TurnFase;

public class AlchemistPower implements Power {

    private String id = "Alchemist";

    @Override
    public void doAction(){
        System.out.println("alchemist");
    }



    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean checkPhaseAction(TurnFase phase) {
        return false;
    }
}
