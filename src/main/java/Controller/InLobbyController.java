package Controller;

import Managers.SceneManager;
import Model.InLobbyModel;
import Observer.InLobbyObserver;

public class InLobbyController {

    InLobbyModel mod = new InLobbyModel();

    public InLobbyController(){
        SceneManager.getInstance().createInLobbyView(this);
    }

    public InLobbyController(String lobbyNaam){
        SceneManager.getInstance().createInLobbyView(this);
        setLobbyNaam(lobbyNaam);
    }





    public void setLobbyNaam(String lobbyNaam){
        mod.setLobbyNaam(lobbyNaam);
    }



    public void start(){            // start button
        new GameController("First", "player1");  // starten van het spel
    }

    public void exitLobby(){
        new LobbyController();
    }

    public void register(InLobbyObserver ob){
        mod.register(ob);
    }

    public void unregister(InLobbyObserver ob){
        mod.unregister(ob);
    }






}
