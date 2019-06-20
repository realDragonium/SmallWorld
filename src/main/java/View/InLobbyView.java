package View;

import Controller.InLobbyController;
import Observable.InLobbyObservable;
import Observer.InLobbyObserver;
import javafx.scene.Group;
import javafx.scene.text.Text;

/**  This View-class is part of the MVC design pattern and shows the In-existing lobby screen.
 * @author: Lars Puente Blom
 * @version: June 2019
 *
 */

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


    // starten van het spel
    public void start(){
        con.start();
    }

//    public void joinen(){
//      //  spelerToevoegen();
//    }

    public void exit(){
        con.exitLobby();
    }

    @Override
    public void update(InLobbyObservable ilo){
        Player1.setText(ilo.getPlayer1());
        Player2.setText(ilo.getPlayer2());
        Player3.setText(ilo.getPlayer3());
        Player4.setText(ilo.getPlayer4());
        if(ilo.getStart()){
            //System.out.println("Gelukt!");
            start();
        }
    }


}
