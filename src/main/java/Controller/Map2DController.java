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
		List<AreaController> areaCons = new ArrayList<>();
		for(String id: model.ActiveAreas){
			areaCons.add(areas.get(id));
		}
		return areaCons;
	}

	void selectActive(List<AreaController> areaCons){
		List<String> oldList = model.ActiveAreas;
		model.ActiveAreas = new ArrayList<>();
		if(oldList.size() > 0) {
			if (areas.get(oldList.get(0)).getId().equals(areaCons.get(0).getId())){
				return;
			}
			else areas.get(oldList.get(0)).changeActive();
		}
		areaCons.forEach(i -> model.ActiveAreas.add(i.getId()));
	}
}
