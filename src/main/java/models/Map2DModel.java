package models;

import java.util.ArrayList;
import java.util.List;

import controlers.AreaController;
import observable.MapObservable;
import observers.MapObserver;

public class Map2DModel implements MapObservable{
	List<AreaController> areas = new ArrayList<>();
	List<MapObserver> observers = new ArrayList<>();
	AreaController activeArea;
	
	public void addArea(AreaController area) {
		areas.add(area);
	}
	
	public List<AreaController> getAreas() {
		return areas;
	}

	public void changeActiveArea(AreaController areaController) {
		activeArea = areaController;
		notifyAllObservers();
	}
	
	public void removeActiveArea() {
		activeArea = null;
		notifyAllObservers();
	}

	@Override
	public void register(MapObserver mvo) {
		observers.add(mvo);
		
	}

	@Override
	public void unregister(MapObserver mvo) {
		observers.remove(mvo);
		
	}

	@Override
	public void notifyAllObservers() {
		for(MapObserver obs : observers) {
			obs.update(this);
		}
	}

	@Override
	public AreaController getActiveArea() {
		return activeArea;
	}
}
