package Controller;

import Managers.SceneManager;
import Model.LobbySettingsModel;
import Observer.LobbySettingsObserver;
import View.LobbyView;

public class LobbySettingsController {

    LobbySettingsModel mod = new LobbySettingsModel();

    public LobbySettingsController(){
        SceneManager.getInstance().createLobbySettingsView(this);
    }

    public void lobbyView(String lobbyNaam, String playerAmount){
        new LobbyController(lobbyNaam, playerAmount);
    }

    public void lobbyView(){
        new LobbyController();
    }

    public void startLobby(){
        new InLobbyController();
    }

    public void register(LobbySettingsObserver ob){
        mod.register(ob);
    }




}
