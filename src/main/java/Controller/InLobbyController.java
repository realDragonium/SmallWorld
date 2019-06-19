package Controller;

import Firebase.FirebaseControllerObserver;
import Managers.SceneManager;
import Model.InLobbyModel;
import Observer.InLobbyObserver;
import Applicatie.Applicatie;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.Scene;

import java.util.Map;

public class InLobbyController implements FirebaseControllerObserver {
    Applicatie app = SceneManager.getInstance().getApp();
    InLobbyModel mod = new InLobbyModel();

    public InLobbyController(){
        SceneManager.getInstance().createInLobbyView(this);
    }

    public InLobbyController(String lobbyNaam, int playerAmount){
        SceneManager.getInstance().createInLobbyView(this);
        setLobbyNaam(lobbyNaam);
        SceneManager.getInstance().getApp().getFirebaseService().inLobbyListener(lobbyNaam, this);
    }

    public InLobbyController(String lobbyNaam){
        SceneManager.getInstance().createInLobbyView(this);
        setLobbyNaam(lobbyNaam);
    }

    public void setLobbyNaam(String lobbyNaam){
        mod.setLobbyNaam(lobbyNaam);
    }

    public void start(){            // start button
        new GameController(mod.getLobbyNaam(), app.getAccountCon().getPlayerId());  // starten van het spel
    }

    public void exitLobby(){
        Applicatie app = SceneManager.getInstance().getApp();
        app.getFirebaseService().leaveLobby(mod.getLobbyNaam(), app.getAccountCon().getAccountName());
        new LobbyController();
    }

    public void register(InLobbyObserver ob){
        mod.register(ob);
    }

    public void unregister(InLobbyObserver ob){
        mod.unregister(ob);
    }


    @Override
    public void update(DocumentSnapshot ds) {
        Map<String, Object> map = ds.getData();
        if(Boolean.valueOf(map.get("begin").toString())){
            new GameController(mod.getLobbyNaam(), app.getAccountCon().getPlayerId());
        }
    }
}
