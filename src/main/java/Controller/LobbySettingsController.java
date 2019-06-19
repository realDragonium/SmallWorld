package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.LobbySettingsModel;
import Observer.LobbySettingsObserver;

public class LobbySettingsController {
    Applicatie app = SceneManager.getInstance().getApp();
    FirebaseServiceOwn fb = app.getFirebaseService();
    LobbySettingsModel mod = new LobbySettingsModel();

    public LobbySettingsController(){
        SceneManager.getInstance().createLobbySettingsView(this);
    }

    public void lobbyView(){
        new LobbyController();
    }

    public void startLobby(String lobbyNaam, int playerAmount){
        fb.createLobby(playerAmount, lobbyNaam, app.getAccountCon().getAccountName());
        new InLobbyController(lobbyNaam, 1);
    }

    public void register(LobbySettingsObserver ob){
        mod.register(ob);
    }




}
