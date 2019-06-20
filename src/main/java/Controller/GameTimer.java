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
    boolean current = false;
    FirebaseServiceOwn fb;

    public GameTimer(GameController gameCon, int time) {
        maxTime = time;
        this.gameCon = gameCon;
        timeLeft = time;
        fb = SceneManager.getInstance().getApp().getFirebaseService();

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
        if (timeLeft == 0 && gameCon.getCurrentPlayer().getId().equals(gameCon.getMyPlayerId())) {
            Map<String, Object> info = new HashMap<>();
            current = !current;
            info.put("endPhase", current);
            info.put("time", maxTime);
            fb.resetTimer(info);
        }
    }

    public void resetTimer(){
        timeLeft = maxTime;
    }

    public void endPhase(){
        Platform.runLater(()->timeLeft = 1);
    }

    public void stopTimer(){
        gameTimer.cancel();
    }
}
