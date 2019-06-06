package views;

import java.io.IOException;

import controlers.MapController;
import Observable.DepthObservable;
import Observer.DepthObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Shape3D;

public class MapView implements DepthObservable{
	private DepthObserver depthObserver;
	private Group graphic;
	
	private MapController mapCon;
	
	
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
		for (Node mesh : graphic.getChildren()) {
        	mesh.setId(mesh.getId().substring(14, mesh.getId().length()));
        	if(mesh.getId().length() > 6) {
        		new views.AreaView(((Shape3D) mesh));
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
