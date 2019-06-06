package models;

import java.util.ArrayList;
import java.util.List;

import controlers.RaceFicheControler;
import observable.AreaObservable;
import observers.AreaObserver;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Translate;
import models.FicheModel.ficheType;

public class AreaModel implements AreaObservable{
	enum Terrain { forest, swamp, sea, mountain, hill, field };
	Terrain terrain;
	enum SpecialType {CAVE, MAGIC, MINE, NONE}
	SpecialType specialType;
	Translate centerPoint;
	List<AreaObserver> observers = new ArrayList<AreaObserver>();
	boolean hovering;
	boolean selected;
	int raceFichesAmount = 0;
	private List<RaceFicheControler> fiches = new ArrayList<>();

	public List<RaceFicheControler> getFichesOnArea() {
		return fiches;
	}

	public void setCenterPoint(Translate point) {
		centerPoint = point;
	}

	public Translate getCenterPoint(){
		return centerPoint;
	}

	public void addFiche(RaceFicheControler ficheControler) {
		fiches.add(ficheControler);
		if(true) {
			raceFichesAmount++;
		}
		if(ficheControler.getMyType() == ficheType.RAS) {
			raceFichesAmount++;
		}
		notifyAllObservers();
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

	@Override
	public int getRaceFichesAmount() {
		return raceFichesAmount;
	}


}
