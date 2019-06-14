package controlers;


import models.AreaModel;
import observers.AreaObserver;
//import Objects.RaceFiche;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import managers.SceneManager;

public class AreaController {

	private Map2DController mapController;
	private List<AreaModel> areas = new ArrayList<>();
	
	public AreaController(Map2DController mapCon) {
		mapController = mapCon;
	}
	
	public void createArea(Node area) {
		String areaId = "area_" + (areas.size() + 1);
		areas.add(new AreaModel(areaId));
		SceneManager.createAreaView(this, area, areaId);	
	}
	
//	public void printAantal() {
//		model.printAantal();
//	}

	
//	public void printCount(String id){
//		System.out.println(model.getFiches().Count());
//	}
	
//	public void addFiches(Stack<RaceFiche> fiches) {
//		model.addFiches(fiches);
//	}
	
//	public Stack<RaceFiche> deleteFiches(int count) {
//		return model.deleteFiche(count);
//	}

//	public boolean isEmpty(){
//		return model.isEmpty();
//	}
//
//	public boolean attackForceBigger(int count){
//		return model.attackForceBigger(count);
//	}

//	public int numbersNeeded(){
//		return model.numbersNeeded();
//	}
	
	public String getActiveArea() {
		for(AreaModel area : areas) {
			if(area.isActive()) {
				return area.getId();
			}
		}
		return null;
	}
	
	public void setAsNonActiveArea(String areaId) {
		getArea(areaId).setAsNonActive();
	}
	
	public void setAsActiveArea(String areaId) {
		getArea(areaId).setAsActive();
	}

	public void AreaClicked(String areaId) {
		if(getArea(areaId).isActive()) {
			setAsNonActiveArea(areaId);
		}
		else {
			setAsActiveArea(areaId);
		}
	}
	
	public AreaModel getArea(String areaId) {
		for(AreaModel area : areas) {
			if(area.getId().equals(areaId)) {
				return area;
			}
		}
		return null;
	}
	
	public void registerView(AreaObserver view, String areaId) {
		getArea(areaId).register(view);
	}
}
