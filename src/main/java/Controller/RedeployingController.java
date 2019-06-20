package Controller;

import Managers.SceneManager;

public class RedeployingController {

    GameController gameCon;

    public RedeployingController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadRedeploying(this);

    }

    public void removeFiche() {
        PlayerController player = gameCon.getCurrentPlayer();
        AreaController activeArea = gameCon.getMapCon().getActiveAreas().get(0);
        if(activeArea != null){
            if(activeArea.getOwnerPlayer().getId().equals(player.getId())){
                if(activeArea.getFichesAmount() >= 1){
                    player.getActiveCombination().getRace().addFiche(activeArea.getOneFiche());
                    if(activeArea.getFichesAmount() == 1){
                        player.addPoints(-1);
                    }
                }

            }
        }
    }

    public void addFiche() {
    }
}
