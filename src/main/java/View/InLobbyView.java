package View;

import Controller.InLobbyController;
import Controller.LobbyController;
import Observable.InLobbyObservable;
import Observer.InLobbyObserver;
import javafx.scene.Group;

public class InLobbyView implements InLobbyObserver {

    InLobbyController con;
    Group group;

    public Group root;

    public InLobbyView(Group group, InLobbyController con) {
        this.group = group;
        this.con = con;
    }

    public void initialize() {
        group.getChildren().add(root);
        con.register(this);
    }


    public void start(){            // start button
        con.start();  // starten van het spel
    }

    public void joinen(){
      //  spelerToevoegen();
    }

    public void exit(){
        con.exitLobby();
    }

//    public void start(){
//    }

    @Override
    public void update(InLobbyObservable ilo){
        if(ilo.getStart()){
            System.out.println("Gelukt!");
            start();
        }
    }


}
