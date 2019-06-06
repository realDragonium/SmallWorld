package views;

import controlers.GameController;
import observable.GameObservable;
import observable.ModelViewObservable;
import observable.SceneObservable;
import observers.GameObserver;
import observers.ModelViewObserver;
import observers.SceneObserver;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

public class GameView implements ModelViewObserver, SceneObservable, GameObserver{

	private SceneObserver sceneObserver;
	
	private GameController gameCon;
	private Scene scene;
	private Group world;
	
	public GameView(SceneObserver so) {
		registerSO(so);
		world = new Group();
		gameCon = new GameController();
		gameCon.register(this);
		start3D();
	}

	private void start3D() {
		System.out.println("loadSceneGameView");
		new DepthView(this);
	}
	
	private void groupToScene(Node subScene) {
		world.getChildren().add(subScene);
		scene = new Scene(world, 1600, 800);
		notifyObserverSO();
	}
	
	@Override
	public void registerSO(SceneObserver so) {
		sceneObserver = so;
	}

	@Override
	public void unregisterSO(SceneObserver so) {
		sceneObserver = so;
	}

	@Override
	public void notifyObserverSO() {
		sceneObserver.update(this);
	}

	@Override
	public Scene getSceneSO() {
		return scene;
	}

	@Override
	public void update(ModelViewObservable mvo) {
		System.out.println("mvo test message");
	}

	@Override
	public void update(GameObservable go) {
		groupToScene(go.getSubScene());		
	}
}
