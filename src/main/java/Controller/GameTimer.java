package Controller;

import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    GameController gameCon;
    int timeLeft;
    Timer gameTimer;

    public GameTimer(GameController gameCon, int time) {
        this.gameCon = gameCon;
        timeLeft = time;
        FirebaseServiceOwn fb = SceneManager.getInstance().getApp().getFirebaseService();
        fb.updateTimer(false, time);
        TimerTask start = new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        timeLeft --;

                        if (timeLeft == 0) {
                            timeLeft = time;
                            fb.updateTimer(true, time);
                        }
                    }
                });
            }
        };
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(start, 0, 1000);
    }

    public void endPhase(){
        timeLeft = 1;
    }

    public void stopTimer(){
        gameTimer.cancel();
    }
}
