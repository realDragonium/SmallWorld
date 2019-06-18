package Controller;

import Firebase.FirebaseServiceOwn;
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
	private FirebaseServiceOwn fb = Applicatie.Applicatie.getFirebaseService();

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
		model.setFiches(fiches);
		fb.mapUpdateFiches(model.getId(), fiches.size());
	}

	void setPlayerOwner(PlayerController player){
		model.player = player;
	}

	PlayerController getOwnerPlayer(){
		return model.player;
	}

	Stack<RaceFiche> removeFiches(){
		fb.mapUpdateFiches(model.getId(), 0);
		return model.getAllFiches();
	}

	void changeActive(){model.changeActive();}

	public void register(AreaObserver ao){model.register(ao);}

	public void selectActive(){	map2DCon.selectSingleArea(this);}

	public void inVerval() {
		destroyAllButOne();
	}

	public void destroyAllButOne(){model.getAllButOne();}

    public void returnAllButOne(RaceController raceController) {

		raceController.pushFiches(model.getAllButOne());
    }







}
