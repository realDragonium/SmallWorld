package Controller;

import Managers.SceneManager;
import Model.GameModel;
import Objects.RattenKracht;

import java.util.HashMap;
import java.util.Map;

public class GameController {
    private GameModel model;
    private Map<String, PlayerController> players = new HashMap<>();
    private PlayerController currentPlayer;
    private RoundController roundCon;
    private TurnController turnCon;
    private Map2DController mapCon;
    private AttackController attCon;
    private ShopController shopCon;

    public GameController() {
        model = new GameModel(8, 8);
        SceneManager.getInstance().createGameView(this);
        SceneManager.getInstance().makeMap();
        createGameParts();
    }

    public PlayerController getPlayer(){
        return currentPlayer;
    }

    public void changePlayerTurn(String player){
        currentPlayer = players.get(player);
    }

    public void createGameParts() {

        createPlayer();
        createShop();
        createTurnsAndRounds();
        new DiceController();
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

    private void createShop(){
        shopCon = new ShopController(this);
    }

    private void createTurnsAndRounds(){
        roundCon = new RoundController(this);
        turnCon = new TurnController(this);
    }



    PlayerController getPlayer(String id){
        return players.get(id);
    }

    public PlayerController getCurrentPlayer(){
        return players.get(turnCon.getCurrentPlayer());
    }

    private void createAttCon(){
        attCon = new AttackController(this);
    }

    RoundController getRoundCon(){
        return roundCon;
    }

    ShopController getShopCon(){return shopCon;}

    TurnController getTurnCon(){
        return turnCon;
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
