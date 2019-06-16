package Controller;

public class CombinationController {

    private RaceController race;
    private PowerController power;

    public CombinationController(RaceController race, PowerController power){
        this.race = race;
        this.power = power;
    }

    public RaceController getRace(){
        return race;
    }

    public PowerController getPower(){
        return power;
    }
}
