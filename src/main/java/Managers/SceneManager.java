package Managers;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

import Controller.*;
import View.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import Applicatie.Applicatie;

public class SceneManager {
    private Map<Class, Callable<?>> creators = new HashMap();
    private Map<String, Group> groepen = new HashMap();
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

    public void changeToScene(Group group) {
        Scene scene = new Scene(group);
        this.app.changeScene(scene);
    }

    public void registerApp(Applicatie newApp) {
        this.app = newApp;
    }

    public void createLoginView(LoginController loginController) {
        Group localGroup = new Group();
        this.creators.put(LoginView.class, () -> {
            return new LoginView(loginController, localGroup);
        });
        this.FXMLLOADER("/LoginScreen/Loginscherm.fxml");
        this.changeToScene(localGroup);
    }

    public void createHomeScreenView(HomeScreenController hsController) {
        Group localGroup = new Group();
        this.creators.put(HomeScreenView.class, () -> {
            return new HomeScreenView(hsController, localGroup);
        });
        this.FXMLLOADER("/HomeScreen/Homescreen.fxml");
        this.changeToScene(localGroup);
    }

    public void createGameView(GameController gameCon) {
        this.gameView = new Group();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/GameMain.fxml"));

        try {
            Pane pane = (Pane)fxmlLoader.load();
            Iterator var4 = pane.getChildren().iterator();

            while(var4.hasNext()) {
                Node groep = (Node)var4.next();
                this.groepen.put(groep.getId(), (Group)groep);
            }

            this.gameView.getChildren().add(pane);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        this.changeToScene(this.gameView);
    }

    public void createAreaView(AreaController areaController, Group area) {
        new AreaView(area, areaController);
    }

    public void createMap(Map2DController mapController) {
        Group localGroup = new Group();
        new Map2DView(mapController, localGroup);
        this.gameView.getChildren().add(localGroup);
    }

    public void makeMap() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/GameMain.fxml"));

        try {
            Pane pane = (Pane)fxmlLoader.load();
            Iterator var3 = pane.getChildren().iterator();

            while(var3.hasNext()) {
                Node groep = (Node)var3.next();
                this.groepen.put(groep.getId(), (Group)groep);
            }

            this.gameView.getChildren().add(pane);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public void loadButtons(KnoppenController knopCon) {
        this.creators.put(KnoppenView.class, () -> {
            return new KnoppenView((Group)this.groepen.get("buttonGroup"), knopCon);
        });
        this.FXMLLOADER("/Buttons.fxml");
    }

    public void loadPlayer(String playerID, PlayerController playerCon) {
        this.creators.put(PlayerView.class, () -> {
            return new PlayerView(playerID, (Group)this.groepen.get("playerGroup"), playerCon);
        });
        this.FXMLLOADER("/PlayerView.fxml");
    }

    public void loadShop(ShopController shopCon) {
        this.creators.put(ShopView.class, () -> {
            return new ShopView((Group)this.groepen.get("shopGroup"), shopCon);
        });
        this.FXMLLOADER("/ShopView.fxml");
    }

    public void loadRound(RoundController roundCon) {
        this.creators.put(RoundView.class, () -> {
            return new RoundView((Group)this.groepen.get("roundGroup"), roundCon);
        });
        this.FXMLLOADER("/RoundView.fxml");
    }

    public void loadTurn(TurnController turnCon) {
        this.creators.put(TurnView.class, () -> {
            return new TurnView((Group)this.groepen.get("turnGroup"), turnCon);
        });
        this.FXMLLOADER("/TurnView.fxml");
    }

    private void FXMLLOADER(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource(path));
        fxmlLoader.setControllerFactory((param) -> {
            Callable callable = (Callable)this.creators.get(param);

            try {
                return callable.call();
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        });

        try {
            fxmlLoader.load();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }
}