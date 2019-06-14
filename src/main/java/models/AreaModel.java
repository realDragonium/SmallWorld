package models;

import controlers.RaceFicheController;
import observable.AreaObservable;
import observers.AreaObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class AreaModel implements AreaObservable{

	private Stack<RaceFicheController> raceFiches = new Stack<>();
	private List<AreaObserver> observers = new ArrayList<>();
	private boolean active = false;
	private String areaId;
	
	public AreaModel(String areaId) {
		this.areaId = areaId;
		//IntStream.range(0, (int) (Math.random()*3)).forEach(i -> raceFiches.push(new RaceFicheController("Lost village")));
	}
	
	public void printAantal() {
		System.out.println("Aanwezig aantal fiches: " + raceFiches.size());
	}
	
	public void setAsActive() {
		active = true;
		notifyAllObservers();
	}
	
	public void setAsNonActive() {
		active = false;
		notifyAllObservers();
	}

//	public void addFiches(Stack<RaceFiche> fiches) {
//		while(fiches.size() > 0){
//			raceFiches.push(fiches.pop());
//		}
//	}
	
//	public Stack<RaceFiche> deleteFiche(int count) {
//		Stack<RaceFiche> leaveFiches = new Stack<>();
//		IntStream.range(0, count).forEach(i -> leaveFiches.push(raceFiches.pop()));
//		return leaveFiches;
//	}

	public boolean isEmpty(){
		return raceFiches.isEmpty();
	}

	public boolean attackForceBigger(int count){
		return count > raceFiches.size();
	}

	public int numbersNeeded(){
		return raceFiches.size() + 1;
	}

	public Object getFiches() {
		return null;
	}

	@Override
	public void register(AreaObserver mvo) {
		observers.add(mvo);
	}

	@Override
	public void unregister(AreaObserver mvo) {
		observers.remove(mvo);
		
	}

	@Override
	public void notifyAllObservers() {
		for(AreaObserver obs : observers) {
			obs.update(this);
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	public String getId() {
		return areaId;
	}
}
