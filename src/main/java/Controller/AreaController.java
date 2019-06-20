package Controller;

import Firebase.FirebaseControllerObserver;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.AreaModel;
import Objects.RaceFiche;
import Observer.AreaObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;
import javafx.scene.Group;
import Enum.AreaProperty;
import Enum.AreaType;

import java.util.Arrays;
import java.util.List;
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

	RaceFiche getOneFiche(){
		return model.getOneFiche();
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
		System.out.println(ds.getData());
		Platform.runLater(()-> {
			if(model.getPlayer() == gameCon.getMyPlayer()){
				model.getPlayer().getActiveCombination().getRace().pushFiches(removeFiches());
				model.player = null;
			} else{
				model.setFiches((int)Math.round(ds.getDouble("fiches")));
			}


			model.setAreaType(ds.getString("type"));
			model.setBorderArea(ds.getBoolean("borderArea"));
			model.setNeighbours((List<String>) ds.get("neighbours"));
			model.setAttackAble(ds.getBoolean("attackAble"));
			model.notifyObserver();
		});
	}

	public AreaProperty getSpecialProp() {
		return model.getSpecialProp();
	}


	public boolean isNextToWater() {
		return model.isNextToWater();
	}

	public int getFichesAmount() {
		return model.getNumberOfFiches();
	}

	public void addFiche(RaceFiche fiche) {
		model.addFiche(fiche);
	}

	public AreaType getAreaType() {
		return model.getAreaType();
	}
}
