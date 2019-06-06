package models;

import java.util.ArrayList;
import java.util.List;

import observable.ModelViewObservable;
import observers.ModelViewObserver;

public class HomeScreenModel implements ModelViewObservable {
	private List<ModelViewObserver> observers = new ArrayList<>();
	
	@Override
	public void notifyAllObservers() {
		for(ModelViewObserver mvo :observers) {
			mvo.update(this);
		}
	}

	@Override
	public void register(ModelViewObserver mvo) {
		observers.add(mvo);
		
	}

	@Override
	public void unregister(ModelViewObserver mvo) {
		observers.remove(mvo);
	}

}
