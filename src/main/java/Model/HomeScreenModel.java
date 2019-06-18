package Model;

import java.util.ArrayList;
import java.util.List;
import Observable.HomeScreenObservable;
import Observer.HomeScreenObserver;

public class HomeScreenModel implements HomeScreenObservable {
	private List<HomeScreenObserver> observers = new ArrayList<>();
	
	@Override
	public void notifyAllObservers() {
		for(HomeScreenObserver mvo :observers) {
			mvo.update(this);
		}
	}

	@Override
	public void register(HomeScreenObserver mvo) {
		observers.add(mvo);
		
	}

	@Override
	public void unregister(HomeScreenObserver mvo) {
		observers.remove(mvo);
	}

}
