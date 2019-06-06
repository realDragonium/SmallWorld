package views;

import java.io.IOException;

import controlers.RaceFicheControler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import moleculesampleapp.Xform;
import observable.FicheObservable;
import observers.FicheObserver;

public class RaceFicheView implements FicheObserver{
	
	Xform fiche3dModel;
	RaceFicheControler controler;
	
	public RaceFicheView(String race) {
		get3dModel(race);
		controler.registerObserver(this);
	}
	
	public void get3dModel(String race) {
		try {
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/" + race + "Fiche.fxml"));
		Group graphic = fxmlLoader.load();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(FicheObservable fo) {
		fiche3dModel.setTranslate(fo.getPosition());
	}

}
