package Controller;

public class AttackController {

    private GameController gameCon;
    private AreaController targetArea;

    AttackController(GameController gameCon) {
        this.gameCon = gameCon;
    }

    void getTargetArea() {
        targetArea = gameCon.getMapCon().getActiveAreas().get(0);
    }

    void attackArea() {
        final int fichesCountNeeded = targetArea.numbersNeeded();
        final PlayerController player = gameCon.getCurrentPlayer();
        System.out.println(player.getId() + " is attacking");
        if (player.getActiveCombination().getRace().hasEnoughFiches(fichesCountNeeded)) {
            if (targetArea.getOwnerPlayer() != null) {
                System.out.println("OMG er staat iemand op... dan maar met meer aanvallen!");
                targetArea.getOwnerPlayer().getActiveCombination().getRace().pushFiches(targetArea.removeFiches());
                targetArea.getOwnerPlayer().getActiveCombination().getRace().removeArea(targetArea);
            }
            System.out.println("Aanvallen met: " + fichesCountNeeded);
            player.getActiveCombination().getRace().addArea(targetArea);
            targetArea.attackArea(player.getActiveCombination().getRace().getFiches(fichesCountNeeded));
            targetArea.setPlayerOwner(player);
        } else System.out.println("Niet genoeg fiches in je bezit!");
    }
}
