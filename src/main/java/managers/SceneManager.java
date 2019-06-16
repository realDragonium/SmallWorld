package Managers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import Controller.*;
import View.*;
import Enum.EnumPane;
//import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import Applicatie.Applicatie;
//import View.DiceView;

public class SceneManager {
    private Map<Class, Callable<?>> creators = new HashMap<>();
    private Map<String, Group> groepen = new HashMap<>();
    private GameView gameView;
    private static SceneManager sceneManager;
    private List<EnumPane> panes = new ArrayList<>();
    private Applicatie app;

    public static SceneManager getInstance(){
        if(sceneManager == null) sceneManager = new SceneManager();
        return sceneManager;
    }

    public void changeToScene(EnumPane newPane) {
        Scene scene = new Scene(newPane.myPane);
        app.changeScene(scene);
    }

    public void registerApp(Applicatie newApp) {
        app = newApp;
    }

    public void createLoginView(LoginController loginController) {
        LoginView login = new LoginView(loginController);
        EnumPane loginPane = EnumPane.LOGIN;
        login.setPane(loginPane.myPane);
        panes.add(loginPane);
        changeToScene(loginPane);
    }

    public void createHomeScreenView(HomeScreenController hsController) {
        HomeScreenView homeScreen = new HomeScreenView(hsController);
        EnumPane homeScreenPane = EnumPane.HOMESCREEN;
        homeScreen.setPane(homeScreenPane.myPane);
        panes.add(homeScreenPane);
        changeToScene(homeScreenPane);
    }
    public void createGameView(GameController gameCon) {
        gameView = new GameView(gameCon);
        EnumPane gamePane = EnumPane.GAMESCREEN;
        gameView.setPane(gamePane.myPane);
        panes.add(gamePane);
        changeToScene(gamePane);
    }

    public void createAreaView(AreaController areaController, Group area) {
        new AreaView(area, areaController);
    }

    public void createMap(Map2DController mapController) {
        Map2DView map = new Map2DView(mapController);
        EnumPane mapPane = EnumPane.MAP;
        map.setPane(mapPane.myPane);
        panes.add(mapPane);
        gameView.addPane(mapPane.myPane);
    }

    public void makeMap() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/GameMain.fxml"));
        try {
            Pane pane = fxmlLoader.load();
            for (Node groep : pane.getChildren()) {
                groepen.put(groep.getId(), (Group) groep);
            }
            gameView.addPane(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadButtons(KnoppenController knopCon) {
        creators.put(KnoppenView.class, (Callable<KnoppenView>) () -> new KnoppenView(groepen.get("buttonGroup"), knopCon));
        FXMLLOADER("/Buttons.fxml");
    }

    public void loadPlayer(String playerID, PlayerController playerCon) {
        creators.put(PlayerView.class, (Callable<PlayerView>) () -> new PlayerView(playerID, groepen.get("playerGroup"), playerCon));
        FXMLLOADER("/PlayerView.fxml");
    }

    public void loadRound(RoundController roundCon) {
        creators.put(RoundView.class, (Callable<RoundView>) () -> new RoundView(groepen.get("roundGroup"), roundCon));
        FXMLLOADER("/RoundView.fxml");
    }

    public void loadTurn(TurnController turnCon) {
        creators.put(TurnView.class, (Callable<TurnView>) () -> new TurnView(groepen.get("turnGroup"), turnCon));
        FXMLLOADER("/TurnView.fxml");
    }

    private void FXMLLOADER(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        fxmlLoader.setControllerFactory(param -> {
            Callable<?> callable = creators.get(param);
            try {
                return callable.call();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        });
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}