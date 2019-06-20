package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.GameModel;
import Objects.RattenKracht;
import com.google.cloud.firestore.Firestore;

import javax.sound.sampled.Line;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ExecutionException;

public class GameController {
    private GameModel model;
    private Map<String, PlayerController> players = new HashMap<>();
    private PlayerController currentPlayer;
    private RoundController roundCon;
    private TurnController turnCon;
    private Map2DController mapCon;
    private String lobbyName;
    private VervallenController vervCon;
    private TimerController timeCon;
    private GameTimer gameTimer;
    private AttackController attCon;
    private ShopController shopCon;
    private GameTurn gameTurn;
    private PlayerController myPlayer;
    private RedeployingController redCon;
    private String myPlayerId;
    private DiceController diceCon;
    private Applicatie app = SceneManager.getInstance().getApp();
    private FirebaseServiceOwn fb = app.getFirebaseService();

    public GameController(String lobbyName, String playerID) {
        System.out.println(this);
        myPlayerId = playerID;
        model = new GameModel(8, 8);
        this.lobbyName = lobbyName;
        setMuFirebaseStufF();
        SceneManager.getInstance().createGameView(this);
        SceneManager.getInstance().makeMap();
        createGameParts();
        startGame();
        createGameTimer();
    }

    public void setMuFirebaseStufF(){
        fb.setGame(lobbyName);
        Map<String, Object> info = new HashMap<>();
        info.put("Name", app.getAccountCon().getAccountName());
        info.put("fiches", 0);
        info.put("punten", 0);
        fb.registerPlayer(myPlayerId, info);
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
        SceneManager.getInstance().loadSmallworld();
        createTurnsAndRounds();
        diceCon = new DiceController();
        new InfoController();
        new KnoppenController(this);
        timeCon = new TimerController(getGameTurn());
        redCon = new RedeployingController(this);

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
        myPlayer = players.get(myPlayerId);
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
        return currentPlayer;
    }

    private void createAttCon(){
        attCon = new AttackController(this);
    }

    public RoundController getRoundCon(){
        return roundCon;
    }

    public ShopController getShopCon(){return shopCon;}

    public TurnController getTurnCon(){
        return turnCon;
    }

    public Map2DController getMapCon(){
        return mapCon;
    }

    public VervallenController getVervCon(){return vervCon;}

    public AttackController getAttCon(){
        return attCon;
    }

    public DiceController getDiceCon() {return diceCon;}

    public GameTurn getGameTurn() { return gameTurn;}

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

    public void createGameTimer(){
        gameTimer = new GameTimer(this, 10);
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

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public TimerController getTimer(){
        return timeCon;
    }

    public String getLobbyname(){
        return lobbyName;
    }
}
