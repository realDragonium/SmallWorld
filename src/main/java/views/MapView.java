package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlers.AnimationControler;
import controlers.AnimationsControler;
import controlers.MapController;
import Observable.DepthObservable;
import Observer.DepthObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Translate;

public class MapView implements DepthObservable{
	private DepthObserver depthObserver;
	private Group graphic;
	
	private MapController mapCon;

	AnimationControler CoinFalling;
	Map<String, Translate> AreaPoints;

	List<Translate> centerPoints = new ArrayList<Translate>();
	AnimationsControler animControler = new AnimationsControler();
	
	public MapView(DepthObserver depthO) {
		depthObserver = depthO;
		mapCon = new MapController();
		loadfxmlMap();
	}

	private void loadfxmlMap() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/map2.fxml"));
        try {
			graphic = fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        createAreas();
        notifyObserver();
	}
	
	private void createAreas() {
		AreaPoints = new HashMap<String, Translate>();
		for (Node mesh : graphic.getChildren()) {

			mesh.setId(mesh.getId().substring(14, mesh.getId().length()));
			if(mesh.getId().length() > 6) {

				AreaView areaView = new AreaView(((Shape3D) mesh), AreaPoints.get(mesh.getId()));
				areaView.controler.setAnimationsControler(animControler);
			}
		}
	}
	
	@Override
	public void notifyObserver() {
		depthObserver.update(this);
	}

	@Override
	public void register(DepthObserver depthO) {
		depthObserver = depthO;
	}

	@Override
	public Group getGroup() {
		return graphic;
	}

	
}
