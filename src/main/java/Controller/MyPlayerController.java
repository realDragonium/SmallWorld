package Controller;

import Managers.SceneManager;
import Observer.PlayerObserver;

public class MyPlayerController {

    private GameController gameCon;

    public MyPlayerController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadMyPlayer(this);
    }

    public void registerAtMyPlayer(PlayerObserver mpO){
        gameCon.getPlayer(gameCon.getMyPlayerId()).register(mpO);
    }

    public void showMyActiveAreas(){
        for(AreaController area :gameCon.getPlayer(gameCon.getMyPlayerId()).getActiveCombination().getRace().getAllAreas()){
            area.highLightArea();
        }
    }
}
