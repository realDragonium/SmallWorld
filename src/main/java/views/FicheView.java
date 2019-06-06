package views;

import java.io.IOException;

import controlers.FicheControler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import moleculesampleapp.Xform;
import observable.FicheObservable;
import observers.FicheObserver;

public class FicheView implements FicheObserver{
	
	Xform fiche3dModel;
	FicheControler controler;
	Group graphic;
	
	public FicheView(String race) {
		get3dModel(race);
		controler.registerObserver(this);
	}

	public void get3dModel(String race) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(this.getClass().getResource("/" + race + "Fiche.fxml"));
			graphic = fxmlLoader.load();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(FicheObservable fo) {
		fiche3dModel.setTranslate(fo.getPosition());
	}

}
