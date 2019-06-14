package models;

import java.util.ArrayList;
import java.util.List;

import observable.PlayerObservable;
import observers.PlayerObserver;

public class PlayerModel implements PlayerObservable{

	String playerId;
	int punten = 0;
	List<PlayerObserver> observers = new ArrayList<>();
	
	public PlayerModel(String playerId) {
		this.playerId = playerId;
	}
	
	public void addPoints(int amount) {
		punten += amount;
	}
	
	public void removePoints(int amount) {
		punten -= amount;
	}

	@Override
	public void register(PlayerObserver mvo) {
		observers.add(mvo);
		
	}

	@Override
	public void unregister(PlayerObserver mvo) {
		observers.remove(mvo);
		
	}

	@Override
	public void notifyAllObservers() {
		for(PlayerObserver obs : observers) {
			obs.Update(this);
		}
		
	}

	@Override
	public int getPuntenAmount() {
		return punten;
	}

}
