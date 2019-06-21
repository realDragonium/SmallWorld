package Controller;

import Enum.TurnFase;
import Managers.SceneManager;

import java.util.List;

/** This Controller-class handles the logic for the methods that can be called in the conquer phase
 *
 * @author yoran, beau
 * @version June 2019
 */

public class AttackController {

    private GameController gameCon;
    private AreaController targetArea;
    private int fichesCountNeeded;
    private boolean diceUsed = false;

    /**
     * Constructor
     * @param gameCon
     */

    AttackController(GameController gameCon) {

        this.gameCon = gameCon;
        SceneManager.getInstance().loadAttack(this);
    }

    /**
     * Removes given amount of fiches
     * @param amount
     */

    public void removeFichesNeeded(int amount){
        fichesCountNeeded -= amount;
    }

    /**
     *
     * @return current targetArea in attack controller
     */

    public AreaController getAttackArea(){
        return targetArea;
    }

    private void getTargetArea() {
        targetArea = gameCon.getMapCon().getActiveAreas().get(0);
    }

    private void attackAreaLocal() {
        fichesCountNeeded = targetArea.numbersNeeded();
        final PlayerController player = gameCon.getCurrentPlayer();
        if(player.hasActiveCombination()){
            player.getActiveCombination().checkForSpecialActions(TurnFase.conquering);
            if (player.getActiveCombination().getRace().hasEnoughFiches(fichesCountNeeded)) {
                if(player.getActiveCombination().getRace().getAllAreas().size() == 0) firstAttack(player);
                else isNeighbour(player);
            }
            else if(player.getActiveCombination().getRace().fichesCount() == 1 && !diceUsed){
                int waarde = gameCon.getDiceCon().ClickedDice();
                diceUsed = true;
                if (player.getActiveCombination().getRace().hasEnoughFiches(fichesCountNeeded - waarde)) {
                    attack(player, 1);
                }
            }
        }
    }

    /**
     * called if it is the players first attack
     * @param player current attacking player
     */

    private void firstAttack(PlayerController player){
        if(targetArea.firstAttackArea()){
            attack(player, fichesCountNeeded);
        }
    }

    /**
     *  call to initialize a attack
     * @param player current attacking player
     * @param fiches amount of fiches
     */

    private void attack(PlayerController player, int fiches){
        if(!targetArea.isAttackAble()) return;
        if (targetArea.getOwnerPlayer() != null && targetArea.getOwnerPlayer() != gameCon.getMyPlayer()) {
            targetArea.getOwnerPlayer().getActiveCombination().getRace().pushFiches(targetArea.removeFiches());
            targetArea.getOwnerPlayer().getActiveCombination().getRace().removeArea(targetArea);
        }
        player.getActiveCombination().getRace().addArea(targetArea);
        targetArea.attackArea(player.getActiveCombination().getRace().getFiches(fiches));
        targetArea.setPlayerOwner(player);
    }

    public void attackCountry() {
        getTargetArea();
        attackAreaLocal();
    }

    /**
     * call if the arrea is a neighbour of current area
     * @param player
     */

    private void isNeighbour(PlayerController player){
        if(isNeighbour(player.getActiveCombination().getRace().getAllAreas())){
            attack(player, fichesCountNeeded);
        }
    }


    /**
     * checking if the selected area is a neigbouring area
     * @param areas
     * @return false if not neighbour, true if neighbour
     */
    private boolean isNeighbour(List<AreaController> areas){
        String Id = targetArea.getId();
        for(AreaController area : areas){
            if(area.getNeighbours().contains(Id)) return true;
        }
        return false;
    }

}
