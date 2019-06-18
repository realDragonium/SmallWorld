package Managers;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

import Controller.*;
import View.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import Applicatie.Applicatie;

public class SceneManager {
    private Map<Class, Callable<?>> creators = new HashMap();
    private Map<String, Group> groepen = new HashMap();
    private List<Group> shopItems = new ArrayList<>();
    private static SceneManager sceneManager;
    private Applicatie app;
    private Group gameView;

    public SceneManager() {
    }

    public static SceneManager getInstance() {
        if (sceneManager == null) {
            sceneManager = new SceneManager();
        }

        return sceneManager;
    }

    public void changeToScene(Parent group) {
        Scene scene = new Scene(group);
        app.changeScene(scene);
    }

    public void registerApp(Applicatie newApp) {
        this.app = newApp;
    }

    public void switchToPreperationPhase() {
        Pane pane = new Pane();
        pane.getChildren().add(groepen.get("mapGroup"));
        pane.getChildren().add(groepen.get("shopGroup"));
        pane.getChildren().add(groepen.get("playerGroup"));
        pane.getChildren().add(groepen.get("timerGroup"));
        pane.getChildren().add(groepen.get("turnGroup"));
        pane.getChildren().add(groepen.get("roundGroup"));
        pane.getChildren().add(groepen.get("vervalGroup"));
        changeToScene(pane);
    }

    public void switchToAttackPhase() {
        Pane pane = new Pane();
        pane.getChildren().add(groepen.get("mapGroup"));
        pane.getChildren().add(groepen.get("playerGroup"));
        pane.getChildren().add(groepen.get("timerGroup"));
        pane.getChildren().add(groepen.get("buttonGroup"));
        pane.getChildren().add(groepen.get("turnGroup"));
        pane.getChildren().add(groepen.get("roundGroup"));
        pane.getChildren().add(groepen.get("diceGroup"));
        changeToScene(pane);
    }

    public void switchToEndingPhase() {
        Pane pane = new Pane();
        pane.getChildren().add(groepen.get("mapGroup"));
        pane.getChildren().add(groepen.get("playerGroup"));
        pane.getChildren().add(groepen.get("timerGroup"));
        pane.getChildren().add(groepen.get("turnGroup"));
        pane.getChildren().add(groepen.get("roundGroup"));
        changeToScene(pane);
    }

    public void LeaderboardView(LeaderboardController con) {
        Group localGroup = new Group();
        creators.put(LeaderboardView.class, (Callable<LeaderboardView>) () -> new LeaderboardView(localGroup, con));
        FXMLLOADER("/Leaderboard/Leaderboard.fxml");
        changeToScene(localGroup);
    }

    public void createLoginView(LoginController loginController) {
        Group localGroup = new Group();
        creators.put(LoginView.class, (Callable<LoginView>) () -> new LoginView(loginController, localGroup));
        FXMLLOADER("/LoginScreen/Loginscherm.fxml");
        changeToScene(localGroup);
    }

    public void createHomeScreenView(HomeScreenController hsController) {
        Group localGroup = new Group();
        creators.put(HomeScreenView.class, (Callable<HomeScreenView>) () -> new HomeScreenView(hsController, localGroup));
        FXMLLOADER("/HomeScreen/Homescreen.fxml");
        changeToScene(localGroup);
    }

