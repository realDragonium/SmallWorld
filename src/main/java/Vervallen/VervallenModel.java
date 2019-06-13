package main.java.Vervallen;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VervallenModel implements VervallenObservable {

	private boolean fichesActive = true;
	
	

	List<VervallenObserver> observers = new ArrayList<>();
	
	
	public VervallenModel() {

	}
	
	public void FichesOmdraaien() {
		
		if(fichesActive == true) {
			fichesActive = false;
		}else if(fichesActive == false) {
			fichesActive = true;
		}
		
		
		
		
		
		notifyAllObs();
		
	}


	@Override
	public void notifyAllObs() {
		for(VervallenObserver ob : observers) {
			ob.update(this);
		}
		
	}


	public void register(VervallenObserver ob) {
		observers.add(ob);
	}
	
	public boolean getFichesActive() {
		if(fichesActive == true) {
			System.out.println("Ras active");
		}else {
			System.out.println("Ras non-active");
		}
		return fichesActive;
	}


}
