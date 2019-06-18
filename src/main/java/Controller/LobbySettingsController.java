package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.LobbySettingsModel;
import Observer.LobbySettingsObserver;
import View.LobbyView;

public class LobbySettingsController {
    Applicatie app = SceneManager.getInstance().getApp();
    FirebaseServiceOwn fb = app.getFirebaseService();
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

    public void startLobby(String lobbyNaam, int playerAmount){
        fb.createLobby(playerAmount, lobbyNaam, app.getAccountCon().getAccountName());
        new InLobbyController(lobbyNaam, playerAmount);
    }

    public void register(LobbySettingsObserver ob){
        mod.register(ob);
    }




}
