package Controller;


import Managers.SceneManager;
import Model.AreaModel;
import Objects.RaceFiche;
import Observer.AreaObserver;
import javafx.scene.Group;

import java.util.Collections;
import java.util.Stack;

public class AreaController {

	private Map2DController map2DCon;
	private AreaModel model;
	private GameController gameCon;
	
	public AreaController(Group area, Map2DController mapCon, GameController gameCon) {
		model = new AreaModel(area.getChildren().get(0).getId());
		map2DCon = mapCon;
		this.gameCon = gameCon;
		SceneManager.getInstance().createAreaView(this, area);
	}

	String getId(){return model.getId();}

	int numbersNeeded(){
		return model.numbersNeeded();
	}

	void attackArea(Stack<RaceFiche> fiches){
		model.attackArea(fiches);
	}

	void setPlayerOwner(PlayerController player){
		model.playerId = player.getId();
	}

	PlayerController getPlayer(){
		return gameCon.getPlayer(model.playerId);
	}

	Stack<RaceFiche> removeFiches(){
		return model.getAllFiches();
	}

	void changeActive(){model.changeActive();}

	public void register(AreaObserver ao){model.register(ao);}

	public void selectActive(){	map2DCon.selectSingleArea(this);}
}
