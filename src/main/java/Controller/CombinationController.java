package Controller;

public class CombinationController {

    private RaceController race;
    private PowerController power;
    private PlayerController player;

    public CombinationController(RaceController race, PowerController power){
        this.race = race;
        this.power = power;
    }

    public void setPlayer(PlayerController player){
        this.player = player;
    }

    public PlayerController getPlayer(){
        return this.player;
    }

    public RaceController getRace(){
        return race;
    }

    public PowerController getPower(){
        return power;
    }

    public void returnFiches() {
        race.returnFiches();
    }
}
