package models;

import java.util.ArrayList;
import java.util.List;

import observable.ModelViewObservable;
import observers.ModelViewObserver;

public class ApplicatieModel implements ModelViewObservable{
	private List<ModelViewObserver> observers = new ArrayList<>();
	
	@Override
	public void register(ModelViewObserver mvo) {
		observers.add(mvo);
	}

	@Override
	public void unregister(ModelViewObserver mvo) {
		observers.remove(mvo);
	}

	@Override
	public void notifyAllObservers() {
		for(ModelViewObserver mvo: observers) {
			mvo.update(this);
		}
	}

}
