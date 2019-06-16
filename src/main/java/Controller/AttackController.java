package Controller;

public class AttackController {

    private GameController gameCon;
    private AreaController targetArea;

    AttackController(GameController gameCon){
        this.gameCon = gameCon;
    }

    void getTargetArea(){
        targetArea = gameCon.getMapCon().getActiveAreas().get(0);
    }

    void attackArea(){
        final int fichesCountNeeded = targetArea.numbersNeeded();
        final PlayerController player = gameCon.getActivePlayerCon();
        if(player.getActiveRace().hasEnoughFiches(fichesCountNeeded)) {
//            if (targetArea.getPlayer() != null)
            targetArea.getPlayer().getActiveRace().pushFiches(targetArea.removeFiches());
//            else {
//                targetArea.removeFiches();
//            }
            targetArea.attackArea(gameCon.getActivePlayerCon().getActiveRace().getFiches(fichesCountNeeded));
            targetArea.setPlayerOwner(gameCon.getActivePlayerCon());
        } else System.out.println("Niet genoeg fiches in je bezit!");
    }
}
