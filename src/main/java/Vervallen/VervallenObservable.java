package main.java.Vervallen;


public interface VervallenObservable {
	
	void notifyAllObs();
	void register(VervallenObserver ob);
	boolean getFichesActive();

}
