package Controller;

import Enum.TurnFase;
import Managers.SceneManager;

public class AttackController {

    private GameController gameCon;
    private AreaController targetArea;
    private int fichesCountNeeded;
    private boolean diceUsed = false;


    AttackController(GameController gameCon) {

        this.gameCon = gameCon;
        SceneManager.getInstance().loadAttack(this);
    }

    public void removeFichesNeeded(int amount){
        fichesCountNeeded -= amount;
    }

    public AreaController getAttackArea(){
        return targetArea;
    }

    void getTargetArea() {
        targetArea = gameCon.getMapCon().getActiveAreas().get(0);
    }

    void attackAreaLocal() {
        fichesCountNeeded = targetArea.numbersNeeded();
        final PlayerController player = gameCon.getCurrentPlayer();
        System.out.println(player.getId() + " is attacking");
        if(player.hasActiveCombination()){
            player.getActiveCombination().checkForSpecialActions(TurnFase.conquering);
            if (player.getActiveCombination().getRace().hasEnoughFiches(fichesCountNeeded)) {
                attack(player);
            }
            else if(player.getActiveCombination().getRace().fichesCount() == 1 && !diceUsed){
                int waarde = gameCon.getDiceCon().ClickedDice();
                diceUsed = true;
                if (player.getActiveCombination().getRace().hasEnoughFiches(fichesCountNeeded + waarde)) {
                    attack(player);
                }
            }
        }
    }


    void attack(PlayerController player){
        if (targetArea.getOwnerPlayer() != null) {
            targetArea.getOwnerPlayer().getActiveCombination().getRace().pushFiches(targetArea.removeFiches());
            targetArea.getOwnerPlayer().getActiveCombination().getRace().removeArea(targetArea);
        }
        player.getActiveCombination().getRace().addArea(targetArea);
        targetArea.attackArea(player.getActiveCombination().getRace().getFiches(fichesCountNeeded));
        targetArea.setPlayerOwner(player);
    }

    void attackFromFirebase(){

    }

    public void attackCountry() {
        getTargetArea();
        attackAreaLocal();
    }
}
