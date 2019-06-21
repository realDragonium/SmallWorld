package Controller;

import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;

public class RedeployingController {

    GameController gameCon;
    FirebaseServiceOwn fb = SceneManager.getInstance().getApp().getFirebaseService();

    RedeployingController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadRedeploying(this);

    }

    public void removeFiche() {
        AreaController activeArea = getActiveArea();
        PlayerController player = gameCon.getCurrentPlayer();

        if(activeArea != null){
            fb.areaUpdateFiches(activeArea.getId(), 0);
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
        AreaController activeArea = getActiveArea();
        PlayerController player = gameCon.getCurrentPlayer();

        if(activeArea != null){
            fb.areaUpdateFiches(activeArea.getId(), 0);
            if(activeArea.getOwnerPlayer().getId().equals(player.getId())){
                if(player.getActiveCombination().getRace().hasEnoughFiches(1)){
                    activeArea.addFiche(player.getActiveCombination().getRace().removeFiche());
                }
            }
        }
    }

    private AreaController getActiveArea(){
        AreaController activeArea = null;

        if(gameCon.getMapCon().getActiveAreas().size() > 0){
            activeArea = gameCon.getMapCon().getActiveAreas().get(0);
        }
        return activeArea;
    }
}
