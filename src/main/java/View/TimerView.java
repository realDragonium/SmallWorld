package View;

import Controller.TimerController;
import Observer.TimerObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import Observable.TimerObservable;
import javafx.scene.text.Text;


/** @author Yoran de Vos
 *  De TimerView class weergeeft een timer aan de speler, zodat die weet hoeveel tijd die heeft in een spelfase.
 *
 */


public class TimerView implements TimerObserver {

    TimerController timerCon;
    Group group;

    @FXML
    public Pane pane;

    @FXML
    public Text timer;


    /**
     *
     * @param group wordt in de constructor meegegeven om alle omvattende nodes uit het fxml mee te sturen.
     * @param timerCon is een variable van de TimerController waar alle logica staat.
     */


    public TimerView(Group group, TimerController timerCon){
        this.group = group;
        this.timerCon = timerCon;
        timerCon.registerObs(this);
    }

    public void initialize(){
        group.getChildren().add(pane);
    }


    /**
     *
     * @param ao is van de TimerObservable. Wordt meegestuurd aan de update methode, waardoor de View (observer) een update krijgt van de model (observable)
     */

    @Override
    public void update(TimerObservable ao) {
        String minutes = Integer.toString((ao.getSeconds() - ao.getSeconds() % 60) / 60);
        String seconds = Integer.toString(ao.getSeconds() % 60);
        if(seconds.length() == 1){
            seconds = "0" + seconds;
        }
        String time = "Time left: " + minutes + ":" + seconds ;
        timer.setText(time);
    }


}
