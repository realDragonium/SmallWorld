package main.java.Vervallen;


public class VervallenController {

	VervallenModel vervalModel = new VervallenModel();
	
	public void ClickedVervallen() {
		FichesOmdraaien();
		
	}

	private void FichesOmdraaien() {
		vervalModel.FichesOmdraaien();
	}

	public void registerObserver(VervallenObserver ob) {
		vervalModel.register(ob);
		
	}


}
