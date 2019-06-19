package Controller;

import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.TimerModel;
import Observer.TimerObserver;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class TimerController {

    GameTurn gameTurn;
    TimerModel model = new TimerModel();

    public void registerObs(TimerObserver timerObs){
        model.register(timerObs);
    }

//    public void resetTimer() {
//
//        TimerTask stop = new TimerTask() {
//
//            @Override
//            public void run() {
//                countDown = 60;
//                System.out.println("Reset");
//            }
//        };
//        timer.cancel();
//        timer = new Timer();
//        timer.schedule(stop, 1000);
//    }

    public TimerController(GameTurn gameTurn){
        this.gameTurn = gameTurn;
        SceneManager.getInstance().loadTimer(this);
    }

    public long getElapsedTime(){
        return model.getSeconds();
    }


    public void setTime(int timer) {
        model.setTimer(timer);
    }
}
