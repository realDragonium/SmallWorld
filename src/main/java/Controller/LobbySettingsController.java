package Controller;

import Managers.SceneManager;
import Model.LobbySettingsModel;
import Observer.LobbySettingsObserver;
import View.LobbyView;

public class LobbySettingsController {

    LobbySettingsModel mod = new LobbySettingsModel();
    LobbyController con = new LobbyController();

   // private String lobbyNaam;

    public LobbySettingsController(){
        SceneManager.getInstance().createLobbySettingsView(this);
    }


//    public LobbySettingsController(String lobbyNaam){
//        SceneManager.getInstance().createLobbySettingsView(this);
//        //setLobbyNaam(lobbyNaam);
//    }

//
//    public void setLobbyName(String lobbyNaam){
//        con.setLobbyName(lobbyNaam);
//    }













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
