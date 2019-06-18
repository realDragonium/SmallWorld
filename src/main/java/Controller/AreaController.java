package Controller;

import Firebase.FirebaseControllerObserver;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.AreaModel;
import Objects.RaceFiche;
import Observer.AreaObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.Group;
import Applicatie.Applicatie;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class AreaController implements FirebaseControllerObserver {

	private Map2DController map2DCon;
	private AreaModel model;
	private GameController gameCon;
	private FirebaseServiceOwn fb = SceneManager.getInstance().getApp().getFirebaseService();

	public AreaController(Group area, Map2DController mapCon, GameController gameCon) {
		model = new AreaModel(area.getChildren().get(0).getId());
		map2DCon = mapCon;
		this.gameCon = gameCon;
		SceneManager.getInstance().createAreaView(this, area);
		setAreaInFirebase();
		fb.AreaListener(model.getId(), this);
	}

	String getId(){return model.getId();}

	public void setAreaInFirebase(){
		Map<String, Object> area = new HashMap<>();
		area.put("fiches", model.getNumberOfFiches());
		area.put("owner", null);
		area.put("oldOwner", null);
		area.put("oldFiches", null);
		fb.setAreas(model.getId(), area);
	}

	int numbersNeeded(){
		return model.numbersNeeded();
	}

	void attackArea(Stack<RaceFiche> fiches){
		model.setFiches(fiches);
		fb.areaUpdateFiches(model.getId(), model.getNumberOfFiches());
	}

	void setPlayerOwner(PlayerController player){
		model.player = player;
	}

	PlayerController getOwnerPlayer(){
		return model.player;
	}

	Stack<RaceFiche> removeFiches(){
		fb.areaUpdateFiches(model.getId(), 0);
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

	@Override
	public void update(DocumentSnapshot ds) {
		if(ds.get("oldowner") == null) return;

		AttackController attCon = gameCon.getAttCon();
		attCon.getTargetArea();
		attCon.attackAreaLocal();
	}
}
