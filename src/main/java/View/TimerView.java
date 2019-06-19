package View;

import Controller.TimerController;
import Observer.TimerObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import Observable.TimerObservable;

public class TimerView implements TimerObserver {

    TimerController timerCon;
    Group group;

    @FXML
    public Pane pane;

    @FXML
    public TextField timer;

    public TimerView(Group group, TimerController timerCon){
        this.group = group;
        this.timerCon = timerCon;
        timerCon.registerObs(this);
    }

    public void initialize(){
        group.getChildren().add(pane);
    }

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
