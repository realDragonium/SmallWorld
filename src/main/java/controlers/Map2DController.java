package controlers;

import views.Map2DView;
import javafx.scene.Node;
import javafx.stage.Stage;
import managers.SceneManager;
import models.Map2DModel;

import java.util.HashMap;
import java.util.Map;


public class Map2DController {
	private Map2DModel model = new Map2DModel();
	private AreaController areaController = new AreaController(this);

	Map2DController(){
		SceneManager.createMap(this);
	}
	
	public void setActiveArea(AreaController areaController) {
		model.changeActiveArea(areaController);
	}
	
	public void removeActiveArea() {
		model.removeActiveArea();
	}

	public void createNewArea(Node area){
		System.out.println(area.getId());
		areaController.createArea(area);
	}


//	public void attackCountry(String id){
//		AttackController attCon =  gameCon.getAttCon();
//		attCon.getTargetArea(id);
//		attCon.attackArea();
//	}
}
