package Controller;

import Firebase.FirebaseControllerObserver;
import Managers.SceneManager;
import Model.InLobbyModel;
import Observer.InLobbyObserver;
import Applicatie.Applicatie;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;
import java.util.Map;

/**  This Controller-class is part of the MVC design pattern and shows the In-existing lobby screen.
 * @author: Lars Puente Blom
 * @version: June 2019
 *
 */

public class InLobbyController implements FirebaseControllerObserver {
    Applicatie app = SceneManager.getInstance().getApp();
    InLobbyModel mod = new InLobbyModel();


    public InLobbyController(){
        SceneManager.getInstance().createInLobbyView(this);
    }

    /**
     *
     * @param lobbyNaam
     * @param id
     */
    public InLobbyController(String lobbyNaam, int id){
        app.getAccountCon().setPlayerId("player"+id);
        SceneManager.getInstance().createInLobbyView(this);
        setLobbyNaam(lobbyNaam);
        app.getFirebaseService().inLobbyListener(lobbyNaam, this);
    }

    /**
     * @param lobbyNaam
     * Creates the InlobbyView and sets the lobbynaam.
     * Puts
     */
    public InLobbyController(String lobbyNaam){
        SceneManager.getInstance().createInLobbyView(this);
        setLobbyNaam(lobbyNaam);
        SceneManager.getInstance().getApp().getFirebaseService().inLobbyListener(lobbyNaam, this);
    }

    /**
     * @param lobbyNaam
     */
    public void setLobbyNaam(String lobbyNaam){
        mod.setLobbyNaam(lobbyNaam);
    }

    /**
     *
     */
    public void start(){            // start button
        SceneManager.getInstance().getApp().getFirebaseService().startGame(mod.getLobbyNaam());
        //new GameController(mod.getLobbyNaam(), app.getAccountCon().getPlayerId());  // starten van het spel
    }


    public void exitLobby(){
        Applicatie app = SceneManager.getInstance().getApp();
        app.getFirebaseService().leaveLobby(mod.getLobbyNaam(), app.getAccountCon().getAccountName());
        new LobbyController();
    }

    /**
     * @param ob registers the observer and giving it to the register method in the InlobbyModel
     */
    public void register(InLobbyObserver ob){
        mod.register(ob);
    }


    /**
     *
     * @param ob
     */
    public void unregister(InLobbyObserver ob){
        mod.unregister(ob);
    }

    /**
     * @param ds
     */
    @Override
    public void update(DocumentSnapshot ds) {
        Map<String, Object> map = ds.getData();
        mod.setPlayer( 1, (String)map.get("player1"));
        mod.setPlayer( 2, (String)map.get("player2"));
        mod.setPlayer( 3, (String)map.get("player3"));
        mod.setPlayer( 4, (String)map.get("player4"));
        if(((Boolean)map.get("begin"))){
            Platform.runLater(() -> new GameController(mod.getLobbyNaam(), app.getAccountCon().getPlayerId()));
        }
    }



}
