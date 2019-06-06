package views;

import java.util.ArrayList;
import java.util.List;

import controlers.HomeScreenController;
import Observable.ModelViewObservable;
import Observable.SceneObservable;
import Observer.ModelViewObserver;
import Observer.SceneObserver;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HomeScreenView implements ModelViewObserver, SceneObservable {
	private List<SceneObserver> sceneObserver = new ArrayList<>();
	
	
	Scene scene;
    HomeScreenController hsCon;

    public HomeScreenView(SceneObserver so){
        registerSO(so);
        hsCon = new HomeScreenController();
        hsCon.register(this);
        loadScene();
    }
    
    private void loadScene(){
        GridPane gPane = new GridPane();
        Button button = new Button("StartGame!");
        button.setOnAction(e->{
        	startGame();
        });
        gPane.add(button, 0, 0);
        scene = new Scene(gPane, 400, 400);
        notifyObserverSO();
    }

    private void startGame() {
    	new GameView(sceneObserver.get(0));
    }
    
	@Override
	public void registerSO(SceneObserver so) {
		sceneObserver.add(so);
	}

	@Override
	public void unregisterSO(SceneObserver so) {
		sceneObserver.remove(so);
	}

	@Override
	public void notifyObserverSO() {
		for(SceneObserver so: sceneObserver) {
			so.update(this);
		}
	}

	@Override
	public Scene getSceneSO() {
		return scene;
	}

	@Override
	public void update(ModelViewObservable mvo) {
		System.out.println("mvo test message");
	}
    


    
    
	
}
