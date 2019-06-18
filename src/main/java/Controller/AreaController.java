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

import java.util.Stack;

public class AreaController implements FirebaseControllerObserver {

	private Map2DController map2DCon;
	private AreaModel model;
	private GameController gameCon;
	private FirebaseServiceOwn fb = Applicatie.getFirebaseService();
	
	public AreaController(Group area, Map2DController mapCon, GameController gameCon) {
		model = new AreaModel(area.getChildren().get(0).getId());
		map2DCon = mapCon;
		this.gameCon = gameCon;
		SceneManager.getInstance().createAreaView(this, area);
		fb.setAreas(model.getId(), model.getNumberOfFiches());
		fb.AreaListener(model.getId(), this);
	}

	String getId(){return model.getId();}

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

    public void returnAllButOne(RaceController raceController) {
		raceController.pushFiches(model.getAllButOne());
    }

	@Override
	public void update(DocumentSnapshot ds) {
		if(ds.getDouble("fiches") == null) return;
		model.fichesCount = (int) Math.round(ds.getDouble("fiches"));
		model.notifyObserver();
	}
}
