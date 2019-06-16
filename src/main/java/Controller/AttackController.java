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
        final PlayerController player = gameCon.getPlayer();
        System.out.println(gameCon.getPlayer().getId() + " is attacking");
        if(player.getActiveCombination().getRace().hasEnoughFiches(fichesCountNeeded)) {
           if (targetArea.getPlayer() != null) {
               targetArea.getPlayer().getActiveCombination().getRace().pushFiches(targetArea.removeFiches());
           }
//            else {
//                targetArea.removeFiches();
//            }
            targetArea.attackArea(gameCon.getPlayer().getActiveCombination().getRace().getFiches(fichesCountNeeded));
            targetArea.setPlayerOwner(gameCon.getPlayer());
        } else System.out.println("Niet genoeg fiches in je bezit!");
    }
}
