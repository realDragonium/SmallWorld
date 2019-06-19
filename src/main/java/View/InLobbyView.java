package View;

import Controller.InLobbyController;
import Controller.LobbyController;
import Observable.InLobbyObservable;
import Observer.InLobbyObserver;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class InLobbyView implements InLobbyObserver {

    InLobbyController con;
    Group group;

    public Group root;
    public Text Player1;
    public Text Player2;
    public Text Player3;
    public Text Player4;


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
        System.out.println(ilo.getPlayer1());
        System.out.println(ilo.getPlayer2());
        System.out.println(ilo.getPlayer3());
        System.out.println(ilo.getPlayer4());
        Player1.setText(ilo.getPlayer1());
        Player2.setText(ilo.getPlayer2());
        Player3.setText(ilo.getPlayer3());
        Player4.setText(ilo.getPlayer4());
        if(ilo.getStart()){

            System.out.println("Gelukt!");
            start();
        }
    }


}
