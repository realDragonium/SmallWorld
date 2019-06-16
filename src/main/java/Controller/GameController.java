package Controller;

import Managers.SceneManager;
import Model.GameModel;

import java.util.HashMap;
import java.util.Map;

public class GameController {
    private GameModel model;
    private Map<String, PlayerController> players = new HashMap<>();
    private RoundController roundCon;
    private TurnController turnCon;
    private Map2DController mapCon;
    private AttackController attCon;

    public GameController() {
        model = new GameModel(8, 8);
        SceneManager.getInstance().createGameView(this);
        SceneManager.getInstance().makeMap();
        createGameParts();
    }

    public void createGameParts() {

        createPlayer();
        createTurnsAndRounds();
        new KnoppenController(this);
        createAttCon();
        mapCon = new Map2DController(this);
    }

    private void createPlayer(){
        players.put("player0", new PlayerController("player0", this));
        players.put("player1", new PlayerController("player1", this));
        players.put("player2", new PlayerController("player2", this));
        players.put("player3", new PlayerController("player3", this));
        players.put("player4", new PlayerController("player4", this));
    }

    private void createTurnsAndRounds(){
        roundCon = new RoundController(this);
        turnCon = new TurnController(this);
    }

    PlayerController getPlayer(String id){
        return players.get(id);
    }

    private void createAttCon(){
        attCon = new AttackController(this);
    }

    RoundController getRoundCon(){
        return roundCon;
    }

    TurnController getTurnCon(){
        return turnCon;
    }

    PlayerController getActivePlayerCon(){
        return players.get(turnCon.getCurrentPlayer());
    }

    Map2DController getMapCon(){
        return mapCon;
    }

    AttackController getAttCon(){
        return attCon;
    }

    void endGame(){
        System.out.println("Game Ended!");
        model.gameEnded = true;
    }

    boolean isGameOver(){
        return model.gameEnded;
    }
}
