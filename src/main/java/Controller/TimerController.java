package Controller;

import Managers.SceneManager;
import Model.TimerModel;
import Observer.TimerObserver;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class TimerController {

    GameTurn gameTurn;
    TimerModel model = new TimerModel();

    Timer timer;

    public void startTimer() {
        TimerTask start = new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        model.addSecond();
                        if (model.timerIsDone()) {
                            timerEnded();
                        }
                    }
                });
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(start, 0, 1000);
    }

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
        startTimer();
    }



    public long getElapsedTime(){
        return model.getSeconds();
    }

    public void timerEnded(){
        timer.cancel();
        gameTurn.endPhase();

    }
}
