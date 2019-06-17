package main.java.RoundTurn;

import RoundTurn.RTObserver;

public interface RTObservable {

	void notifyAllObs();
	void register(RTObserver ob);
	String getWhichTurn();
	String getRounds();
}