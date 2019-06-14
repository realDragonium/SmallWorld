package models;

import java.util.ArrayList;
import java.util.List;

import controlers.AreaController;
import observable.MapObservable;
import observers.MapObserver;

public class Map2DModel implements MapObservable{
	List<MapObserver> observers = new ArrayList<>();
	

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
}
