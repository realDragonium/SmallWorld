package Controller;

import Managers.SceneManager;
import Model.Map2DModel;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.*;

public class Map2DController {
	private Map<String, AreaController> areas = new HashMap<>();
	private GameController gameCon;
	private Map2DModel model = new Map2DModel();

	public Map2DController(GameController gameCon){
		this.gameCon = gameCon;
		SceneManager.getInstance().createMap(this);
	}

	public void createArea(Group area){
		areas.put(area.getChildren().get(0).getId(), new AreaController( area, this, gameCon));
	}

	List<AreaController> getActiveAreas(){
		return model.ActiveAreas;
	}

	void selectSingleArea(AreaController areaCons){
		deleteAllActives();
		addActive(areaCons);
	}

	void selectMultipleAreas(List<AreaController> areaCons){
		deleteAllActives();
		for(AreaController areaCon: areaCons){
			addActive(areaCon);
		}
	}

	void deleteAllActives(){
		List<AreaController> oldActives = model.ActiveAreas;
		for(AreaController areaCon: oldActives){
			deleteActive(areaCon);
		}
	}

	void deleteActive(AreaController areaCon){
		model.ActiveAreas.remove(areaCon);
		areaCon.changeActive();
	}

	void addActive(AreaController areaCon){
		model.ActiveAreas.add(areaCon);
		areaCon.changeActive();
	}
}
