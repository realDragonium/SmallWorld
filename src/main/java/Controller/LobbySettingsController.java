package Controller;

import Managers.SceneManager;
import Model.LobbySettingsModel;
import Observer.LobbySettingsObserver;

public class LobbySettingsController {

    LobbySettingsModel mod = new LobbySettingsModel();
    InLobbyController con;


    public LobbySettingsController(){
        SceneManager.getInstance().createLobbySettingsView(this);
    }

    public void startLobby(){
        new InLobbyController();
    }

    public void register(LobbySettingsObserver ob){
        mod.register(ob);
    }



}
