package models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.transform.Translate;
import moleculesampleapp.Xform;
import observable.FicheObservable;
import observers.FicheObserver;

public class FicheModel implements FicheObservable{
	
	int defenseValue;
	int attackValue;
	public enum ficheType {RAS, SPECIAL, SPECIALPOWER};
	ficheType type;
	Translate position;
	Xform fiche3dModel;
	List<FicheObserver> observers = new ArrayList<FicheObserver>();
	
	public FicheModel(Xform xform) {
		fiche3dModel = xform;
	}
	
	public int whatsMyAttackValue() {
		return attackValue;
	}
	
	public int whatsMyDeffenceValue() {
		return defenseValue;
	}
	
	public ficheType whatsMyType() {
		return type;
	}
	
	public void setPosition(Translate pos) {
		position = pos;
		notifyAllObservers();
	}
	
	public Xform getXform() {
		return fiche3dModel;
	}

	@Override
	public void register(FicheObserver fo) {
		observers.add(fo);
	}

	@Override
	public void notifyAllObservers() {
		for(FicheObserver obs : observers) {
			obs.update(this);
		}
	}

	@Override
	public Translate getPosition() {
		return position;
	}
	

}
