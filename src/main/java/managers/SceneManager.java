package managers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Shape;

import controlers.Map2DController;
import controlers.AreaController;
import controlers.DiceController;
import controlers.GameScreenController;
import controlers.HomeScreenController;
import controlers.LoginController;
import controlers.MapController;
import controlers.PlayerListController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.Applicatie;
import views.AreaView;
import views.DiceView;
import views.GameScreenView;
import views.HomeScreenView;
import views.LoginView;
import views.Map2DView;
import views.MapView;
import views.PlayerListView;

public final class SceneManager {
	
	static GameScreenView gameScreen;
	
	enum pane{LOGIN(1600, 800), HOMESCREEN(1600, 800), TITLESCREEN(1600, 800), GAMESCREEN(1600, 800), MAP(1200, 600, 200, 100), PLAYERLIST(200, 600, 0, 100), DICE(200, 100, 1400, 0);
		Pane myPane = new Pane();
		pane(int width, int height){
			myPane.setMinSize(width, height);
		}
		
		pane(int width, int height, int xLocation, int yLocation){
			myPane.setMinSize(width, height);
			myPane.setTranslateX(xLocation);
			myPane.setTranslateY(yLocation);
		}
	};
	
	static List<pane> panes = new ArrayList<>();
	
	static Applicatie app;
	
	public static void registerApp(Applicatie newApp) {
		app = newApp;
	}
	
	public static void createLoginView(LoginController loginController) {
		LoginView login = new LoginView(loginController);
		pane loginPane = pane.LOGIN;
		login.setPane(loginPane.myPane);
		panes.add(loginPane);
		changeToScene(loginPane);
	}
	
	public static void createDiceView(DiceController diceController) {
		DiceView dice = new DiceView(diceController);
		pane dicePane = pane.DICE;
		dice.setPane(dicePane.myPane);
		panes.add(dicePane);
		gameScreen.addPane(dicePane.myPane);
	}
	
	public static void createHomeScreenView(HomeScreenController hsController) {
		HomeScreenView homeScreen = new HomeScreenView(hsController);
		pane homeScreenPane = pane.HOMESCREEN;
		homeScreen.setPane(homeScreenPane.myPane);
		panes.add(homeScreenPane);
		changeToScene(homeScreenPane);
	}
	
	public static void createGameScreenView(GameScreenController gsController) {
		gameScreen = new GameScreenView(gsController);
		pane gameScreenPane = pane.GAMESCREEN;
		gameScreen.setPane(gameScreenPane.myPane);
		panes.add(gameScreenPane);
		changeToScene(gameScreenPane);
	}
	
	public static void createMapView(MapController mapController) {
		MapView map = new MapView(mapController);
		pane mapPane = pane.MAP;
		map.setPane(mapPane.myPane);
		panes.add(mapPane);
		gameScreen.addPane(mapPane.myPane);
	}
	
	public static void createAreaView(AreaController areaController, Node area, String areaId) {
		AreaView areaView = new AreaView(areaController, area, areaId);
	}
	
	public static void createRasView() {
		
	}
	
	public static void createPowerView() {
		
	}
	
	public static void createInfoView() {
		
	}
	
	public static void createFicheView(){
		
	}
	
	public static void createPlayerListView(PlayerListController plController) {
		PlayerListView playerList = new PlayerListView(plController);
		pane playerListPane = pane.PLAYERLIST;
		playerList.setPane(playerListPane.myPane);
		panes.add(playerListPane);
		gameScreen.addPane(playerListPane.myPane);
	}
	
	public static void createRaceCombination() {
		
	}
//	
//	public static void createTitleScreenView(TitelScreenController tsController) {
//		LoginView login = new LoginView(loginController);
//		pane loginPane = pane.LOGIN;
//		login.setPane(loginPane.myPane);
//		panes.add(loginPane);
//	}
	
	public static void changeToScene(pane newPane) {
		Scene scene = new Scene(newPane.myPane);
		app.changeScene(scene);
	}

	public static void createMap(Map2DController mapController) {
		Map2DView map = new Map2DView(mapController);
		pane mapPane = pane.MAP;
		map.setPane(mapPane.myPane);
		panes.add(mapPane);
		gameScreen.addPane(mapPane.myPane);
	}
	
}