    public void createGameView(GameController gameCon) {

        gameView = new Group();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/GameMain.fxml"));
        try {
            Pane pane = fxmlLoader.load();
            for (Node groep : pane.getChildren()) {
                groepen.put(groep.getId(), (Group) groep);
            }
            gameView.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeToScene(gameView);
    }

    public void createAreaView(AreaController areaController, Group area) {
        new AreaView(area, areaController);
    }

    public void createMap(Map2DController mapController) {
        creators.put(Map2DView.class, (Callable<Map2DView>) () -> new Map2DView(mapController, groepen.get("mapGroup")));
        FXMLLOADER("/UglyMap.fxml");
    }

    public void makeMap() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/GameMain.fxml"));
        try {
            Pane pane = fxmlLoader.load();
            for (Node groep : pane.getChildren()) {
                groepen.put(groep.getId(), (Group) groep);
            }
            gameView.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createLobbyView(LobbyController con) {
        Group localGroup = new Group();
        creators.put(LobbyView.class, (Callable<LobbyView>) () -> new LobbyView(localGroup, con));
        FXMLLOADER("/LobbyScreen/LobbyScreen.fxml");
        changeToScene(localGroup);
    }

    public void loadTimer(TimerController con) {
        creators.put(TimerView.class, (Callable<TimerView>) () -> new TimerView(groepen.get("timerGroup"), con));
        FXMLLOADER("/TimerView.fxml");
    }

    public void loadCombination(CombinationController con) {
        creators.put(CombinationView.class, () -> {
            for (Group item : shopItems) {
                if (item.getChildren().size() == 1) {
                    return new CombinationView(item, con);
                }
            }
            return null;
        });
        FXMLLOADER("/CombinationView.fxml");
    }

    public void loadVerval(VervallenController con) {
        creators.put(VervallenView.class, (Callable<VervallenView>) () -> new VervallenView(con, groepen.get("vervalGroup")));
        FXMLLOADER("/VervallenView.fxml");
    }

    public void createLobbySettingsView(LobbySettingsController con) {
        Group localGroup = new Group();
        creators.put(LobbySettingsView.class, (Callable<LobbySettingsView>) () -> new LobbySettingsView(localGroup, con));
        FXMLLOADER("/LobbyScreen/CreateLobbySettings.fxml");
        changeToScene(localGroup);
    }


    public void createInLobbyView(InLobbyController con) {
        Group localGroup = new Group();
        creators.put(InLobbyView.class, (Callable<InLobbyView>) () -> new InLobbyView(localGroup, con));
        FXMLLOADER("/LobbyScreen/InLobbyScreen.fxml");
        changeToScene(localGroup);
    }

    public void loadButtons(KnoppenController knopCon) {
        creators.put(KnoppenView.class, (Callable<KnoppenView>) () -> new KnoppenView(groepen.get("buttonGroup"), knopCon));
        FXMLLOADER("/Buttons.fxml");
    }

    public void loadPlayer(String playerID, PlayerController playerCon) {
        creators.put(PlayerView.class, (Callable<PlayerView>) () -> new PlayerView(playerID, groepen.get("playerGroup"), playerCon));
        FXMLLOADER("/PlayerView.fxml");
    }

    public void loadShop(ShopController shopCon) {
        creators.put(ShopView.class, (Callable<ShopView>) () -> new ShopView(groepen.get("shopGroup"), shopCon));
        FXMLLOADER("/ShopView.fxml");
    }

    public void loadRound(RoundController roundCon) {
        creators.put(RoundView.class, (Callable<RoundView>) () -> new RoundView(groepen.get("roundGroup"), roundCon));
        FXMLLOADER("/RoundView.fxml");
    }

    public void loadTurn(TurnController turnCon) {
        creators.put(TurnView.class, (Callable<TurnView>) () -> new TurnView(groepen.get("turnGroup"), turnCon));
        FXMLLOADER("/TurnView.fxml");
    }

    public void loadDice(DiceController diceCon) {
        new DiceView(diceCon, groepen.get("diceGroup"));
    }

    private void FXMLLOADER(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        fxmlLoader.setControllerFactory(param -> {
            Callable<?> callable = creators.get(param);
            if (callable == null) {
                try {
                    // default handling: use no-arg constructor
                    return param.newInstance();
                } catch (InstantiationException | IllegalAccessException ex) {
                    throw new IllegalStateException(ex);
                }
            } else {
                try {
                    return callable.call();
                } catch (Exception ex) {
                    throw new IllegalStateException(ex);
                }
            }
        });

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
