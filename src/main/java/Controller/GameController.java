package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseServiceOwn;
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
    private VervallenController vervCon;
    private AttackController attCon;
    private ShopController shopCon;
    private GameTurn gameTurn;
    private PlayerController myPlayer;
    private String myPlayerId;


    public GameController(String lobbyName, String playerID) {
        myPlayerId = playerID;
        model = new GameModel(8, 8);
        SceneManager.getInstance().getApp().getFirebaseService().setGame(lobbyName);
        System.out.println("test");
        SceneManager.getInstance().createGameView(this);
        System.out.println("test2");
        SceneManager.getInstance().makeMap();
        createGameParts();
        startGame();

    }

    public String getMyPlayerId(){
        return myPlayerId;
    }

    public PlayerController getPlayer(){
        return currentPlayer;
    }

    public void changePlayerTurn(String player){
        currentPlayer = players.get(player);
        createGameTurn();
    }

    public void createGameParts() {
        createPlayer();
        createShop();
        createVerval();

        createTurnsAndRounds();
        new DiceController();
        new KnoppenController(this);

        createAttCon();
        mapCon = new Map2DController(this);
    }

    private void createVerval() {
        vervCon = new VervallenController(this);
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

    private void createGameTurn(){
        gameTurn = new GameTurn(this, currentPlayer);
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

    VervallenController getVervCon(){return vervCon;}

    AttackController getAttCon(){
        return attCon;
    }

    GameTurn getGameTurn() { return gameTurn;}

    void endGame(){
        System.out.println("Game Ended!");
        model.gameEnded = true;
    }

    boolean isGameOver(){
        return model.gameEnded;
    }


    private void startGame(){
        gameTurn = new GameTurn(this, currentPlayer);
    }

    public void nextTurn() {
        turnCon.nextTurn();
    }

    public PlayerController getMyPlayer() {
        return myPlayer;
    }

    public void setCurrentPlayer(int i) {
        currentPlayer = getPlayer("player" + i);
    }
}
