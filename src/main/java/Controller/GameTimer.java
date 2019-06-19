package Controller;

import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import javafx.application.Platform;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    GameController gameCon;
    int timeLeft;
    int maxTime;
    Timer gameTimer;
    FirebaseServiceOwn fb;

    public GameTimer(GameController gameCon, int time) {
        maxTime = time;
        this.gameCon = gameCon;
        timeLeft = time;
        System.out.println("maakt gameTimer aan");
        fb = SceneManager.getInstance().getApp().getFirebaseService();
        fb.updateTimer(false, time);
        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> timerAction());
            }
        };

        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(start, 0, 1000);
    }

    public void timerAction(){
        timeLeft--;
        gameCon.getGameTurn().phaseTimer.setTime(timeLeft);
        if (timeLeft == 0) {
            Map<String, Object> info = new HashMap<>();
            info.put("endPhase", true);
            info.put("time", maxTime);
            System.out.println(gameCon.getLobbyname());
            fb.getFireStore().collection("Games").document(gameCon.getLobbyname()).collection("Extras").document("Timer").set(info);
        }
    }

    public void resetTimer(){
        timeLeft = maxTime;
    }

    public void endPhase(){
        timeLeft = 1;
    }

    public void stopTimer(){
        gameTimer.cancel();
    }
}
