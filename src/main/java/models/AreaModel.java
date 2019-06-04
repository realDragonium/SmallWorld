package models;

import java.util.ArrayList;
import java.util.List;

import controlers.FicheControler;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Translate;
import observers.AreaObservable;
import observers.AreaObserver;

public class AreaModel implements AreaObservable{
	enum Terrain { forest, swamp, sea, mountain, hill, field };
	Terrain terrain;
	enum SpecialType {CAVE, MAGIC, MINE, NONE}
	SpecialType specialType;
	List<FicheControler> fiches = new ArrayList<>();
	Translate centerPoint;
	List<AreaObserver> observers = new ArrayList<AreaObserver>();
	boolean hovering;
	boolean selected;
	
	public List<FicheControler> getFichesOnArea() {
		return fiches;
	}
	
	public void configureData(Shape3D mesh) {
		String type = mesh.getId().split("_")[1];
		terrain = Terrain.valueOf(type);
		specialType = SpecialType.NONE;
	}
	

	@Override
	public void register(AreaObserver ao) {
		observers.add(ao);
		
	}

	@Override
	public void notifyAllObservers() {
		for(AreaObserver obs : observers) {
			obs.update(this);
		}
	}
	
	public void changeStateHovering() {
		hovering = !hovering;
		System.out.println(hovering);
		notifyAllObservers();
	}

	@Override
	public boolean isHovering() {
		return hovering;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}
	
	
}
