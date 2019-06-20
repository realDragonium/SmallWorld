package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.LobbySettingsModel;
import Observer.LobbySettingsObserver;


/**  This Controller-class is part of the MVC design pattern and shows the lobbySettings screen.
 * @author: Lars Puente Blom
 * @version: June 2019
 */

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
